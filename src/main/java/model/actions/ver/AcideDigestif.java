package model.actions.ver;

import model.Animal;
import model.actions.Action;

public class AcideDigestif extends Action {
    public AcideDigestif() { super(18, 95); }
    @Override
    public void executer(Animal attaquant, Animal cible) {
        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Acide Digestif !");
            return;
        }
        applyStandardDamage(attaquant, cible, 0, "Acide Digestif");
        if (Math.random() < 0.30) {
            cible.applyStatusPoison();
            System.out.println(cible.getNom() + " est empoisonné !");
        }
    }
}
