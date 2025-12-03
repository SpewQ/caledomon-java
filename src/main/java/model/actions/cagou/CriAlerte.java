package model.actions.cagou;

import model.Animal;
import model.actions.DebuffAction;

public class CriAlerte extends DebuffAction {

    public CriAlerte() {
        super("Cri d'Alerte"); // pas de damage, juste effet de stage
    }

    @Override
    public void executer(Animal user, Animal target) {
        // réduit d'un stage l'attaque cible
        target.applyStage("attack", -1);
        System.out.println(user.getNom() + " utilise Cri d'alerte ! L'attaque de " + target.getNom() + " baisse.");
    }
}
