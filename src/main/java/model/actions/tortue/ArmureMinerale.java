package model.actions.tortue;

import model.Animal;
import model.actions.BuffAction;

public class ArmureMinerale extends BuffAction {
    public ArmureMinerale() { super("Armure Minérale"); }
    @Override
    public void executer(Animal user, Animal target) {
        user.applyStage("defense", +2);
        System.out.println(user.getNom() + " renforce sa carapace !");
    }
}
