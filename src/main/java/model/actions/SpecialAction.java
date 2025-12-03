package model.actions;

import model.Animal;

public class SpecialAction extends Action {
    @Override
    public void executer(Animal attaquant, Animal cible) {
        attaquant.actionSpeciale(cible);
    }
}