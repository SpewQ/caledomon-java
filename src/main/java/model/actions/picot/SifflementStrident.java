package model.actions.picot;

import model.Animal;
import model.actions.Action;

public class SifflementStrident extends Action {
    public SifflementStrident() { super(18, 95); }
    @Override
    public void executer(Animal attaquant, Animal cible) {
        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Sifflement Strident !");
            return;
        }
        applyStandardDamage(attaquant, cible, 0, "Sifflement Strident");
        if (Math.random() < 0.30) {
            cible.applyStatusParalysis();
            System.out.println(cible.getNom() + " est paralysé !");
        }
    }
}
