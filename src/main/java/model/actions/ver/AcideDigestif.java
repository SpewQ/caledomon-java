/**
 * <p>
 * Classe <strong>AcideDigestif</strong>.
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
 *   <li>Centraliser la logique associée à AcideDigestif</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions.ver;

import model.Animal;
import model.actions.Action;

public class AcideDigestif extends Action {
    public AcideDigestif() { super(18, 95); }

    @Override
    /**
     * Méthode publique pour exécuter AcideDigestif sur l'ennemi
     * qui inflige des dégâts et a 30% de chances d'empoisonner l'ennemi.
     * @param attaquant : Ver exécutant l'action
     * @param cible : CalédoMon ennemi
     */
    public void executer(Animal attaquant, Animal cible) {
        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Acide Digestif !");
            return;
        }
        applyStandardDamage(attaquant, cible, 0, "Acide Digestif");
        if (Math.random() < 0.30) {
            cible.applyStatusPoison();
            System.out.println(cible.getNom() + " est empoisonné !");
        }
    }
}
