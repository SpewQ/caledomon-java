/**
 * <p>
 * Classe <strong>BrouillardMarin</strong>.
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
 *   <li>Centraliser la logique associée à BrouillardMarin</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions.dawa;

import model.Animal;
import model.actions.DebuffAction;

public class BrouillardMarin extends DebuffAction {
    public BrouillardMarin() { super("Brouillard Marin"); }

    @Override
    /**
     * Méthode publique pour exécuter BrouillardMarin sur l'ennemi
     * qui applique -1 vitesse.
     * @param attaquant : Dawa exécutant l'action
     * @param cible : CalédoMon ennemi
     */
    public void executer(Animal user, Animal target) {
        target.applyStage("speed", -1); 
        System.out.println(user.getNom() + " utilise Brouillard Marin : vitesse ennemie réduite.");
    }
}
