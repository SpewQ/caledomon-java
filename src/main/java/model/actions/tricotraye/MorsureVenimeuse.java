package model.actions.tricotraye;

import model.Animal;
import model.actions.Action;

public class MorsureVenimeuse extends Action {

    public MorsureVenimeuse() {
        super(18, 95); 
    } // power ~18, précision 95%

    @Override
    public void executer(Animal attaquant, Animal cible) {

        // précision
        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Morsure Venimeuse !");
            return;
        }

        // Dégâts standardisés (pas de bonus : +0)
        applyStandardDamage(attaquant, cible, 0, "Morsure Venimeuse");

        // 30% de chance d'empoisonner
        if (Math.random() < 0.30) {
            // applique poison (si non déjà empoisonné)
            cible.applyStatusPoison();
        }
    }
}
