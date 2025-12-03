package model.actions.cagou;

import model.Animal;
import model.actions.Action;

public class SautDeBrousse implements Action {

    @Override
    public void executer(Animal attaquant, Animal cible) {
        // bonus +5 par rapport à l'attaque de base
        applyStandardDamage(attaquant, cible, 5, "Saut de Brousse");
    }
}
