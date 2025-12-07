package model.actions.notou;

import model.Animal;
import model.actions.Action;

public class SouffleLunaire extends Action {
    public SouffleLunaire() { super(18, 100); }
    @Override
    public void executer(Animal attaquant, Animal cible) {
        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Souffle Lunaire !");
            return;
        }
        applyStandardDamage(attaquant, cible, 0, "Souffle Lunaire");
        if (Math.random() < 0.20) {
            cible.applyStage("attack", -1);
            System.out.println(cible.getNom() + " voit son attaque diminuer !");
        }
    }
}
