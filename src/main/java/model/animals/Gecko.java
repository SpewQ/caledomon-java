/**
 * <p>
 * Classe <strong>Gecko</strong>.
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
 *   <li>Centraliser la logique associée à Gecko</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.animals;

import java.util.List;
import model.Animal;
import model.Type;
import model.actions.gecko.*;

public class Gecko extends Animal {

    /**
     * Constructeur de Gecko avec les paramètres de points de vie, attaque, défense, vitesse et type)
     */
    public Gecko() {
        super("Gecko", 85, 22, 12, 25, Type.TERRESTRE);
        this.actions = List.of(
            new Acceleration(),
            new LueurHypnotique(),
            new QueueFouetRapide(), 
            new VeninCamoufle()
        );
    }

    @Override
    public void actionSpeciale(Animal cible) { }

    @Override
    public Animal copy() {
        Gecko clone = new Gecko(); // constructeur remet tout à zéro
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
