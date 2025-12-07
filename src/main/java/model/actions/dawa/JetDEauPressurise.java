package model.actions.dawa;

import model.Animal;
import model.actions.Action;

public class JetDEauPressurise extends Action {
    public JetDEauPressurise() { super(20, 95); }
    @Override
    public void executer(Animal attaquant, Animal cible) {
        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Jet d'Eau Pressurisé !");
            return;
        }
        applyStandardDamage(attaquant, cible, 0, "Jet d'Eau Pressurisé");
        if (Math.random() < 0.20) {
            cible.applyStage("attack", -1);
            System.out.println(cible.getNom() + " voit son attaque baisser !");
        }
    }
}
