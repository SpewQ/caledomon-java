package model.actions.cagou;

import model.Animal;
import model.actions.Action;

public class SautDeBrousse extends Action {

    public SautDeBrousse() { 
        super(40, 70);
    } // plus de puissance, moins précis

    @Override
    public void executer(Animal attaquant, Animal cible) {

        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Saut de Brousse !");
            return;
        }

        // bonus +5 par rapport à l'attaque de base
        applyStandardDamage(attaquant, cible, 5, "Saut de Brousse");

        // 90% de chances de self-damage (option) :
        if (Math.random() < 0.90) {
            long selfDmg = Math.max(1, (int) Math.round(attaquant.getMaxPv() * 0.05));
            attaquant.setPv((int) (attaquant.getPv() - selfDmg));
            System.out.println(attaquant.getNom() + " subit " + selfDmg + " de recul suite à Saut de Brousse.");
        }
    }
    
}
