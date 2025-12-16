/**
 * <p>
 * Classe <strong>SifflementStrident</strong>.
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
 *   <li>Centraliser la logique associée à SifflementStrident</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions.picot;

import model.Animal;
import model.actions.Action;

public class SifflementStrident extends Action {
    public SifflementStrident() { super(18, 95); }

    @Override
    /**
     * Méthode publique pour exécuter SifflementStrident sur l'ennemi
     * qui inflige des dégâts et a 30% de chances de paralyser l'ennemi.
     * @param user : Picot exécutant l'action
     * @param target : CalédoMon ennemi
     */
    public void executer(Animal attaquant, Animal cible) {
        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Sifflement Strident !");
            return;
        }
        applyStandardDamage(attaquant, cible, 0, "Sifflement Strident");
        if (Math.random() < 0.30) {
            cible.applyStatusParalysis();
            System.out.println(cible.getNom() + " est paralysé !");
        }
    }
}
