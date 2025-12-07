package model.actions.roussette;

import model.Animal;
import model.actions.Action;

public class MorsureSanguine extends Action {
    public MorsureSanguine() { super(18, 100); }
    @Override
    public void executer(Animal attaquant, Animal cible) {
        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Morsure Sanguine !");
            return;
        }
        // dégâts
        int before = cible.getPv();
        applyStandardDamage(attaquant, cible, 0, "Morsure Sanguine");
        int dealt = before - cible.getPv();
        int heal = Math.max(1, dealt / 2);
        attaquant.setPv(attaquant.getPv() + heal);
        System.out.println(attaquant.getNom() + " se soigne de " + heal + " PV grâce à Morsure Sanguine.");
    }
}
