package model.actions.roussette;

import model.Animal;
import model.actions.BuffAction;

public class Nocturnisation extends BuffAction {
    public Nocturnisation() { super("Nocturnisation"); }
    @Override
    public void executer(Animal user, Animal target) {
        user.applyStage("speed", +2);
        System.out.println(user.getNom() + " utilise Nocturnisation : vitesse augmentée.");
    }
}
