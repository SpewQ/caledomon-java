/**
 * <p>
 * Classe <strong>CarapaceTropicale</strong>.
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
 *   <li>Centraliser la logique associée à CarapaceTropicale</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions.tortue;

import model.Animal;
import model.actions.BuffAction;

public class CarapaceTropicale extends BuffAction {
    public CarapaceTropicale() { super("Carapace Tropicale"); }

    @Override
    /**
     * Méthode publique pour exécuter CarapaceTropicale sur soi et l'ennemi
     * qui applique +1 de défense sur soi et -1 d'attaque sur l'ennemi.
     * @param user : Tortue exécutant l'action
     * @param target : CalédoMon ennemi
     */
    public void executer(Animal user, Animal target) {
        user.applyStage("defense", +1);
        target.applyStage("attack", -1);
        System.out.println(user.getNom() + " se protège et affaiblit l'ennemi !");
    }
}
