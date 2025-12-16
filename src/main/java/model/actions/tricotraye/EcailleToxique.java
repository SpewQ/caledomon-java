/**
 * <p>
 * Classe <strong>EcailleToxique</strong>.
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
 *   <li>Centraliser la logique associée à EcailleToxique</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions.tricotraye;

import model.Animal;
import model.actions.Action;

public class EcailleToxique extends Action {

    public EcailleToxique() {
        super(30, 70);
    }

    @Override
    /**
     * Méthode publique pour exécuter EcailleToxique sur l'ennemi
     * qui a 70% de chances d'infliger d'importants dégâts.
     * @param attaquant : Tricot Rayé exécutant l'action
     * @param cible : CalédoMon ennemi
     */
    public void executer(Animal attaquant, Animal cible) {

        System.out.println(attaquant.getNom() + " projette une Écaille Toxique !");

        // 80% de précision
        if (Math.random() < 0.8) {
            // dégâts standardisés + bonus de +10
            applyStandardDamage(attaquant, cible, 10, "Écaille Toxique");
        } else {
            System.out.println(attaquant.getNom() + " rate son attaque !");
        }
    }
}
