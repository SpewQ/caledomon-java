package model.actions.cerf;

import model.Animal;
import model.actions.BuffAction;

public class NoblesseBestiale extends BuffAction {
    public NoblesseBestiale() { super("Noblesse Bestiale"); }
    @Override
    public void executer(Animal user, Animal target) {
        user.applyStage("attack", +1);
        user.applyStage("defense", +1);
        System.out.println(user.getNom() + " utilise Noblesse Bestiale : attaque et défense augmentées.");
    }
}
