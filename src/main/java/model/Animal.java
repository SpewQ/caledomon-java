package model;

import java.util.List;

import model.actions.Action;

/**
 * model/Animal.java - version avec stages de stats & status
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

    /**
     * Constructeur de la classe Animal
     * @param nom : nom du CalédoMon en chaîne de caractères
     * @param pv : points de vie du CalédoMon en entier
     * @param attaque : attaque du CalédoMon en entier
     * @param defense : défense du CalédoMon en entier
     * @param vitesse : vitesse du CalédoMon en entier
     * @param type : type du CalédoMon en Type (défini dans Type.java)
     */
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

    // --- combat helpers (capacités par défaut si pas d'actions assignées)------------------------------------
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

    /**
     * Getter de la liste d'actions
     */
    public List<Action> getActions() { return actions; }

    /**
     * Setter de la liste d'actions
     * @param actions : liste d'actions
     */
    public void setActions(List<Action> actions) { this.actions = actions; }

    /**
     * Méthode privée statique pour définir les limites de stages de stats (entre -6 et 6)
     */
    private static int clampStage(int v) {
        if (v < -6) return -6;
        if (v > 6) return 6;
        return v;
    }

    /**
     * Méthode privée statique pour calculer le multiplicateur de modification de stat en fonction du stage
     */
    private static double stageMultiplier(int stage) {
        return 1.0 + stage * 0.1; // chaque stage = +10% / -10%
    }

    /**
     * Méthode publique pour appliquer un stage de stat
     * @param stat : stat concernée, en chaîne de caractères
     * @param delta : différentiel en entier
     */
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

    /**
     * Getter pour le stage d'attaque
     */
    public int getAttackStage() { return attackStage; }

    /**
     * Getter pour le stage de défense
     */
    public int getDefenseStage() { return defenseStage; }

    /**
     * Getter pour le stage de vitesse
     */
    public int getSpeedStage() { return speedStage; }

    /**
     * Getter pour récupérer la vraie valeur d'attaque après calculs en fonction du multiplicateur de stage
     */
    public int getRealAttack() {
        double mult = stageMultiplier(attackStage);
        // calcul en entier
        return (int) Math.max(1, Math.round(attaque * mult));
    }

    /**
     * Getter pour récupérer la vraie valeur de défense après calculs en fonction du multiplicateur de stage
     */
    public int getRealDefense() {
        double mult = stageMultiplier(defenseStage);
        return (int) Math.max(0, Math.round(defense * mult));
    }

    /**
     * Getter pour récupérer la vraie valeur de vitesse après calculs en fonction du multiplicateur de stage
     */
    public int getRealVitesse() {
        double mult = stageMultiplier(speedStage);
        int base = (int)Math.max(1, Math.round(vitesse * mult));
        if (paralyzed) {
            base = (int)Math.max(1, Math.round(base * 0.5)); // paralysie réduit la vitesse
        }
        return base;
    }

    /**
     * Méthode publique pour appliquer le poison à une entité
     */
    public void applyStatusPoison() {
        if (!poisoned) {
            poisoned = true;
            System.out.println(nom + " est empoisonné !");
        }
    }

    /**
     * Méthode publique pour appliquer la paralysie à une entité
     */
    public void applyStatusParalysis() {
        if (!paralyzed) {
            paralyzed = true;
            System.out.println(nom + " est paralysé !");
        }
    }

    /**
     * Méthode publique pour renvoyer un booléen si une entité est empoisonnée ou non
     */
    public boolean isPoisoned() { return poisoned; }

    /**
     * Méthode publique pour renvoyer un booléen si une entité est paralysée ou non
     */
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
