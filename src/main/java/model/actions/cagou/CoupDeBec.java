package model.actions.cagou;

import model.Animal;
import model.actions.Action;

public class CoupDeBec implements Action {
    @Override
    public void executer(Animal attaquant, Animal cible) {
        // pas de bonus, juste l'attaque classique
        applyStandardDamage(attaquant, cible, 0, "Coup de Bec");
    }
}
