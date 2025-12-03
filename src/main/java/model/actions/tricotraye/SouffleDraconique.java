package model.actions.tricotraye;

import model.Animal;
import model.actions.Action;

public class SouffleDraconique extends Action {

    public SouffleDraconique() {
        super(20, 90);
    } // plus puissant, moins précis

    @Override
    public void executer(Animal attaquant, Animal cible) {

        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Souffle Draconique !");
            return;
        }

        // Dégâts standards (attaque - défense + 5)
        applyStandardDamage(attaquant, cible, 5, "Souffle Draconique");

        // 20% de chance de paralyser
        // 30% chance de paralyser
        if (Math.random() < 0.30) {
            cible.applyStatusParalysis();
        }
    }
}
