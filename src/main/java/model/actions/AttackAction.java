package model.actions;

import model.Animal;

public class AttackAction extends Action {
    @Override
    public void executer(Animal attaquant, Animal cible) {
        attaquant.attaquer(cible);
    }
}