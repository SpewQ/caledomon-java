/**
 * <p>
 * Classe <strong>CriAlerte</strong>.
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
 *   <li>Centraliser la logique associée à CriAlerte</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions.cagou;

import model.Animal;
import model.actions.DebuffAction;

public class CriAlerte extends DebuffAction {

    public CriAlerte() {
        super("Cri d'Alerte"); // pas de damage, juste effet de stage
    }

    @Override
    /**
     * Méthode publique pour exécuter CriAlerte sur l'ennemi
     * qui applique -1 attaque.
     * @param user : Cagou exécutant l'action
     * @param target : CalédoMon ennemi
     */
    public void executer(Animal user, Animal target) {
        // réduit d'un stage l'attaque cible
        target.applyStage("attack", -1);
        System.out.println(user.getNom() + " utilise Cri d'alerte ! L'attaque de " + target.getNom() + " baisse.");
    }
}
