package model.actions.dawa;

import model.Animal;
import model.actions.BuffAction;

public class DanseAquatique extends BuffAction {
    public DanseAquatique() { super("Danse Aquatique"); }
    @Override
    public void executer(Animal user, Animal target) {
        user.applyStage("speed", +2);
        System.out.println(user.getNom() + " utilise Danse Aquatique : vitesse augmentée.");
    }
}
