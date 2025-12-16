/**
 * <p>
 * Classe <strong>HealAction</strong>.
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
 *   <li>Centraliser la logique associée à HealAction</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions;

import model.Animal;

public class HealAction extends BuffAction {

    public HealAction() { 
        super("HealAction");
    }

    @Override
    public void executer(Animal attaquant, Animal cible) {
        attaquant.soigner();
    }
}