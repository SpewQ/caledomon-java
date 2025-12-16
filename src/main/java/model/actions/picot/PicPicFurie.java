/**
 * <p>
 * Classe <strong>PicPicFurie</strong>.
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
 *   <li>Centraliser la logique associée à PicPicFurie</li>
 *   <li>Garantir la cohérence des données manipulées</li>
 *   <li>Faciliter l'évolution et la maintenabilité du code</li>
 * </ul>
 */

package model.actions.picot;

import model.Animal;
import model.actions.Action;

public class PicPicFurie extends Action {
    public PicPicFurie() { super(12, 90); }

    @Override
    /**
     * Méthode publique pour exécuter PicDeFurie sur l'ennemi
     * qui inflige des dégâts 1 à 2 fois.
     * @param user : Picot exécutant l'action
     * @param target : CalédoMon ennemi
     */
    public void executer(Animal attaquant, Animal cible) {
        int hits = 2 + (Math.random() < 0.5 ? 1 : 0); // 2 ou 3 coups
        for (int i = 0; i < hits; i++) {
            if (Math.random() * 100 > this.accuracy) {
                System.out.println(attaquant.getNom() + " rate un coup de Pic-Pic-Furie !");
            } else {
                applyStandardDamage(attaquant, cible, 0, "Pic-Pic-Furie (coup)");
            }
        }
    }
}
