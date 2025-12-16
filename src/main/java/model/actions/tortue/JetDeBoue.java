/**
 * <p>
 * Classe <strong>JetDeBoue</strong>.
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
 *   <li>Centraliser la logique associée à JetDeBoue</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions.tortue;

import model.Animal;
import model.actions.Action;

public class JetDeBoue extends Action {
    public JetDeBoue() { super(15, 95); }

    @Override
    /**
     * Méthode publique pour exécuter JetDeBoue sur soi et l'ennemi
     * qui inflige des dégâts et applique -1 de vitesse à l'ennemi.
     * @param attaquant : Tortue exécutant l'action
     * @param cible : CalédoMon ennemi
     */
    public void executer(Animal attaquant, Animal cible) {
        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Jet de Boue !");
            return;
        }
        applyStandardDamage(attaquant, cible, 0, "Jet de Boue");
        cible.applyStage("speed", -1);
        System.out.println(cible.getNom() + " voit sa vitesse diminuer !");
    }
}
