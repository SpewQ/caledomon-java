/**
 * <p>
 * Classe <strong>BruitDeSabots</strong>.
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
 *   <li>Centraliser la logique associée à BruitDeSabots</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions.cerf;

import model.Animal;
import model.actions.Action;

public class BruitDeSabots extends Action {
    public BruitDeSabots() { super(10, 90); } // multi-hit handled here as two hits

    @Override
    /**
     * Méthode publique pour exécuter BruitDeSabots sur l'ennemi
     * qui inflige des dégâts 2 fois.
     * @param attaquant : Cerf exécutant l'action
     * @param cible : CalédoMon ennemi
     */
    public void executer(Animal attaquant, Animal cible) {
        int hits = 2;
        for (int i = 0; i < hits; i++) {
            if (Math.random() * 100 > this.accuracy) {
                System.out.println(attaquant.getNom() + " rate un coup de Bruit de Sabots !");
            } else {
                applyStandardDamage(attaquant, cible, 0, "Bruit de Sabots (coup)");
            }
        }
    }
}
