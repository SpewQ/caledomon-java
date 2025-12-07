package model.actions.gecko;

import model.Animal;
import model.actions.Action;

public class QueueFouetRapide extends Action {
    public QueueFouetRapide() { super(15, 100); }
    @Override
    public void executer(Animal attaquant, Animal cible) {
        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Queue-Fouet Rapide !");
            return;
        }
        applyStandardDamage(attaquant, cible, 0, "Queue-Fouet Rapide");
        // gagne un stage de vitesse
        attaquant.applyStage("speed", +1);
        System.out.println(attaquant.getNom() + " devient plus rapide !");
    }
}
