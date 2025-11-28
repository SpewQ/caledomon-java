package model.factories;

import model.Animal;
import model.animals.Cagou;
import model.animals.Gecko;
import model.animals.TricotRaye;

public class AnimalFactory {
    public static Animal createAnimal(String nom) {
        if (nom == null) throw new IllegalArgumentException("Nom null");
        switch (nom.trim().toLowerCase()) {
            case "cagou": {
                Cagou a = new Cagou();
                return a;
            }
            case "gecko": {
                Gecko a = new Gecko();
                return a;
            }
            case "tricot rayé":
            case "tricotraye":
            case "tricot raye": {
                TricotRaye a = new TricotRaye();
                return a;
            }
            default:
                throw new IllegalArgumentException("Animal inconnu : " + nom);
        }
    }
}
