package model.animals;

import java.util.List;

import model.Animal;
import model.Type;
import model.actions.roussette.*;

public class Roussette extends Animal {

    public Roussette() {
        super("Roussette", 90, 23, 16, 23, Type.AERIEN);
        this.actions = List.of(
            new MorsureSanguine(),
            new Nocturnisation(),
            new NuageToxique(), 
            new OndeSonique()
        );
    }

    @Override
    public void actionSpeciale(Animal cible) { }

    @Override
    public Animal copy() {
        Roussette clone = new Roussette(); // constructeur remet tout à zéro
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
