/**
 * <p>
 * Classe <strong>ChargeCornee</strong>.
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
 *   <li>Centraliser la logique associée à ChargeCornee</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions.cerf;

import model.Animal;
import model.actions.Action;

public class ChargeCornee extends Action {
    public ChargeCornee() { super(24, 95); }

    @Override
    /**
     * Méthode publique pour exécuter ChargeCornee sur l'ennemi
     * qui inflige des dégâts et a 20% de chances d'appliquer -1 défense à l'ennemi.
     * @param attaquant : Cerf exécutant l'action
     * @param cible : CalédoMon ennemi
     */
    public void executer(Animal attaquant, Animal cible) {
        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Charge Cornée !");
            return;
        }
        applyStandardDamage(attaquant, cible, 0, "Charge Cornée");
        if (Math.random() < 0.20) {
            cible.applyStage("defense", -1);
            System.out.println(cible.getNom() + " voit sa défense baisser !");
        }
    }
}
