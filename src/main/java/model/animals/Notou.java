package model.animals;

import java.util.List;

import model.Animal;
import model.Type;
import model.actions.notou.*;

public class Notou extends Animal {

    public Notou() {
        super("Notou", 110, 32, 20, 10, Type.AERIEN);
        this.actions = List.of(
            new CriObscur(),
            new PlumesSombres(),
            new SerreDeNuit(), 
            new SouffleLunaire()
        );
    }

    @Override
    public void actionSpeciale(Animal cible) { }

    @Override
    public Animal copy() {
        Notou clone = new Notou(); // constructeur remet tout à zéro
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
