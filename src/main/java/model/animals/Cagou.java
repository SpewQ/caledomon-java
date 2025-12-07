package model.animals;

import java.util.List;

import model.Animal;
import model.Type;
import model.actions.cagou.CoupDeBec;
import model.actions.cagou.CriAlerte;
import model.actions.cagou.DanseDuSol;
import model.actions.cagou.SautDeBrousse;

public class Cagou extends Animal {
    public Cagou() {
        super("Cagou", 100, 36, 16, 12, Type.AERIEN);
        this.actions = List.of(
            new CoupDeBec(),
            new SautDeBrousse(), 
            new CriAlerte(), 
            new DanseDuSol());
    }

    @Override
    public void actionSpeciale(Animal cible) { /* ... */ }

    @Override
    public Animal copy() {
        Cagou clone = new Cagou(); // constructeur remet tout à zéro
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
