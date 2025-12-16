/**
 * <p>
 * Classe <strong>OndeSonique</strong>.
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
 *   <li>Centraliser la logique associée à OndeSonique</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions.roussette;

import model.Animal;
import model.actions.Action;

public class OndeSonique extends Action {
    public OndeSonique() { super(20, 90); }

    @Override
    /**
     * Méthode publique pour exécuter OndeSonique sur l'ennemi
     * qui inflige des dégâts et a 20% de chances d'appliquer -1 d'attaque à l'ennemi.
     * @param attaquant : Roussette exécutant l'action
     * @param cible : CalédoMon ennemi
     */
    public void executer(Animal attaquant, Animal cible) {
        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Onde Sonique' !");
            return;
        }
        applyStandardDamage(attaquant, cible, 0, "Onde Soniq'");
        if (Math.random() < 0.20) {
            cible.applyStage("attack", -1);
            System.out.println(cible.getNom() + " voit son attaque baisser !");
        }
    }
}
