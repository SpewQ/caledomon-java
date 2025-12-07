package model.animals;

import java.util.List;

import model.Animal;
import model.Type;
import model.actions.tricotraye.ContractionSerpentine;
import model.actions.tricotraye.EcailleToxique;
import model.actions.tricotraye.MorsureVenimeuse;
import model.actions.tricotraye.SouffleDraconique;

/**
 * model/animals/TricotRaye.java
 */
public class TricotRaye extends Animal {

    public TricotRaye() {
        super("TricotRaye", 85, 19, 21, 14, Type.MARIN);

        // initialise la liste d'actions pour cet Animal
        this.actions = List.of(
            new ContractionSerpentine(),
            new EcailleToxique(),
            new MorsureVenimeuse(),
            new SouffleDraconique()
        );
    }

    @Override
    public void actionSpeciale(Animal cible) {
        // si tu veux un sort signature, le définir ici
    }

    @Override
    public Animal copy() {
        TricotRaye t = new TricotRaye();
        t.attackStage = 0;
        t.defenseStage = 0;
        t.speedStage = 0;
        t.poisoned = false;
        t.paralyzed = false;
        t.setPv(t.getMaxPv());
        return t;
    }
}
