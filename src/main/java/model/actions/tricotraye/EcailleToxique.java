package model.actions.tricotraye;

import model.Animal;
import model.actions.Action;

public class EcailleToxique extends Action {

    public EcailleToxique() {
        super(10, 80); // puissance approximative 10, précision 80%
    }

    @Override
    public void executer(Animal attaquant, Animal cible) {

        System.out.println(attaquant.getNom() + " projette une Écaille Toxique !");

        // 80% de précision
        if (Math.random() < 0.8) {
            // dégâts standardisés + bonus de +10
            applyStandardDamage(attaquant, cible, 10, "Écaille Toxique");
        } else {
            System.out.println(attaquant.getNom() + " rate son attaque !");
        }
    }
}
