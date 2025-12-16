package model.actions;

import model.Animal;
import model.Etat;
import model.Type;
import view.BattleView;

/**
 * Classe abstraite publique Action pour pouvoir instancier les capacités de chaque Calédomon
 */
public abstract class Action {
    // puissance conventionnelle (pour attaques)
    protected int power = 0;
    protected int accuracy = 100; // en pourcentage
    protected BattleView battleView;

    /**
     * Constructeur par défaut
     */
    public Action() {}

    /**
     * Constructeur avec paramètres
     * @param power : entier représentant la puissance d'une capacité
     * @param accuracy : entier représentant la précision d'une capacité
     */
    public Action(int power, int accuracy) {
        this.power = power;
        this.accuracy = accuracy;
    }

    /**
     * Méthode publique permettant de régler une vue de combat
     * @param battleView : vue de combat
     */
    public void setBattleView(BattleView battleView) {
        this.battleView = battleView;
    }

    /**
     * Exécuter l'action : user = qui utilise, target = cible.
     * Les sous-classes appelleront user/target methods ou infligeront status.
     */
    public abstract void executer(Animal user, Animal target);

    /**
     * Getter pour l'attribut power
     */
    public int getPower() { return power; }

    /**
     * Getter pour l'attribut accuracy
     */
    public int getAccuracy() { return accuracy; }

    /**
     * Calcul standard : (attaque - défense + bonus), multiplicateur de type,
     * état DEFENSE, messages, etc.
     */
    public void applyStandardDamage(Animal attaquant, Animal cible, int bonus, String nomAttaque) {
        // base = attaque - défense + bonus
        int base = Math.max(0, attaquant.getRealAttack() - cible.getRealDefense() + bonus);

        // multiplicateur de type
        Type typeAtt = attaquant.getType();
        Type typeDef = cible.getType();
        double mult = typeAtt.effectivenessAgainst(typeDef);

        int degats = (int)Math.max(1, Math.round(base * mult * (this.power / 10.0)));

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

    /**
     * Méthode publique renvoyant un booléen si une action est un buff ou non
     */
    public boolean isBuff() { return false; }

    /**
     * Méthode publique renvoyant un booléen si une action est un débuff ou non
     */
    public boolean isDebuff() { return false; }

}
