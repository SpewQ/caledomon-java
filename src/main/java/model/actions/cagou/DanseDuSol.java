package model.actions.cagou;

import model.Animal;
import model.actions.Action;

public class DanseDuSol implements Action {
    @Override
    public void executer(Animal attaquant, Animal cible) {
        int oldDef = attaquant.getDefense();
        int oldVit = attaquant.getVitesse();
        attaquant.setDefense(oldDef + 3);
        attaquant.setVitesse(oldVit + 3);
        System.out.println(attaquant.getNom() + " exécute Danse du Sol ! Défense (" + oldDef + " → " + attaquant.getDefense() + "), Vitesse (" + oldVit + " → " + attaquant.getVitesse() + ").");
    }
}
