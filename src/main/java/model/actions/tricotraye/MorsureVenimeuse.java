package model.actions.tricotraye;

import model.Animal;
import model.Etat;
import model.actions.Action;

public class MorsureVenimeuse implements Action {

    @Override
    public void executer(Animal attaquant, Animal cible) {

        // Dégâts standardisés (pas de bonus : +0)
        applyStandardDamage(attaquant, cible, 0, "Morsure Venimeuse");

        // 30% de chance d'empoisonner
        if (Math.random() < 0.30) {
            cible.setEtat(Etat.EMPOISONNE);
            System.out.println(cible.getNom() + " est empoisonné !");
        }
    }
}
