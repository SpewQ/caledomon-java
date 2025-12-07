package model.actions.tortue;

import model.Animal;
import model.actions.BuffAction;

public class CarapaceTropicale extends BuffAction {
    public CarapaceTropicale() { super("Carapace Tropicale"); }
    @Override
    public void executer(Animal user, Animal target) {
        user.applyStage("defense", +1);
        target.applyStage("attack", -1);
        System.out.println(user.getNom() + " se protège et affaiblit l'ennemi !");
    }
}
