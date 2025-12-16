/**
 * <p>
 * Classe <strong>DanseDuSol</strong>.
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
 *   <li>Centraliser la logique associée à DanseDuSol</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions.cagou;

import model.Animal;
import model.actions.BuffAction;

public class DanseDuSol extends BuffAction {
    
    public DanseDuSol() { 
        super("Danse du Sol");
    }

    @Override
    /**
     * Méthode publique pour exécuter DanseDuSol sur soi
     * qui applique +1 défense et vitesse.
     * @param user : Cagou exécutant l'action
     * @param target : CalédoMon ennemi
     */
    public void executer(Animal user, Animal target) {
        // cible peut être user pour self-buff
        user.applyStage("defense", +1);
        user.applyStage("speed", +1);
        System.out.println(user.getNom() + " utilise Danse du Sol ! Défense et vitesse augmentées.");
    }
}
