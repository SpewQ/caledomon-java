/**
 * <p>
 * Classe <strong>FrappeTronc</strong>.
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
 *   <li>Centraliser la logique associée à FrappeTronc</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions.baobab;

import model.Animal;
import model.actions.Action;

public class FrappeTronc extends Action {
    public FrappeTronc() { super(22, 90); }

    @Override
    /**
     * Méthode publique pour exécuter FrappeTronc sur l'ennemi
     * qui inflige des dégâts avec 10% de chances de paralyser l'ennemi.
     * @param attaquant : Baobab exécutant l'action
     * @param cible : CalédoMon ennemi
     */
    public void executer(Animal attaquant, Animal cible) {
        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Frappe Tronc !");
            return;
        }
        applyStandardDamage(attaquant, cible, 0, "Frappe Tronc");
        // 10% chance de paralysie légère
        if (Math.random() < 0.10) {
            cible.applyStatusParalysis();
            System.out.println(cible.getNom() + " est paralysé !");
        }
    }
}
