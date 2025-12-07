package model.actions.ver;

import model.Animal;
import model.actions.DebuffAction;

public class FilEntravant extends DebuffAction {
    public FilEntravant() { super("Fil Entravant"); }
    @Override
    public void executer(Animal user, Animal target) {
        target.applyStage("speed", -2);
        target.applyStage("accuracy", -1); // si tu gères accuracy stage
        System.out.println(user.getNom() + " lance un Fil Entravant : l'ennemi est ralenti.");
    }
}
