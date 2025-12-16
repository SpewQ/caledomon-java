/**
 * <p>
 * Classe <strong>JetDEauPressurise</strong>.
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
 *   <li>Centraliser la logique associée à JetDEauPressurise</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions.dawa;

import model.Animal;
import model.actions.Action;

public class JetDEauPressurise extends Action {
    public JetDEauPressurise() { super(20, 95); }

    @Override
    /**
     * Méthode publique pour exécuter JetDEauPressurise sur l'ennemi
     * qui inflige des dégâts et a 20% de chances d'appliquer -1 attaque.
     * @param attaquant : Dawa exécutant l'action
     * @param cible : CalédoMon ennemi
     */
    public void executer(Animal attaquant, Animal cible) {
        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Jet d'Eau Pressurisé !");
            return;
        }
        applyStandardDamage(attaquant, cible, 0, "Jet d'Eau Pressurisé");
        if (Math.random() < 0.20) {
            cible.applyStage("attack", -1);
            System.out.println(cible.getNom() + " voit son attaque baisser !");
        }
    }
}
