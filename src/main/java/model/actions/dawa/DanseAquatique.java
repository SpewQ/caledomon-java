/**
 * <p>
 * Classe <strong>DanseAquatique</strong>.
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
 *   <li>Centraliser la logique associée à DanseAquatique</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions.dawa;

import model.Animal;
import model.actions.BuffAction;

public class DanseAquatique extends BuffAction {
    public DanseAquatique() { super("Danse Aquatique"); }

    @Override
    /**
     * Méthode publique pour exécuter DanseAquatique sur soi
     * qui applique +2 vitesse.
     * @param user : Dawa exécutant l'action
     * @param target : CalédoMon ennemi
     */
    public void executer(Animal user, Animal target) {
        user.applyStage("speed", +2);
        System.out.println(user.getNom() + " utilise Danse Aquatique : vitesse augmentée.");
    }
}
