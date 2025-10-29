package model.actions.cagou;

import model.Animal;
import model.actions.Action;

public class CriAlerte implements Action {
    @Override
    public void executer(Animal attaquant, Animal cible) {
        int old = cible.getAttaque();
        int newVal = Math.max(0, old - 3);
        cible.setAttaque(newVal);
        System.out.println(attaquant.getNom() + " pousse un Cri d’Alerte ! L’attaque de " + cible.getNom() + " diminue (" + old + " → " + newVal + ").");
    }
}
