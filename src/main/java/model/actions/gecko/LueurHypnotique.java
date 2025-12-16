/**
 * <p>
 * Classe <strong>LueurHypnotique</strong>.
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
 *   <li>Centraliser la logique associée à LueurHypnotique</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions.gecko;

import model.Animal;
import model.actions.Action;

public class LueurHypnotique extends Action {
    public LueurHypnotique() { super(0, 90); }

    @Override
    /**
     * Méthode publique pour exécuter LueurHypnotique sur l'ennemi
     * qui a 50% de chances de paralyser l'ennemi.
     * @param attaquant : Gecko exécutant l'action
     * @param cible : CalédoMon ennemi
     */
    public void executer(Animal attaquant, Animal cible) {
        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Lueur Hypnotique !");
            return;
        }
        if (Math.random() < 0.50) {
            cible.applyStatusParalysis();
            System.out.println(cible.getNom() + " est paralysé par la Lueur Hypnotique !");
        } else {
            System.out.println(cible.getNom() + " résiste à la Lueur Hypnotique.");
        }
    }
}
