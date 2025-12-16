/**
 * <p>
 * Classe <strong>Nocturnisation</strong>.
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
 *   <li>Centraliser la logique associée à Nocturnisation</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions.roussette;

import model.Animal;
import model.actions.BuffAction;

public class Nocturnisation extends BuffAction {
    public Nocturnisation() { super("Nocturnisation"); }

    @Override
    /**
     * Méthode publique pour exécuter Nocturnisation sur soi
     * qui applique +2 de vitesse.
     * @param attaquant : Roussette exécutant l'action
     * @param cible : CalédoMon ennemi
     */
    public void executer(Animal user, Animal target) {
        user.applyStage("speed", +2);
        System.out.println(user.getNom() + " utilise Nocturnisation : vitesse augmentée.");
    }
}
