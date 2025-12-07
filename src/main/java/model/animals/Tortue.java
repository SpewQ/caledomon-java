package model.animals;

import java.util.List;

import model.Animal;
import model.Type;
import model.actions.tortue.*;

public class Tortue extends Animal {

    public Tortue() {
        super("Tortue", 140, 20, 35, 6, Type.MARIN);
        this.actions = List.of(
            new ArmureMinerale(),
            new CarapaceTropicale(),
            new CoupDeCarapace(), 
            new JetDeBoue()
        );
    }

    @Override
    public void actionSpeciale(Animal cible) { }

    @Override
    public Animal copy() {
        Tortue clone = new Tortue(); // constructeur remet tout à zéro
        // s'assurer que aucun status/stage n'est copié
        clone.attackStage = 0;
        clone.defenseStage = 0;
        clone.speedStage = 0;
        clone.poisoned = false;
        clone.paralyzed = false;
        clone.setPv(clone.getMaxPv());
        return clone;
    }
}
