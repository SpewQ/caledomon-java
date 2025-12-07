package model.actions.ver;

import model.Animal;
import model.actions.Action;

public class MorsureMandibule extends Action {
    public MorsureMandibule() { super(14, 100); }
    @Override
    public void executer(Animal attaquant, Animal cible) {
        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Morsure de Mandibule !");
            return;
        }
        applyStandardDamage(attaquant, cible, 0, "Morsure de Mandibule");
        if (Math.random() < 0.20) {
            cible.applyStage("defense", -1);
            System.out.println(cible.getNom() + " perd un peu de défense !");
        }
    }
}
