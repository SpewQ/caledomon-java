/**
 * <p>
 * Classe <strong>RegardTerrifiant</strong>.
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
 *   <li>Centraliser la logique associée à RegardTerrifiant</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions.cerf;

import model.Animal;
import model.actions.DebuffAction;

public class RegardTerrifiant extends DebuffAction {
    public RegardTerrifiant() { super("Regard Terrifiant"); }

    @Override
    /**
     * Méthode publique pour exécuter RegardTerrifiant sur l'ennemi
     * qui applique -1 attaque et vitesse.
     * @param attaquant : Cerf exécutant l'action
     * @param cible : CalédoMon ennemi
     */
    public void executer(Animal user, Animal target) {
        target.applyStage("attack", -1);
        target.applyStage("speed", -1);
        System.out.println(user.getNom() + " utilise Regard Terrifiant : stats ennemies diminuées.");
    }
}
