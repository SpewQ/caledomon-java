/**
 * <p>
 * Classe <strong>ContractionSerpentine</strong>.
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
 *   <li>Centraliser la logique associée à ContractionSerpentine</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions.tricotraye;

import model.Animal;
import model.actions.Action;

public class ContractionSerpentine extends Action {

    public ContractionSerpentine() {
        super(10, 100);
    }

    @Override
    /**
     * Méthode publique pour exécuter ContractionSerpentine sur l'ennemi
     * qui inflige des dégâts et applique -1 de vitesse à l'ennemi.
     * @param attaquant : Tricot Rayé exécutant l'action
     * @param cible : CalédoMon ennemi
     */
    public void executer(Animal attaquant, Animal cible) {

        // 1) Dégâts standardisés avec malus -3
        //    (attaque - défense - 3) × multiplicateur de type
        applyStandardDamage(attaquant, cible, -3, "Contraction Serpentine");

        // 2) Effet spécial : réduire la vitesse de la cible
        cible.setVitesse(Math.max(1, cible.getVitesse() - 2));
        System.out.println(cible.getNom() + " voit sa vitesse réduite !");
    }
}
