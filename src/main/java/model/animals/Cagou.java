package model.animals;

import model.Animal;
import model.Type;
import model.Etat;

public class Cagou extends Animal {
    public Cagou() {
        super("Cagou", 100, 15, 10, 8, Type.TERRESTRE);
    }

    @Override
    public void actionSpeciale(Animal cible) {
        System.out.println("Le Cagou pousse un cri étourdissant !");
        cible.setEtat(Etat.DEFENSE);
        cible.setDefense(Math.max(0, cible.getDefense() - 3));
    }
}