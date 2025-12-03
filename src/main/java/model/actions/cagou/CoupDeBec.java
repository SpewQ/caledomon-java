package model.actions.cagou;

import model.Animal;
import model.actions.Action;

public class CoupDeBec extends Action {

    public CoupDeBec() {
        super(15, 100);
    }

    @Override
    public void executer(Animal attaquant, Animal cible) {

        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Coup de Bec !");
            return;
        }

        // pas de bonus, juste l'attaque classique
        applyStandardDamage(attaquant, cible, 0, "Coup de Bec");
    }
}
