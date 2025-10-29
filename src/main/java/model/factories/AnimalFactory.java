package model.factories;

import model.Animal;
import model.animals.Cagou;
import model.animals.Gecko;
import model.animals.TricotRaye;

public class AnimalFactory {
    public static Animal createAnimal(String nom) {
        if (nom == null) throw new IllegalArgumentException("Nom null");
        switch (nom.trim().toLowerCase()) {
            case "cagou": return new Cagou();
            case "gecko": return new Gecko();
            case "tricot rayé":
            case "tricotraye":
            case "tricot raye":
                return new TricotRaye();
            default: throw new IllegalArgumentException("Animal inconnu : " + nom);
        }
    }
}