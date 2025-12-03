package model.actions;

import model.Animal;

public class DefendAction extends BuffAction {

    public DefendAction() { 
        super("DefendAction");
    }
    
    @Override
    public void executer(Animal attaquant, Animal cible) {
        attaquant.defendre();
    }
}