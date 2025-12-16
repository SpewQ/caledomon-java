/**
 * <p>
 * Classe <strong>Dawa</strong>.
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
 *   <li>Centraliser la logique associée à Dawa</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.animals;

import java.util.List;

import model.Animal;
import model.Type;
import model.actions.dawa.*;

public class Dawa extends Animal {

    /**
     * Constructeur de Dawa avec les paramètres de points de vie, attaque, défense, vitesse et type)
     */
    public Dawa() {
        super("Dawa", 95, 26, 17, 18, Type.MARIN);
        this.actions = List.of(
            new BrouillardMarin(),
            new ClaquementDeQueue(),
            new DanseAquatique(), 
            new JetDEauPressurise()
        );
    }

    @Override
    public void actionSpeciale(Animal cible) { }

    @Override
    public Animal copy() {
        Dawa clone = new Dawa(); // constructeur remet tout à zéro
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
