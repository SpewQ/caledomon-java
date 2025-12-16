/**
 * <p>
 * Classe <strong>QueueFouetRapide</strong>.
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
 *   <li>Centraliser la logique associée à QueueFouetRapide</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions.gecko;

import model.Animal;
import model.actions.Action;

public class QueueFouetRapide extends Action {
    public QueueFouetRapide() { super(15, 100); }

    @Override
    /**
     * Méthode publique pour exécuter QueueFouetRapide sur soi
     * qui inflige des dégâts et applique +1 vitesse.
     * @param attaquant : Gecko exécutant l'action
     * @param cible : CalédoMon ennemi
     */
    public void executer(Animal attaquant, Animal cible) {
        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Queue-Fouet Rapide !");
            return;
        }
        applyStandardDamage(attaquant, cible, 0, "Queue-Fouet Rapide");
        // gagne un stage de vitesse
        attaquant.applyStage("speed", +1);
        System.out.println(attaquant.getNom() + " devient plus rapide !");
    }
}
