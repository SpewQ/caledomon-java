package model.animals;

import java.util.List;

import model.Animal;
import model.Type;
import model.actions.cerf.*;

public class Cerf extends Animal {

    public Cerf() {
        super("Cerf", 100, 28, 18, 16, Type.TERRESTRE);
        this.actions = List.of(
            new BruitDeSabots(),
            new ChargeCornee(),
            new NoblesseBestiale(), 
            new RegardTerrifiant()
        );
    }

    @Override
    public void actionSpeciale(Animal cible) { }

    @Override
    public Animal copy() {
        Cerf clone = new Cerf(); // constructeur remet tout à zéro
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
