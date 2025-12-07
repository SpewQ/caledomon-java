package model.actions.notou;

import model.Animal;
import model.actions.BuffAction;

public class PlumesSombres extends BuffAction {
    public PlumesSombres() { super("Plumes Sombres"); }
    @Override
    public void executer(Animal user, Animal target) {
        user.applyStage("defense", +1);
        user.applyStage("attack", +1);
        System.out.println(user.getNom() + " utilise Plumes Sombres : attaque et défense augmentées.");
    }
}
