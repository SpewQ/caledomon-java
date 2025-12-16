/**
 * <p>
 * Classe <strong>NuageToxique</strong>.
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
 *   <li>Centraliser la logique associée à NuageToxique</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions.roussette;

import model.Animal;
import model.actions.DebuffAction;

public class NuageToxique extends DebuffAction {
    public NuageToxique() { super("Nuage Toxique"); }

    @Override
    /**
     * Méthode publique pour exécuter NuageToxique sur l'ennemi
     * qui a 85% de chances d'empoisonner l'ennemi.
     * @param user : Roussette exécutant l'action
     * @param target : CalédoMon ennemi
     */
    public void executer(Animal user, Animal target) {
        if (Math.random() * 100 > 85) {
            System.out.println(user.getNom() + " rate Nuage Toxique !");
            return;
        }
        target.applyStatusPoison();
        System.out.println(target.getNom() + " est empoisonné par Nuage Toxique !");
    }
}
