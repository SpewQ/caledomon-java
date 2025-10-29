package model.actions.cagou;

import model.Animal;
import model.actions.Action;

public class CoupDeBec implements Action {
    @Override
    public void executer(Animal attaquant, Animal cible) {
        int degats = Math.max(0, attaquant.getAttaque() - cible.getDefense());
        cible.setPv(cible.getPv() - degats);
        System.out.println(attaquant.getNom() + " utilise Coup de Bec ! (" + degats + " dégâts)");
    }
}
