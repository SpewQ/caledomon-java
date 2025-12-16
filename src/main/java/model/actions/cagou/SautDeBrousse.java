/**
 * <p>
 * Classe <strong>SautDeBrousse</strong>.
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
 *   <li>Centraliser la logique associée à SautDeBrousse</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions.cagou;

import model.Animal;
import model.actions.Action;

public class SautDeBrousse extends Action {

    public SautDeBrousse() { 
        super(40, 70);
    } // plus de puissance, moins précis

    @Override
    /**
     * Méthode publique pour exécuter SautDeBrousse sur l'ennemi
     * qui inflige d'importants dégâts, avec 90% de chances au Cagou de se blesser.
     * @param attaquant : Cagou exécutant l'action
     * @param cible : CalédoMon ennemi
     */
    public void executer(Animal attaquant, Animal cible) {

        if (Math.random() * 100 > this.accuracy) {
            System.out.println(attaquant.getNom() + " rate Saut de Brousse !");
            return;
        }

        // bonus +5 par rapport à l'attaque de base
        applyStandardDamage(attaquant, cible, 5, "Saut de Brousse");

        // 90% de chances de self-damage (option) :
        if (Math.random() < 0.90) {
            long selfDmg = Math.max(1, (int) Math.round(attaquant.getMaxPv() * 0.05));
            attaquant.setPv((int) (attaquant.getPv() - selfDmg));
            System.out.println(attaquant.getNom() + " subit " + selfDmg + " de recul suite à Saut de Brousse.");
        }
    }
    
}
