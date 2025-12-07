package model.actions.gecko;

import model.Animal;
import model.actions.Action;

public class LueurHypnotique extends Action {
    public LueurHypnotique() { super(0, 90); }
    @Override
    public void executer(Animal attaquant, Animal cible) {
        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Lueur Hypnotique !");
            return;
        }
        if (Math.random() < 0.50) {
            cible.applyStatusParalysis();
            System.out.println(cible.getNom() + " est paralysé par la Lueur Hypnotique !");
        } else {
            System.out.println(cible.getNom() + " résiste à la Lueur Hypnotique.");
        }
    }
}
