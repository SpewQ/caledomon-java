package model.actions.baobab;

import model.Animal;
import model.actions.DebuffAction;

public class ChantDuVent extends DebuffAction {
    public ChantDuVent() { super("Chant du Vent"); }
    @Override
    public void executer(Animal user, Animal target) {
        target.applyStage("speed", -1);
        target.applyStage("attack", -1);
        System.out.println(user.getNom() + " utilise Chant du Vent : vitesse et attaque ennemies diminuées.");
    }
}
