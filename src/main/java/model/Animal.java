package model;

import java.util.List;

import model.actions.Action;

/**
 * model/Animal.java - version avec stages & status
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

    // Stages de stats (-6..+6)
    protected int attackStage = 0;
    protected int defenseStage = 0;
    protected int speedStage = 0;

    // Statuts
    protected boolean poisoned = false;
    protected boolean paralyzed = false;

    // actions
    public List<Action> actions;

    public Animal(String nom, int pv, int attaque, int defense, int vitesse, Type type) {
        this.nom = nom;
        this.pv = pv;
        this.maxPv = pv; // initial max = pv de base
        this.attaque = attaque;
        this.defense = defense;
        this.vitesse = vitesse;
        this.type = type;
        this.etat = Etat.NORMAL;
    }

    public abstract void actionSpeciale(Animal cible);

    // --- combat helpers ------------------------------------
    public void attaquer(Animal cible) {
        int base = Math.max(1, this.getRealAttack() - cible.getRealDefense());
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

    // --- Getters / Setters ---------------------------------
    public String getNom() { return nom; }
    public int getPv() { return pv; }
    public void setPv(int pv) { this.pv = Math.max(0, Math.min(pv, maxPv)); }
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
    public void setMaxPv(int maxPv) { this.maxPv = maxPv; }

    // Actions
    public List<Action> getActions() { return actions; }
    public void setActions(List<Action> actions) { this.actions = actions; }

    // ----------------- stages & real stats -------------------
    private static int clampStage(int v) {
        if (v < -6) return -6;
        if (v > 6) return 6;
        return v;
    }

    private static double stageMultiplier(int stage) {
        return 1.0 + stage * 0.1; // chaque stage = +10% / -10%
    }

    public void applyStage(String stat, int delta) {
        switch (stat.toLowerCase()) {
            case "attack":
            case "attaque":
                attackStage = clampStage(attackStage + delta);
                break;
            case "defense":
                defenseStage = clampStage(defenseStage + delta);
                break;
            case "speed":
            case "vitesse":
                speedStage = clampStage(speedStage + delta);
                break;
            default:
                break;
        }
    }

    public int getAttackStage() { return attackStage; }
    public int getDefenseStage() { return defenseStage; }
    public int getSpeedStage() { return speedStage; }

    public int getRealAttack() {
        double mult = stageMultiplier(attackStage);
        // calcul en entier
        return (int) Math.max(1, Math.round(attaque * mult));
    }

    public int getRealDefense() {
        double mult = stageMultiplier(defenseStage);
        return (int) Math.max(0, Math.round(defense * mult));
    }

    public int getRealVitesse() {
        double mult = stageMultiplier(speedStage);
        int base = (int)Math.max(1, Math.round(vitesse * mult));
        if (paralyzed) {
            base = (int)Math.max(1, Math.round(base * 0.5)); // paralysie réduit la vitesse
        }
        return base;
    }

    // ----------------- statuses ------------------------------
    public void applyStatusPoison() {
        if (!poisoned) {
            poisoned = true;
            System.out.println(nom + " est empoisonné !");
        }
    }

    public void applyStatusParalysis() {
        if (!paralyzed) {
            paralyzed = true;
            System.out.println(nom + " est paralysé !");
        }
    }

    public boolean isPoisoned() { return poisoned; }
    public boolean isParalyzed() { return paralyzed; }

    /**
     * Appliquer les effets de statut en fin de tour.
     * Retourne true si une action importante s'est produite (ex: KO par poison).
     */
    public boolean tickEndOfTurnStatus() {
        // Poison : inflige maxPv/8 (arrondir vers le bas)
        if (poisoned && this.estVivant()) {
            int dmg = Math.max(1, this.maxPv / 8);
            setPv(this.getPv() - dmg);
            System.out.println(nom + " souffre du poison et perd " + dmg + " PV.");
            if (!this.estVivant()) {
                System.out.println(nom + " est K.O. à cause du poison !");
                return true;
            }
        }
        // Paralysie n'a pas d'effet end-of-turn (géré au moment d'agir)
        return false;
    }

    // ----------------- copy / factory note --------------------
    /**
     * copy() doit renvoyer une instance "propre" de la même espèce,
     * avec PV = maxPv (fresh), stages reset, statuses reset.
     * Comme Animal est abstrait, chaque sous-classe doit implémenter copy().
     */
    public abstract Animal copy();
}
