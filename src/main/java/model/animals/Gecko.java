package model.animals;

import model.Animal;
import model.Type;

public class Gecko extends Animal {
    public Gecko() {
        super("Gecko", 90, 12, 8, 12, Type.TERRESTRE);
    }

    @Override
    public void actionSpeciale(Animal cible) {
        System.out.println("Le Gecko se camoufle et augmente sa défense !");
        this.setDefense(this.getDefense() + 4);
    }

    @Override
    public Animal copy() {
        Gecko clone = new Gecko();

        // copie des actions (shallow copy, suffisant si les actions sont stateless)
        clone.setActions(this.actions);

        return clone;
    }
}