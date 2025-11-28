package model.animals;

import java.util.List;

import model.Animal;
import model.Etat;
import model.Type;
import model.actions.cagou.CoupDeBec;
import model.actions.cagou.CriAlerte;
import model.actions.cagou.DanseDuSol;
import model.actions.cagou.SautDeBrousse;

/**
 * model/animals/Cagou.java
 */
public class Cagou extends Animal {

    public Cagou() {
        super("Cagou", 100, 15, 10, 8, Type.TERRESTRE);

        // initialise la liste d'actions pour cet Animal
        this.actions = List.of(
            new CoupDeBec(),
            new CriAlerte(),
            new SautDeBrousse(),
            new DanseDuSol()
        );
    }

    // On peut garder un getter si besoin, hérité par Animal.getActions()

    @Override
    public void actionSpeciale(Animal cible) {
        System.out.println("Le Cagou pousse un cri étourdissant !");
        cible.setEtat(Etat.DEFENSE);
        cible.setDefense(Math.max(0, cible.getDefense() - 3));
    }

    @Override
    public Animal copy() {
        Cagou clone = new Cagou();

        // copie des actions (shallow copy, suffisant si les actions sont stateless)
        clone.setActions(this.actions);

        return clone;
    }
}
