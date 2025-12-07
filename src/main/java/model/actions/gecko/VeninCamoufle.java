package model.actions.gecko;

import model.Animal;
import model.actions.Action;

public class VeninCamoufle extends Action {
    public VeninCamoufle() { super(12, 100); }
    @Override
    public void executer(Animal attaquant, Animal cible) {
        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Venin Camouflé !");
            return;
        }
        applyStandardDamage(attaquant, cible, 0, "Venin Camouflé");
        if (Math.random() < 0.50) {
            cible.applyStatusPoison();
            System.out.println(cible.getNom() + " est empoisonné !");
        }
    }
}
