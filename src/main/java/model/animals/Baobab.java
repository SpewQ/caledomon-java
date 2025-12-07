package model.animals;

import java.util.List;
import model.Animal;
import model.Type;
import model.actions.baobab.*;

public class Baobab extends Animal {

    public Baobab() {
        super("Baobab", 130, 25, 30, 8, Type.TERRESTRE);
        this.actions = List.of(
            new ChantDuVent(),
            new Enracinement(),
            new FrappeTronc(), 
            new PulverisationSeve()
        );
    }

    @Override
    public void actionSpeciale(Animal cible) { }

    @Override
    public Animal copy() {
        Baobab clone = new Baobab(); // constructeur remet tout à zéro
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
