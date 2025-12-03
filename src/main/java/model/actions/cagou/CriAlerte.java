package model.actions.cagou;

import model.Animal;
import model.actions.Action;

public class CriAlerte extends Action {

    public CriAlerte() {
        super(0, 100); // pas de damage, juste effet de stage
    }

    @Override
    public void executer(Animal user, Animal target) {
        // réduit d'un stage l'attaque cible
        target.applyStage("attack", -1);
        System.out.println(user.getNom() + " utilise Cri d'alerte ! L'attaque de " + target.getNom() + " baisse.");
    }
}
