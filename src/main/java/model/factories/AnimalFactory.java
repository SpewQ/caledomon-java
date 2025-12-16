/**
 * <p>
 * Classe <strong>AnimalFactory</strong>.
 * </p>
 *
 * <p>
 * Cette classe fait partie du cœur applicatif du projet et joue un rôle précis
 * dans l'architecture globale (MVC). Elle encapsule un comportement métier,
 * une logique de contrôle ou un composant d'interface selon son package.
 * </p>
 *
 * <p>
 * Les responsabilités principales de cette classe sont :
 * </p>
 * <ul>
 *   <li>Centraliser la logique associée à AnimalFactory</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.factories;

import model.Animal;
import model.animals.Baobab;
import model.animals.Cagou;
import model.animals.Cerf;
import model.animals.Dawa;
import model.animals.Gecko;
import model.animals.Notou;
import model.animals.Picot;
import model.animals.Roussette;
import model.animals.Tortue;
import model.animals.TricotRaye;
import model.animals.Ver;

/**
 * Classe publique AnimalFactory
 */
public class AnimalFactory {

    /**
     * Méthode publique statique createAnimal qui instancie un CalédoMon en fonction de son nom
     * @param nom : chaîne de caractères du nom du CalédoMon
     */
    public static Animal createAnimal(String nom) {
        if (nom == null) throw new IllegalArgumentException("Nom null");
        switch (nom.trim().toLowerCase()) {

            case "baobab": {
                Baobab a = new Baobab();
                return a;
            }

            case "cagou": {
                Cagou a = new Cagou();
                return a;
            }

            case "cerf": {
                Cerf a = new Cerf();
                return a;
            }
            
            case "dawa": {
                Dawa a = new Dawa();
                return a;
            }

            case "gecko": {
                Gecko a = new Gecko();
                return a;
            }

            case "notou": {
                Notou a = new Notou();
                return a;
            }

            case "picot": {
                Picot a = new Picot();
                return a;
            }

            case "roussette": {
                Roussette a = new Roussette();
                return a;
            }

            case "tortue": {
                Tortue a = new Tortue();
                return a;
            }

            case "tricot rayé":
            case "tricotraye":
            case "tricot raye": {
                TricotRaye a = new TricotRaye();
                return a;
            }

            case "ver": {
                Ver a = new Ver();
                return a;
            }
            default:
                throw new IllegalArgumentException("Animal inconnu : " + nom);
        }
    }
}
