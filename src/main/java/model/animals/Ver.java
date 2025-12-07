package model.animals;

import java.util.List;

import model.Animal;
import model.Type;
import model.actions.ver.*;

public class Ver extends Animal {

    public Ver() {
        super("Ver", 70, 15, 10, 18, Type.TERRESTRE);
        this.actions = List.of(
            new AcideDigestif(),
            new FilEntravant(),
            new MorsureMandibule(), 
            new MueProtectrice()
        );
    }

    @Override
    public void actionSpeciale(Animal cible) { }

    @Override
    public Animal copy() {
        Ver clone = new Ver(); // constructeur remet tout à zéro
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
