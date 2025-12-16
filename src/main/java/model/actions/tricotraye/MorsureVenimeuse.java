/**
 * <p>
 * Classe <strong>MorsureVenimeuse</strong>.
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
 *   <li>Centraliser la logique associée à MorsureVenimeuse</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions.tricotraye;

import model.Animal;
import model.actions.Action;

public class MorsureVenimeuse extends Action {

    public MorsureVenimeuse() {
        super(18, 95); 
    } // power ~18, précision 95%

    @Override
    /**
     * Méthode publique pour exécuter MorsureVenimeuse sur l'ennemi
     * qui inflige des dégâts et a 30% de chances d'empoisonner l'ennemi.
     * @param attaquant : Tricot Rayé exécutant l'action
     * @param cible : CalédoMon ennemi
     */
    public void executer(Animal attaquant, Animal cible) {

        // précision
        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Morsure Venimeuse !");
            return;
        }

        // Dégâts standardisés (pas de bonus : +0)
        applyStandardDamage(attaquant, cible, 0, "Morsure Venimeuse");

        // 30% de chance d'empoisonner
        if (Math.random() < 0.30) {
            // applique poison (si non déjà empoisonné)
            cible.applyStatusPoison();
        }
    }
}
