package model.actions.tortue;

import model.Animal;
import model.actions.Action;

public class JetDeBoue extends Action {
    public JetDeBoue() { super(15, 95); }
    @Override
    public void executer(Animal attaquant, Animal cible) {
        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Jet de Boue !");
            return;
        }
        applyStandardDamage(attaquant, cible, 0, "Jet de Boue");
        cible.applyStage("speed", -1);
        System.out.println(cible.getNom() + " voit sa vitesse diminuer !");
    }
}
