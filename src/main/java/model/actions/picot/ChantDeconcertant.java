package model.actions.picot;

import model.Animal;
import model.actions.DebuffAction;

public class ChantDeconcertant extends DebuffAction {
    public ChantDeconcertant() { super("Chant Déconcertant"); }
    @Override
    public void executer(Animal user, Animal target) {
        target.applyStage("attack", -1);
        target.applyStage("defense", -1);
        System.out.println(user.getNom() + " utilise Chant Déconcertant : l'ennemi est affaibli.");
    }
}
