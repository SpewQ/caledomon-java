/**
 * <p>
 * Classe <strong>Cerf</strong>.
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
 *   <li>Centraliser la logique associée à Cerf</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.animals;

import java.util.List;

import model.Animal;
import model.Type;
import model.actions.cerf.*;

public class Cerf extends Animal {
    
    /**
     * Constructeur de Cerf avec les paramètres de points de vie, attaque, défense, vitesse et type)
     */
    public Cerf() {
        super("Cerf", 100, 28, 18, 16, Type.TERRESTRE);
        this.actions = List.of(
            new BruitDeSabots(),
            new ChargeCornee(),
            new NoblesseBestiale(), 
            new RegardTerrifiant()
        );
    }

    @Override
    public void actionSpeciale(Animal cible) { }

    @Override
    public Animal copy() {
        Cerf clone = new Cerf(); // constructeur remet tout à zéro
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
