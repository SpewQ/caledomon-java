package model.actions.cagou;

import model.Animal;
import model.actions.Action;

public class SautDeBrousse implements Action {
    @Override
    public void executer(Animal attaquant, Animal cible) {
        int base = Math.max(0, attaquant.getAttaque() - cible.getDefense());
        int degats = base + 5; // petit bonus
        cible.setPv(cible.getPv() - degats);
        System.out.println(attaquant.getNom() + " effectue un Saut de Brousse ! (" + degats + " dégâts)");
    }
}
