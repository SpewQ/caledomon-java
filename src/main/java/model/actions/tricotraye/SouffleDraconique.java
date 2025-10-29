package model.actions.tricotraye;

import model.Animal;
import model.Etat;
import model.actions.Action;

public class SouffleDraconique implements Action {

    @Override
    public void executer(Animal attaquant, Animal cible) {
        System.out.println(attaquant.getNom() + " lance Souffle Draconique !");
        
        int degats = Math.max(0, attaquant.getAttaque() - cible.getDefense() + 5);
        cible.setPv(cible.getPv() - degats);
        System.out.println(cible.getNom() + " subit " + degats + " dégâts !");
        
        // 20% de chance de paralyser
        if (Math.random() < 0.2) {
            cible.setEtat(Etat.PARALYSE);
            System.out.println(cible.getNom() + " est paralysé et risque de perdre un tour !");
        }
    }
}
