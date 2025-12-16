/**
 * <p>
 * Classe <strong>NoblesseBestiale</strong>.
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
 *   <li>Centraliser la logique associée à NoblesseBestiale</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions.cerf;

import model.Animal;
import model.actions.BuffAction;

public class NoblesseBestiale extends BuffAction {
    public NoblesseBestiale() { super("Noblesse Bestiale"); }

    @Override
    /**
     * Méthode publique pour exécuter NoblesseBestiale sur soi
     * qui applique +1 attaque et défense.
     * @param attaquant : Cerf exécutant l'action
     * @param cible : CalédoMon ennemi
     */
    public void executer(Animal user, Animal target) {
        user.applyStage("attack", +1);
        user.applyStage("defense", +1);
        System.out.println(user.getNom() + " utilise Noblesse Bestiale : attaque et défense augmentées.");
    }
}
