package model.actions;

import model.Animal;

public class HealAction extends BuffAction {

    public HealAction() { 
        super("HealAction");
    }

    @Override
    public void executer(Animal attaquant, Animal cible) {
        attaquant.soigner();
    }
}