package model.actions.cerf;

import model.Animal;
import model.actions.DebuffAction;

public class RegardTerrifiant extends DebuffAction {
    public RegardTerrifiant() { super("Regard Terrifiant"); }
    @Override
    public void executer(Animal user, Animal target) {
        target.applyStage("attack", -1);
        target.applyStage("speed", -1);
        System.out.println(user.getNom() + " utilise Regard Terrifiant : stats ennemies diminuées.");
    }
}
