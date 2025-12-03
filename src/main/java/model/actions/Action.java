package model.actions;

import model.Animal;
import model.Etat;
import model.Type;

public interface Action {

    void executer(Animal attaquant, Animal cible);

    /**
     * Calcul standard : (attaque - défense + bonus), multiplicateur de type,
     * état DEFENSE, messages, etc.
     */
    default void applyStandardDamage(Animal attaquant, Animal cible, int bonus, String nomAttaque) {
        // base = attaque - défense + bonus
        int base = Math.max(0, attaquant.getAttaque() - cible.getDefense() + bonus);

        // multiplicateur de type
        Type typeAtt = attaquant.getType();
        Type typeDef = cible.getType();
        double mult = typeAtt.effectivenessAgainst(typeDef);

        int degats = (int) Math.round(base * mult);

        // cible en défense
        if (cible.getEtat() == Etat.DEFENSE) {
            degats = Math.max(0, degats - 5);
        }

        degats = Math.max(1, degats);

        cible.setPv(cible.getPv() - degats);

        System.out.println(attaquant.getNom() + " utilise " + nomAttaque + " ! (" + degats + " dégâts)");

        if (mult > 1.0) {
            System.out.println("C'est super efficace !");
        } else if (mult < 1.0) {
            System.out.println("Ce n'est pas très efficace...");
        }
    }
}
