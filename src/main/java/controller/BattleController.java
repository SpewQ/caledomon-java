package controller;

import model.Battle;
import model.actions.Action;
import model.actions.AttackAction;
import model.actions.DefendAction;
import model.actions.HealAction;
import model.actions.SpecialAction;
import view.BattleView;

import java.util.Random;

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

    public void startBattle() {
        if (view != null) {
            view.bindNames(battle.getJoueur1().getNom(), battle.getJoueur2().getNom());
            view.refreshHp(battle.getJoueur1().getPv(), battle.getJoueur2().getPv(),
                           battle.getJoueur1().getMaxPv(), battle.getJoueur2().getMaxPv());
            view.appendMessage("Le combat commence !");
        }
    }

    public void onPlayerAction(String actionName) {
        if (battle.combatTermine()) {
            view.appendMessage("Le combat est terminé.");
            view.disableActions();
            return;
        }

        Action playerAction = parseAction(actionName);
        Action iaAction = randomIaAction();

        playerAction.executer(battle.getJoueur1(), battle.getJoueur2());
        if (!battle.combatTermine()) {
            iaAction.executer(battle.getJoueur2(), battle.getJoueur1());
        }

        view.refreshHp(battle.getJoueur1().getPv(), battle.getJoueur2().getPv(),
                       battle.getJoueur1().getMaxPv(), battle.getJoueur2().getMaxPv());
        view.appendMessage("Joueur: " + actionLabel(playerAction) + " | IA: " + actionLabel(iaAction));
        view.appendMessage("PV Joueur: " + battle.getJoueur1().getPv() +
                           " | PV IA: " + battle.getJoueur2().getPv());

        if (battle.combatTermine()) {
            String result = (battle.getJoueur1().estVivant())
                    ? "Victoire du joueur !"
                    : "Défaite...";
            view.appendMessage(result);
            view.disableActions();
        }
    }

    private Action parseAction(String name) {
        switch (name.toLowerCase()) {
            case "attaquer": return new AttackAction();
            case "defendre": return new DefendAction();
            case "soigner":  return new HealAction();
            case "speciale":
            default:         return new SpecialAction();
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
        if (a instanceof AttackAction) return "Attaquer";
        if (a instanceof DefendAction) return "Défendre";
        if (a instanceof HealAction)   return "Soigner";
        return "Spéciale";
    }
}