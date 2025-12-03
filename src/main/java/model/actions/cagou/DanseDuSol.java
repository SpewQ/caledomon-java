package model.actions.cagou;

import model.Animal;
import model.actions.Action;

public class DanseDuSol extends Action {
    
    public DanseDuSol() { 
        super(0, 100);
    }

    @Override
    public void executer(Animal user, Animal target) {
        // cible peut être user pour self-buff
        user.applyStage("defense", +1);
        user.applyStage("speed", +1);
        System.out.println(user.getNom() + " utilise Danse du Sol ! Défense et vitesse augmentées.");
    }
}
