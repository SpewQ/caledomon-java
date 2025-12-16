/**
 * <p>
 * Classe <strong>FilEntravant</strong>.
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
 *   <li>Centraliser la logique associée à FilEntravant</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions.ver;

import model.Animal;
import model.actions.DebuffAction;

public class FilEntravant extends DebuffAction {
    public FilEntravant() { super("Fil Entravant"); }

    @Override
    /**
     * Méthode publique pour exécuter FilEntravant sur l'ennemi
     * qui applique -2 de vitesse.
     * @param user : Ver exécutant l'action
     * @param target : CalédoMon ennemi
     */
    public void executer(Animal user, Animal target) {
        target.applyStage("speed", -2);
        System.out.println(user.getNom() + " lance un Fil Entravant : l'ennemi est ralenti.");
    }
}
