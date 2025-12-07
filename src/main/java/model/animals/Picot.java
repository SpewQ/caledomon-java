package model.animals;

import java.util.List;

import model.Animal;
import model.Type;
import model.actions.picot.*;

public class Picot extends Animal {

    public Picot() {
        super("Picot", 80, 24, 14, 28, Type.MARIN);
        this.actions = List.of(
            new ChantDeconcertant(),
            new EcaillesAcerees(),
            new PicPicFurie(), 
            new SifflementStrident()
        );
    }

    @Override
    public void actionSpeciale(Animal cible) { }

    @Override
    public Animal copy() {
        Picot clone = new Picot(); // constructeur remet tout à zéro
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
