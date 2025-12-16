/**
 * <p>
 * Classe <strong>SouffleLunaire</strong>.
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
 *   <li>Centraliser la logique associée à SouffleLunaire</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions.notou;

import model.Animal;
import model.actions.Action;

public class SouffleLunaire extends Action {
    public SouffleLunaire() { super(18, 100); }

    @Override
    /**
     * Méthode publique pour exécuter SouffleLunaire sur l'ennemi
     * qui inflige des dégâts et a 20% de chances d'appliquer -1 d'attaque à l'ennemi.
     * @param attaquant : Notou exécutant l'action
     * @param cible : CalédoMon ennemi
     */
    public void executer(Animal attaquant, Animal cible) {
        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Souffle Lunaire !");
            return;
        }
        applyStandardDamage(attaquant, cible, 0, "Souffle Lunaire");
        if (Math.random() < 0.20) {
            cible.applyStage("attack", -1);
            System.out.println(cible.getNom() + " voit son attaque diminuer !");
        }
    }
}
