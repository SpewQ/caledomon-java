package model.actions.notou;

import model.Animal;
import model.actions.Action;

public class SerreDeNuit extends Action {
    public SerreDeNuit() { super(20, 95); }
    @Override
    public void executer(Animal attaquant, Animal cible) {
        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Serre de Nuit !");
            return;
        }
        applyStandardDamage(attaquant, cible, 0, "Serre de Nuit");
        if (Math.random() < 0.10) {
            cible.applyStatusParalysis();
            System.out.println(cible.getNom() + " est paralysé !");
        }
    }
}
