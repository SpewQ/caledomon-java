package model.actions.roussette;

import model.Animal;
import model.actions.Action;

public class OndeSonique extends Action {
    public OndeSonique() { super(20, 90); }
    @Override
    public void executer(Animal attaquant, Animal cible) {
        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Onde Sonique' !");
            return;
        }
        applyStandardDamage(attaquant, cible, 0, "Onde Soniq'");
        if (Math.random() < 0.20) {
            cible.applyStage("attack", -1);
            System.out.println(cible.getNom() + " voit son attaque baisser !");
        }
    }
}
