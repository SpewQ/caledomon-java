/**
 * <p>
 * Classe <strong>ClaquementDeQueue</strong>.
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
 *   <li>Centraliser la logique associée à ClaquementDeQueue</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions.dawa;

import model.Animal;
import model.actions.Action;

public class ClaquementDeQueue extends Action {
    public ClaquementDeQueue() { super(16, 100); }
    
    @Override
    /**
     * Méthode publique pour exécuter ClaquementDeQueue sur l'ennemi
     * qui inflige des dégâts.
     * @param attaquant : Dawa exécutant l'action
     * @param cible : CalédoMon ennemi
     */
    public void executer(Animal attaquant, Animal cible) {
        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Claquement de Queue !");
            return;
        }
        applyStandardDamage(attaquant, cible, 0, "Claquement de Queue");
    }
}
