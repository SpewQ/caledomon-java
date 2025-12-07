package model.actions.cerf;

import model.Animal;
import model.actions.Action;

public class ChargeCornee extends Action {
    public ChargeCornee() { super(24, 95); }
    @Override
    public void executer(Animal attaquant, Animal cible) {
        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Charge Cornée !");
            return;
        }
        applyStandardDamage(attaquant, cible, 0, "Charge Cornée");
        if (Math.random() < 0.20) {
            cible.applyStage("defense", -1);
            System.out.println(cible.getNom() + " voit sa défense baisser !");
        }
    }
}
