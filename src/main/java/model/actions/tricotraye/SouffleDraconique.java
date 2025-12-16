/**
 * <p>
 * Classe <strong>SouffleDraconique</strong>.
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
 *   <li>Centraliser la logique associée à SouffleDraconique</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions.tricotraye;

import model.Animal;
import model.actions.Action;

public class SouffleDraconique extends Action {

    public SouffleDraconique() {
        super(20, 90);
    } // plus puissant, moins précis

    @Override
    /**
     * Méthode publique pour exécuter SouffleDraconique sur l'ennemi
     * qui inflige des dégâts et a 30% de chances de paralyser l'ennemi.
     * @param attaquant : Tricot Rayé exécutant l'action
     * @param cible : CalédoMon ennemi
     */
    public void executer(Animal attaquant, Animal cible) {

        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Souffle Draconique !");
            return;
        }

        // Dégâts standards (attaque - défense + 5)
        applyStandardDamage(attaquant, cible, 5, "Souffle Draconique");

        // 20% de chance de paralyser
        // 30% chance de paralyser
        if (Math.random() < 0.30) {
            cible.applyStatusParalysis();
        }
    }
}
