package model.actions.baobab;

import model.Animal;
import model.actions.BuffAction;

public class Enracinement extends BuffAction {
    public Enracinement() { super("Enracinement"); }
    @Override
    public void executer(Animal user, Animal target) {
        // Défense +2 (stages), petit soin = +5% maxPV (arrondi)
        user.applyStage("defense", +2);
        int heal = Math.max(1, user.getMaxPv() * 5 / 100);
        user.setPv(user.getPv() + heal);
        System.out.println(user.getNom() + " utilise Enracinement : Défense augmentée et récupère " + heal + " PV.");
    }
}
