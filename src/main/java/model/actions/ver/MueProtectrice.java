/**
 * <p>
 * Classe <strong>MueProtectrice</strong>.
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
 *   <li>Centraliser la logique associée à MueProtectrice</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions.ver;

import model.Animal;
import model.actions.BuffAction;

public class MueProtectrice extends BuffAction {
    public MueProtectrice() { super("Mue Protectrice"); }

    @Override
    /**
     * Méthode publique pour exécuter MueProtectrice sur soi
     * qui applique +2 de défense et +1 de vitesse.
     * @param user : Ver exécutant l'action
     * @param target : CalédoMon ennemi
     */
    public void executer(Animal user, Animal target) {
        user.applyStage("defense", +2);
        user.applyStage("speed", +1);
        System.out.println(user.getNom() + " mute et gagne en défense/vitesse !");
    }
}
