package model.animals;

import java.util.List;

import model.Animal;
import model.Type;
import model.actions.dawa.*;

public class Dawa extends Animal {

    public Dawa() {
        super("Dawa", 95, 26, 17, 18, Type.MARIN);
        this.actions = List.of(
            new BrouillardMarin(),
            new ClaquementDeQueue(),
            new DanseAquatique(), 
            new JetDEauPressurise()
        );
    }

    @Override
    public void actionSpeciale(Animal cible) { }

    @Override
    public Animal copy() {
        Dawa clone = new Dawa(); // constructeur remet tout à zéro
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
