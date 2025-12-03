package model.actions.tricotraye;

import model.Animal;
import model.actions.Action;

public class ContractionSerpentine implements Action {

    @Override
    public void executer(Animal attaquant, Animal cible) {

        // 1) Dégâts standardisés avec malus -3
        //    (attaque - défense - 3) × multiplicateur de type
        applyStandardDamage(attaquant, cible, -3, "Contraction Serpentine");

        // 2) Effet spécial : réduire la vitesse de la cible
        cible.setVitesse(Math.max(1, cible.getVitesse() - 2));
        System.out.println(cible.getNom() + " voit sa vitesse réduite !");
    }
}
