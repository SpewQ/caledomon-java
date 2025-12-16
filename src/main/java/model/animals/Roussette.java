/**
 * <p>
 * Classe <strong>Roussette</strong>.
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
 *   <li>Centraliser la logique associée à Roussette</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.animals;

import java.util.List;

import model.Animal;
import model.Type;
import model.actions.roussette.*;

public class Roussette extends Animal {

    /**
     * Constructeur de Roussette avec les paramètres de points de vie, attaque, défense, vitesse et type)
     */
    public Roussette() {
        super("Roussette", 90, 23, 16, 23, Type.AERIEN);
        this.actions = List.of(
            new MorsureSanguine(),
            new Nocturnisation(),
            new NuageToxique(), 
            new OndeSonique()
        );
    }

    @Override
    public void actionSpeciale(Animal cible) { }

    @Override
    public Animal copy() {
        Roussette clone = new Roussette(); // constructeur remet tout à zéro
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
