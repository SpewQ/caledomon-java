package model.actions.gecko;

import model.Animal;
import model.actions.BuffAction;

public class Acceleration extends BuffAction {
    public Acceleration() { super("Accélération"); }
    @Override
    public void executer(Animal user, Animal target) {
        user.applyStage("speed", +2);
        System.out.println(user.getNom() + " utilise Accélération : vitesse fortement augmentée.");
    }
}
