package model.actions.notou;

import model.Animal;
import model.actions.DebuffAction;

public class CriObscur extends DebuffAction {
    public CriObscur() { super("Cri Obscur"); }
    @Override
    public void executer(Animal user, Animal target) {
        target.applyStage("attack", -1);
        target.applyStage("speed", -1);
        System.out.println(user.getNom() + " utilise Cri Obscur : l'ennemi perd attaque et vitesse.");
    }
}
