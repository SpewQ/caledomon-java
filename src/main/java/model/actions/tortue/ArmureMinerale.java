/**
 * <p>
 * Classe <strong>ArmureMinerale</strong>.
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
 *   <li>Centraliser la logique associée à ArmureMinerale</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions.tortue;

import model.Animal;
import model.actions.BuffAction;

public class ArmureMinerale extends BuffAction {
    public ArmureMinerale() { super("Armure Minérale"); }

    @Override
    /**
     * Méthode publique pour exécuter ArmureMinerale sur soi
     * qui applique +2 de défense.
     * @param user : Tortue exécutant l'action
     * @param target : CalédoMon ennemi
     */
    public void executer(Animal user, Animal target) {
        user.applyStage("defense", +2);
        System.out.println(user.getNom() + " renforce sa carapace !");
    }
}
