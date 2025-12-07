package model.actions.cerf;

import model.Animal;
import model.actions.Action;

public class BruitDeSabots extends Action {
    public BruitDeSabots() { super(10, 90); } // multi-hit handled here as two hits
    @Override
    public void executer(Animal attaquant, Animal cible) {
        int hits = 2;
        for (int i = 0; i < hits; i++) {
            if (Math.random() * 100 > this.accuracy) {
                System.out.println(attaquant.getNom() + " rate un coup de Bruit de Sabots !");
            } else {
                applyStandardDamage(attaquant, cible, 0, "Bruit de Sabots (coup)");
            }
        }
    }
}
