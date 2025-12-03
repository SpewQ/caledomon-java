package model.actions.tricotraye;

import model.Animal;
import model.Etat;
import model.actions.Action;

public class SouffleDraconique implements Action {

    @Override
    public void executer(Animal attaquant, Animal cible) {

        // Dégâts standards (attaque - défense + 5)
        applyStandardDamage(attaquant, cible, 5, "Souffle Draconique");

        // 20% de chance de paralyser
        if (Math.random() < 0.20) {
            cible.setEtat(Etat.PARALYSE);
            System.out.println(cible.getNom() + " est paralysé et risque de perdre un tour !");
        }
    }
}
