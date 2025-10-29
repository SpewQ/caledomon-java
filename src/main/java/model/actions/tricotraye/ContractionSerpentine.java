package model.actions.tricotraye;

import model.Animal;
import model.actions.Action;

public class ContractionSerpentine implements Action {

    @Override
    public void executer(Animal attaquant, Animal cible) {
        System.out.println(attaquant.getNom() + " utilise Contraction Serpentine !");
        
        int degats = Math.max(0, attaquant.getAttaque() - cible.getDefense() - 3);
        cible.setPv(cible.getPv() - degats);
        System.out.println(cible.getNom() + " subit " + degats + " dégâts !");
        
        // Ralentit légèrement la cible
        cible.setVitesse(Math.max(1, cible.getVitesse() - 2));
        System.out.println(cible.getNom() + " voit sa vitesse réduite !");
    }
}
