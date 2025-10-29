package model.actions;

import model.Animal;

public interface Action {
    void executer(Animal attaquant, Animal cible);
}