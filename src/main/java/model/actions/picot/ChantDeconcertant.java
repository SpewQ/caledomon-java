/**
 * <p>
 * Classe <strong>ChantDeconcertant</strong>.
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
 *   <li>Centraliser la logique associée à ChantDeconcertant</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions.picot;

import model.Animal;
import model.actions.DebuffAction;

public class ChantDeconcertant extends DebuffAction {
    public ChantDeconcertant() { super("Chant Déconcertant"); }

    @Override
    /**
     * Méthode publique pour exécuter ChantDeconcertant sur l'ennemi
     * qui applique -1 d'attaque et de défense.
     * @param user : Picot exécutant l'action
     * @param target : CalédoMon ennemi
     */
    public void executer(Animal user, Animal target) {
        target.applyStage("attack", -1);
        target.applyStage("defense", -1);
        System.out.println(user.getNom() + " utilise Chant Déconcertant : l'ennemi est affaibli.");
    }
}
