/**
 * <p>
 * Classe <strong>VeninCamoufle</strong>.
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
 *   <li>Centraliser la logique associée à VeninCamoufle</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions.gecko;

import model.Animal;
import model.actions.Action;

public class VeninCamoufle extends Action {
    public VeninCamoufle() { super(12, 100); }
    
    @Override
    /**
     * Méthode publique pour exécuter VeninCamoufle sur soi
     * qui inflige des dégâts et a 50% de chances d'empoisonner l'ennemi.
     * @param attaquant : Gecko exécutant l'action
     * @param cible : CalédoMon ennemi
     */
    public void executer(Animal attaquant, Animal cible) {
        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Venin Camouflé !");
            return;
        }
        applyStandardDamage(attaquant, cible, 0, "Venin Camouflé");
        if (Math.random() < 0.50) {
            cible.applyStatusPoison();
            System.out.println(cible.getNom() + " est empoisonné !");
        }
    }
}
