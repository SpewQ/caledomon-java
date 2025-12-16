/**
 * <p>
 * Classe <strong>ChantDuVent</strong>.
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
 *   <li>Centraliser la logique associée à ChantDuVent</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions.baobab;

import model.Animal;
import model.actions.DebuffAction;

public class ChantDuVent extends DebuffAction {
    public ChantDuVent() { super("Chant du Vent"); }

    @Override
    /**
     * Méthode publique pour exécuter ChantDuVent sur l'ennemi
     * qui applique -1 attaque et vitesse.
     * @param user : Baobab exécutant l'action
     * @param target : CalédoMon ennemi
     */
    public void executer(Animal user, Animal target) {
        target.applyStage("speed", -1);
        target.applyStage("attack", -1);
        System.out.println(user.getNom() + " utilise Chant du Vent : vitesse et attaque ennemies diminuées.");
    }
}
