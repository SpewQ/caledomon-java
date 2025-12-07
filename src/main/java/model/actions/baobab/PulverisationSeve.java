package model.actions.baobab;

import model.Animal;
import model.actions.Action;

public class PulverisationSeve extends Action {
    public PulverisationSeve() { super(16, 100); }
    @Override
    public void executer(Animal attaquant, Animal cible) {
        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Pulvérisation Sève !");
            return;
        }
        applyStandardDamage(attaquant, cible, 0, "Pulvérisation Sève");
        if (Math.random() < 0.30) {
            cible.applyStatusPoison();
            System.out.println(cible.getNom() + " est empoisonné !");
        }
    }
}
