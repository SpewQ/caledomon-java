package model.actions.cagou;

import model.Animal;
import model.actions.BuffAction;

public class DanseDuSol extends BuffAction {
    
    public DanseDuSol() { 
        super("Danse du Sol");
    }

    @Override
    public void executer(Animal user, Animal target) {
        // cible peut être user pour self-buff
        user.applyStage("defense", +1);
        user.applyStage("speed", +1);
        System.out.println(user.getNom() + " utilise Danse du Sol ! Défense et vitesse augmentées.");
    }
}
