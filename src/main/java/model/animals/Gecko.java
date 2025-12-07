package model.animals;

import java.util.List;
import model.Animal;
import model.Type;
import model.actions.gecko.*;

public class Gecko extends Animal {

    public Gecko() {
        super("Gecko", 85, 22, 12, 25, Type.TERRESTRE);
        this.actions = List.of(
            new Acceleration(),
            new LueurHypnotique(),
            new QueueFouetRapide(), 
            new VeninCamoufle()
        );
    }

    @Override
    public void actionSpeciale(Animal cible) { }

    @Override
    public Animal copy() {
        Gecko clone = new Gecko(); // constructeur remet tout à zéro
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
