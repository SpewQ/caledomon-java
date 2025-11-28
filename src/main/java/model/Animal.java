package model;

import java.util.List;

import model.actions.Action;

/**
 * model/Animal.java
 */
public abstract class Animal {
    protected String nom;
    protected int pv;
    protected int attaque;
    protected int defense;
    protected int vitesse;
    protected Type type;
    protected Etat etat;

    // Nouvelle liste d'actions
    public List<Action> actions;

    public Animal(String nom, int pv, int attaque, int defense, int vitesse, Type type) {
        this.nom = nom;
        this.pv = pv;
        this.attaque = attaque;
        this.defense = defense;
        this.vitesse = vitesse;
        this.type = type;
        this.etat = Etat.NORMAL;
    }

    public abstract void actionSpeciale(Animal cible);

    public void attaquer(Animal cible) {
        int base = Math.max(0, this.attaque - cible.defense);
        int degats = base;
        if (cible.etat == Etat.DEFENSE) {
            degats = Math.max(0, degats - 5);
        }
        cible.setPv(cible.getPv() - degats);
        System.out.println(nom + " attaque " + cible.nom + " et inflige " + degats + " dégâts !");
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

    public int getMaxPv() { return 100; }

    // --- Actions management ---
    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }
    
    public abstract Animal copy();
}
