package model.actions.tortue;

import model.Animal;
import model.actions.Action;

public class CoupDeCarapace extends Action {
    public CoupDeCarapace() { super(25, 90); }
    @Override
    public void executer(Animal attaquant, Animal cible) {
        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Coup de Carapace !");
            return;
        }
        applyStandardDamage(attaquant, cible, 0, "Coup de Carapace");
        if (Math.random() < 0.20) {
            cible.applyStage("speed", -1);
            System.out.println(cible.getNom() + " voit sa vitesse baisser !");
        }
    }
}
