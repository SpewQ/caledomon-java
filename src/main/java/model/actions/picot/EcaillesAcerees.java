package model.actions.picot;

import model.Animal;
import model.actions.BuffAction;

public class EcaillesAcerees extends BuffAction {
    public EcaillesAcerees() { super("Ecailles acérées"); }
    @Override
    public void executer(Animal user, Animal target) {
        user.applyStage("attack", +2);
        user.applyStage("speed", +1);
        System.out.println(user.getNom() + " utilise Ecailles acérées : attaque et vitesse augmentées.");
    }
}
