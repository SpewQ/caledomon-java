package model.actions.picot;

import model.Animal;
import model.actions.Action;

public class PicPicFurie extends Action {
    public PicPicFurie() { super(12, 90); }
    @Override
    public void executer(Animal attaquant, Animal cible) {
        int hits = 2 + (Math.random() < 0.5 ? 1 : 0); // 2 ou 3 coups
        for (int i = 0; i < hits; i++) {
            if (Math.random() * 100 > this.accuracy) {
                System.out.println(attaquant.getNom() + " rate un coup de Pic-Pic-Furie !");
            } else {
                applyStandardDamage(attaquant, cible, 0, "Pic-Pic-Furie (coup)");
            }
        }
    }
}
