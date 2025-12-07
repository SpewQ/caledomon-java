package model.actions.ver;

import model.Animal;
import model.actions.BuffAction;

public class MueProtectrice extends BuffAction {
    public MueProtectrice() { super("Mue Protectrice"); }
    @Override
    public void executer(Animal user, Animal target) {
        user.applyStage("defense", +2);
        user.applyStage("speed", +1);
        System.out.println(user.getNom() + " mute et gagne en défense/vitesse !");
    }
}
