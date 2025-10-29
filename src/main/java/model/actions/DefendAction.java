package model.actions;

import model.Animal;

public class DefendAction implements Action {
    @Override
    public void executer(Animal attaquant, Animal cible) {
        attaquant.defendre();
    }
}