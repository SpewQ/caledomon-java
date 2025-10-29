package model.actions.tricotraye;

import model.Animal;
import model.actions.Action;

public class EcailleToxique implements Action {

    @Override
    public void executer(Animal attaquant, Animal cible) {
        System.out.println(attaquant.getNom() + " projette une Écaille Toxique !");
        
        if (Math.random() < 0.8) { // 80% de précision
            int degats = Math.max(0, attaquant.getAttaque() - cible.getDefense() + 10);
            cible.setPv(cible.getPv() - degats);
            System.out.println(cible.getNom() + " subit " + degats + " dégâts !");
        } else {
            System.out.println(attaquant.getNom() + " rate son attaque !");
        }
    }
}
