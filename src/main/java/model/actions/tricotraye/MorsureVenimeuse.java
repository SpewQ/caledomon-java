package model.actions.tricotraye;

import model.Animal;
import model.Etat;
import model.actions.Action;

public class MorsureVenimeuse implements Action {

    @Override
    public void executer(Animal attaquant, Animal cible) {
        int degats = Math.max(0, attaquant.getAttaque() - cible.getDefense());
        cible.setPv(cible.getPv() - degats);
        System.out.println(attaquant.getNom() + " utilise Morsure Venimeuse ! (" + degats + " dégâts)");

        // 30% de chance d'empoisonner
        if (Math.random() < 0.30) {
            cible.setEtat(Etat.EMPOISONNE);
            System.out.println(cible.getNom() + " est empoisonné !");
        }
    }
}
