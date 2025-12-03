package model;

import java.util.List;

import model.actions.Action;

/**
 * model/Animal.java
 */
public abstract class Animal {
    protected String nom;
    protected int pv;
    protected int maxPv;
    protected int attaque;
    protected int defense;
    protected int vitesse;
    protected Type type;
    protected Etat etat;

    // Liste d'actions de l'animal
    public List<Action> actions;

    public Animal(String nom, int pv, int attaque, int defense, int vitesse, Type type) {
        this.nom = nom;
        this.pv = pv;
        this.maxPv = pv;
        this.attaque = attaque;
        this.defense = defense;
        this.vitesse = vitesse;
        this.type = type;
        this.etat = Etat.NORMAL;
    }

    public abstract void actionSpeciale(Animal cible);

    /**
     * Attaque "de base" générique.
     */
    public void attaquer(Animal cible) {
        // dégâts de base
        int base = Math.max(0, this.attaque - cible.defense);

        // multiplicateur selon les types
        double mult = this.type.effectivenessAgainst(cible.getType());

        int degats = (int) Math.round(base * mult);

        // cible en défense
        if (cible.etat == Etat.DEFENSE) {
            degats = Math.max(0, degats - 5);
        }

        // on évite les attaques qui font 0 alors que ça touche
        degats = Math.max(1, degats);

        cible.setPv(cible.getPv() - degats);

        // Messages pour l'UI (console pour l’instant)
        System.out.println(nom + " attaque " + cible.nom + " et inflige " + degats + " dégâts !");

        if (mult > 1.0) {
            System.out.println("C'est super efficace !");
        } else if (mult < 1.0) {
            System.out.println("Ce n'est pas très efficace...");
        }
    }

    public void defendre() {
        System.out.println(nom + " se met en défense !");
        this.etat = Etat.DEFENSE;
    }

    public void soigner() {
        int soin = 15;
        setPv(Math.min(getPv() + soin, getMaxPv()));
        System.out.println(nom + " se soigne de " + soin + " PV.");
    }

    public boolean estVivant() {
        return this.pv > 0;
    }

    // Getters / Setters
    public String getNom() { return nom; }
    public int getPv() { return pv; }
    public void setPv(int pv) { this.pv = Math.max(0, pv); }
    public int getAttaque() { return attaque; }
    public void setAttaque(int attaque) { this.attaque = attaque; }
    public int getDefense() { return defense; }
    public void setDefense(int defense) { this.defense = defense; }
    public int getVitesse() { return vitesse; }
    public void setVitesse(int vitesse) { this.vitesse = vitesse; }
    public Type getType() { return type; }
    public Etat getEtat() { return etat; }
    public void setEtat(Etat e) { this.etat = e; }

    public int getMaxPv() { return maxPv; }

    // --- Actions management ---
    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public abstract Animal copy();
}
