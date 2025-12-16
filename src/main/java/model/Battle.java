package model;

import model.actions.Action;

/**
 * Classe publique Battle pour mettre en place le combat (qui sera récupéré par BattleView et BattleController)
 */
public class Battle {
    private Animal joueur1;
    private Animal joueur2;
    private int tourActuel = 1;
    private EnvironmentType environment;   // 👈 nouvel attribut

    /**
     * Constructeur Battle avec comme paramètres : 
     * @param j1 : CalédoMon du joueur
     * @param j2 : CalédoMon de l'IA
     * @param environment : Environnement sélectionné par l'IA
     */
    public Battle(Animal j1, Animal j2, EnvironmentType environment) {
        this.joueur1 = j1;
        this.joueur2 = j2;
        this.environment = environment;
    }

    /**
     * Constructeur Battle sans le paramètre environment qui mettra l'environnement GRASS par défaut.
     * @param j1 : CalédoMon du joueur
     * @param j2 : CalédoMon de l'IA
     */
    public Battle(Animal j1, Animal j2) {
        this(j1, j2, EnvironmentType.GRASS);
    }

    /**
     * Méthode publique jouerTour pour décider qui joue en premier en fonction de la vitesse
     */
    public void jouerTour(Action actionJ1, Action actionJ2) {
        System.out.println("=== Tour " + tourActuel + " ===");

        if (joueur1.getVitesse() >= joueur2.getVitesse()) {
            actionJ1.executer(joueur1, joueur2);
            if (joueur2.estVivant()) actionJ2.executer(joueur2, joueur1);
        } else {
            actionJ2.executer(joueur2, joueur1);
            if (joueur1.estVivant()) actionJ1.executer(joueur1, joueur2);
        }

        // Effet poison simple
        appliquerEffets(joueur1);
        appliquerEffets(joueur2);

        tourActuel++;
    }

    private void appliquerEffets(Animal a) {
        if (a.getEtat() == Etat.EMPOISONNE) {
            a.setPv(a.getPv() - 5);
        }
        if (a.getEtat() == Etat.DEFENSE) {
            // La défense revient à normal au prochain tour
            a.setEtat(Etat.NORMAL);
        }
    }

    /**
     * Méthode publique renvoyant un booléen si le combat est terminé ou non en fonction de la mort d'un des deux CalédoMons
     */
    public boolean combatTermine() {
        return !joueur1.estVivant() || !joueur2.estVivant();
    }

    /**
     * Getter pour désigner le vainqueur du combat
     */
    public Animal getVainqueur() {
        if (joueur1.estVivant() && !joueur2.estVivant()) return joueur1;
        else if (joueur2.estVivant() && !joueur1.estVivant()) return joueur2;
        else return null;
    }

    /**
     * Getter pour retourner le CalédoMon du joueur
     */
    public Animal getJoueur1() { return joueur1; }

    /**
     * Getter pour retourner le CalédoMon de l'IA
     */
    public Animal getJoueur2() { return joueur2; }

    /**
     * Appliquer les effets de fin de tour pour les deux combattants.
     * Retourne vrai si combat terminé après ces effets.
     */
    public boolean processEndOfTurn() {
        // On applique le poison / etc.
        // ordre : joueur puis IA (ou les deux simultanément).
        boolean finished = false;
        finished |= joueur1.tickEndOfTurnStatus();
        finished |= joueur2.tickEndOfTurnStatus();
        return finished;
    }

    /**
     * Vérifier si le user peut agir : gère paralysie chance d'échouer
     * Retourne true si l'action est possible.
     */
    public boolean canAct(Animal a) {
        if (!a.estVivant()) return false;
        if (a.isParalyzed()) {
            double r = Math.random();
            // 25% chance de rater l'action
            if (r < 0.25) {
                System.out.println(a.getNom() + " est paralysé et ne peut pas agir !");
                return false;
            }
        }
        return true;
    }

    /**
     * Getter qui récupère l'environnement utilisé
     */
    public EnvironmentType getEnvironment() {
        return environment;
    }
}
