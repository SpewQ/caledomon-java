/**
 * <p>
 * Classe <strong>CoupDeBec</strong>.
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
 *   <li>Centraliser la logique associée à CoupDeBec</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions.cagou;

import model.Animal;
import model.actions.Action;

public class CoupDeBec extends Action {

    public CoupDeBec() {
        super(19, 100);
    }

    @Override
    /**
     * Méthode publique pour exécuter CoupDeBec sur l'ennemi
     * qui inflige des dégâts.
     * @param attaquant : Cagou exécutant l'action
     * @param cible : CalédoMon ennemi
     */
    public void executer(Animal attaquant, Animal cible) {

        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Coup de Bec !");
            return;
        }

        // pas de bonus, juste l'attaque classique
        applyStandardDamage(attaquant, cible, 0, "Coup de Bec");
    }
}
