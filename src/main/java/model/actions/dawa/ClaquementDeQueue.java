package model.actions.dawa;

import model.Animal;
import model.actions.Action;

public class ClaquementDeQueue extends Action {
    public ClaquementDeQueue() { super(16, 100); }
    @Override
    public void executer(Animal attaquant, Animal cible) {
        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Claquement de Queue !");
            return;
        }
        applyStandardDamage(attaquant, cible, 0, "Claquement de Queue");
    }
}
