package model;

import model.actions.Action;

public class Battle {
    private Animal joueur1;
    private Animal joueur2;
    private int tourActuel = 1;
    private EnvironmentType environment;   // 👈 nouvel attribut

    // Constructeur complet : on choisit l'environnement
    public Battle(Animal j1, Animal j2, EnvironmentType environment) {
        this.joueur1 = j1;
        this.joueur2 = j2;
        this.environment = environment;
    }

    // Constructeur par défaut : si tu ne précises rien -> GRASS
    public Battle(Animal j1, Animal j2) {
        this(j1, j2, EnvironmentType.GRASS);
    }

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

    public boolean combatTermine() {
        return !joueur1.estVivant() || !joueur2.estVivant();
    }

    public Animal getVainqueur() {
        if (joueur1.estVivant() && !joueur2.estVivant()) return joueur1;
        else if (joueur2.estVivant() && !joueur1.estVivant()) return joueur2;
        else return null;
    }

    public Animal getJoueur1() { return joueur1; }
    public Animal getJoueur2() { return joueur2; }

    // 👇 pour que la vue puisse savoir quel décor afficher
    public EnvironmentType getEnvironment() {
        return environment;
    }
}
