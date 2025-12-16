/**
 * <p>
 * Classe <strong>EcaillesAcerees</strong>.
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
 *   <li>Centraliser la logique associée à EcaillesAcerees</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions.picot;

import model.Animal;
import model.actions.BuffAction;

public class EcaillesAcerees extends BuffAction {
    public EcaillesAcerees() { super("Ecailles acérées"); }

    @Override
    /**
     * Méthode publique pour exécuter EcaillesAcerees sur soi
     * qui applique +2 d'attaque et +1 de vitesse.
     * @param user : Picot exécutant l'action
     * @param target : CalédoMon ennemi
     */
    public void executer(Animal user, Animal target) {
        user.applyStage("attack", +2);
        user.applyStage("speed", +1);
        System.out.println(user.getNom() + " utilise Ecailles acérées : attaque et vitesse augmentées.");
    }
}
