package model.actions.dawa;

import model.Animal;
import model.actions.DebuffAction;

public class BrouillardMarin extends DebuffAction {
    public BrouillardMarin() { super("Brouillard Marin"); }
    @Override
    public void executer(Animal user, Animal target) {
        target.applyStage("speed", -1); 
        System.out.println(user.getNom() + " utilise Brouillard Marin : vitesse ennemie réduite.");
    }
}
