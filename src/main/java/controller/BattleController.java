package controller;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.Battle;
import model.EnvironmentType;
import model.actions.Action;
import model.actions.AttackAction;
import model.actions.DefendAction;
import model.actions.HealAction;
import model.actions.SpecialAction;
import model.actions.cagou.CoupDeBec;
import model.actions.cagou.CriAlerte;
import model.actions.cagou.DanseDuSol;
import model.actions.cagou.SautDeBrousse;
import view.BattleView;

public class BattleController {
    private final Battle battle;
    private BattleView view;
    private final Random rnd = new Random();

    // callback lorsque le combat est terminé. true = joueur gagne
    private Consumer<Boolean> onBattleEnded;

    // musique et effets (optionnel)
    private MediaPlayer bgmPlayer;
    private AudioClip sfxVictory;
    private AudioClip sfxDefeat;

    public BattleController(Battle battle) {
        this.battle = battle;
        loadAudio();
    }

    private void loadAudio() {
        try {
            var bgmUrl = getClass().getResource("/sounds/bgm.mp3");
            if (bgmUrl != null) {
                bgmPlayer = new MediaPlayer(new Media(bgmUrl.toExternalForm()));
                bgmPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                bgmPlayer.setVolume(0.5);
            }

            var vUrl = getClass().getResource("/sounds/victory.wav");
            if (vUrl != null) sfxVictory = new AudioClip(vUrl.toExternalForm());

            var dUrl = getClass().getResource("/sounds/defeat.wav");
            if (dUrl != null) sfxDefeat = new AudioClip(dUrl.toExternalForm());
        } catch (Exception e) {
            System.err.println("Impossible de charger les sons : " + e.getMessage());
        }
    }

    public void setView(BattleView view) {
        this.view = view;
    }

    public void setOnBattleEnded(Consumer<Boolean> callback) {
        this.onBattleEnded = callback;
    }

    /**
     * Lance le combat et initialise l'affichage (noms, PV, images..., boutons d'actions).
     */
    public void startBattle() {
        if (view != null) {

            // play bgm if present
            if (bgmPlayer != null) {
                bgmPlayer.stop();
                bgmPlayer.play();
            }

            // Tirage aléatoire de l'environnement
            EnvironmentType env = EnvironmentType.random();
            view.setEnvironment(env);
            view.addLog("Environnement : " + env.name());

            // Noms et points de vie
            view.bindNames(battle.getJoueur1().getNom(), battle.getJoueur2().getNom());
            view.refreshHp(
                battle.getJoueur1().getPv(), battle.getJoueur2().getPv(),
                battle.getJoueur1().getMaxPv(), battle.getJoueur2().getMaxPv()
            );

            // Images des Calédomon
            view.setImages(
                battle.getJoueur1().getNom(),
                battle.getJoueur2().getNom()
            );

            // --- charger les actions du joueur (boutons dynamiques) ---
            if (battle.getJoueur1().getActions() != null) {
                view.setPlayerActions(battle.getJoueur1().getActions());
            }

            view.addLog("Le combat commence !");
        }
    }

    /**
     * Appelé par la vue lorsqu'un bouton d'action est pressé.
     * Le paramètre est une Action concrète (ex : CoupDeBec).
     */
    public void onPlayerAction(Action action) {
        if (battle.combatTermine()) {
            view.addLog("Le combat est terminé.");
            view.disableActions();
            return;
        }

        // action IA choisie parmi les moves de l'IA si disponible, sinon random fallback
        Action iaAction = randomIaAction();

        // Exécution de l'action du joueur
        action.executer(battle.getJoueur1(), battle.getJoueur2());

        // si l'IA est encore en vie, elle répond
        if (!battle.combatTermine()) {
            iaAction.executer(battle.getJoueur2(), battle.getJoueur1());
        }

        // refresh UI
        view.refreshHp(
            battle.getJoueur1().getPv(), battle.getJoueur2().getPv(),
            battle.getJoueur1().getMaxPv(), battle.getJoueur2().getMaxPv()
        );

        view.addLog("Joueur: " + actionLabel(action) + " | IA: " + actionLabel(iaAction));
        view.addLog("PV Joueur: " + battle.getJoueur1().getPv() +
                    " | PV IA: " + battle.getJoueur2().getPv());

        if (battle.combatTermine()) {
            boolean playerWon = battle.getJoueur1().estVivant();
            String result = playerWon ? "Victoire du joueur !" : "Défaite...";
            view.addLog(result);
            view.disableActions();

            // stop BGM
            if (bgmPlayer != null) {
                bgmPlayer.stop();
            }

            // play sfx
            if (playerWon && sfxVictory != null) sfxVictory.play();
            if (!playerWon && sfxDefeat != null) sfxDefeat.play();

            // small delay optional could be added before calling callback
            if (onBattleEnded != null) {
                onBattleEnded.accept(playerWon);
            }
        }
    }

    /**
     * Choisit une action pour l'IA parmi ses actions si possible.
     */
    private Action randomIaAction() {
        List<Action> iaMoves = battle.getJoueur2().getActions();
        if (iaMoves != null && !iaMoves.isEmpty()) {
            return iaMoves.get(rnd.nextInt(iaMoves.size()));
        }
        // fallback générique
        int pick = rnd.nextInt(4);
        switch (pick) {
            case 0: return new AttackAction();
            case 1: return new DefendAction();
            case 2: return new HealAction();
            default: return new SpecialAction();
        }
    }

    /**
     * Fournit un label lisible pour une action (utilisé aussi par la view).
     * Rendue publique pour que BattleView puisse l'utiliser.
     */
    public String actionLabel(Action a) {
        if (a instanceof CoupDeBec)     return "Coup de Bec";
        if (a instanceof CriAlerte)     return "Cri d'Alerte";
        if (a instanceof SautDeBrousse) return "Saut de Brousse";
        if (a instanceof DanseDuSol)    return "Danse du Sol";
        if (a instanceof AttackAction)  return "Attaquer";
        if (a instanceof DefendAction)  return "Défendre";
        if (a instanceof HealAction)    return "Soigner";
        if (a instanceof SpecialAction) return "Spéciale";
        // fallback : nom de la classe sans package
        String cn = a.getClass().getSimpleName();
        return cn.replaceAll("([A-Z])", " $1").trim();
    }
}