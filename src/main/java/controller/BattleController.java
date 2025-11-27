package controller;

import java.util.Random;

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

    public BattleController(Battle battle) {
        this.battle = battle;
    }

    public void setView(BattleView view) {
        this.view = view;
    }

    /**
     * Lance le combat et initialise l'affichage (noms, PV, images...).
     */
    public void startBattle() {
        if (view != null) {

            // Tirage aléatoire de l'environnement
            EnvironmentType env = EnvironmentType.random();
            view.setEnvironment(env);                          // ✅ appel sur l'instance
            view.addLog("Environnement : " + env.name());      // ✅ appel sur l'instance

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

            view.addLog("Le combat commence !");
        }
    }

    public void onPlayerAction(String actionName) {
        if (battle.combatTermine()) {
            view.addLog("Le combat est terminé.");
            view.disableActions();
            return;
        }

        Action playerAction = parseAction(actionName);
        Action iaAction = randomIaAction();

        playerAction.executer(battle.getJoueur1(), battle.getJoueur2());
        if (!battle.combatTermine()) {
            iaAction.executer(battle.getJoueur2(), battle.getJoueur1());
        }

        view.refreshHp(
            battle.getJoueur1().getPv(), battle.getJoueur2().getPv(),
            battle.getJoueur1().getMaxPv(), battle.getJoueur2().getMaxPv()
        );
        view.addLog("Joueur: " + actionLabel(playerAction) + " | IA: " + actionLabel(iaAction));
        view.addLog("PV Joueur: " + battle.getJoueur1().getPv() +
                    " | PV IA: " + battle.getJoueur2().getPv());

        if (battle.combatTermine()) {
            String result = (battle.getJoueur1().estVivant())
                    ? "Victoire du joueur !"
                    : "Défaite...";
            view.addLog(result);
            view.disableActions();
        }
    }

    private Action parseAction(String name) {
        switch (name.toLowerCase()) {
            case "coup":  return new CoupDeBec();
            case "cri":   return new CriAlerte();
            case "saut":  return new SautDeBrousse();
            case "danse": return new DanseDuSol();
            default:      return new CoupDeBec();
        }
    }

    private Action randomIaAction() {
        int pick = rnd.nextInt(4);
        switch (pick) {
            case 0: return new AttackAction();
            case 1: return new DefendAction();
            case 2: return new HealAction();
            default: return new SpecialAction();
        }
    }

    private String actionLabel(Action a) {
        if (a instanceof CoupDeBec)     return "Coup de Bec";
        if (a instanceof CriAlerte)     return "Cri d'Alerte";
        if (a instanceof SautDeBrousse) return "Saut de Brousse";
        if (a instanceof DanseDuSol)    return "Danse du Sol";
        if (a instanceof AttackAction)  return "Attaquer";
        if (a instanceof DefendAction)  return "Défendre";
        if (a instanceof HealAction)    return "Soigner";
        if (a instanceof SpecialAction) return "Spéciale";
        return "Spéciale";
    }
}
