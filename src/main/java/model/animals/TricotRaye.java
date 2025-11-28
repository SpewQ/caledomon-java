package model.animals;

import java.util.List;

import model.Animal;
import model.Etat;
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
        super("TricotRaye", 80, 12, 10, 14, Type.MARIN);

        // initialise la liste d'actions pour cet Animal
        this.actions = List.of(
            new ContractionSerpentine(),
            new EcailleToxique(),
            new MorsureVenimeuse(),
            new SouffleDraconique()
        );
    }

    // On peut garder un getter si besoin, hérité par Animal.getActions()

    @Override
    public void actionSpeciale(Animal cible) {
        System.out.println("Le Tricot Rayé mord et empoisonne " + cible.getNom() + " !");
        cible.setEtat(Etat.EMPOISONNE);
    }

    @Override
    public Animal copy() {
        TricotRaye clone = new TricotRaye();

        // copie des actions (shallow copy, suffisant si les actions sont stateless)
        clone.setActions(this.actions);

        return clone;
    }
}
