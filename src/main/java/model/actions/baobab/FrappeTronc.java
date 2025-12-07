package model.actions.baobab;

import model.Animal;
import model.actions.Action;

public class FrappeTronc extends Action {
    public FrappeTronc() { super(22, 90); }
    @Override
    public void executer(Animal attaquant, Animal cible) {
        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Frappe Tronc !");
            return;
        }
        applyStandardDamage(attaquant, cible, 0, "Frappe Tronc");
        // 10% chance de paralysie légère
        if (Math.random() < 0.10) {
            cible.applyStatusParalysis();
            System.out.println(cible.getNom() + " est paralysé !");
        }
    }
}
