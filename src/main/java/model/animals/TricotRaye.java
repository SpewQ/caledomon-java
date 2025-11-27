package model.animals;

import model.Animal;
import model.Etat;
import model.Type;

public class TricotRaye extends Animal {
    public TricotRaye() {
        super("Tricot Rayé", 80, 18, 6, 10, Type.MARIN);

    }

    @Override
    public void actionSpeciale(Animal cible) {
        System.out.println("Le Tricot Rayé mord et empoisonne " + cible.getNom() + " !");
        cible.setEtat(Etat.EMPOISONNE);
    }
}