/**
 * <p>
 * Classe <strong>Enracinement</strong>.
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
 *   <li>Centraliser la logique associée à Enracinement</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions.baobab;

import model.Animal;
import model.actions.BuffAction;

public class Enracinement extends BuffAction {
    public Enracinement() { super("Enracinement"); }
    
    @Override
    /**
     * Méthode publique pour exécuter Enracinement sur soi
     * qui applique +2 défense et un petit soin de 5% des PVs max
     * @param user : Baobab exécutant l'action
     * @param target : CalédoMon ennemi
     */
    public void executer(Animal user, Animal target) {
        // Défense +2 (stages), petit soin = +5% maxPV (arrondi)
        user.applyStage("defense", +2);
        int heal = Math.max(1, user.getMaxPv() * 5 / 100);
        user.setPv(user.getPv() + heal);
        System.out.println(user.getNom() + " utilise Enracinement : Défense augmentée et récupère " + heal + " PV.");
    }
}
