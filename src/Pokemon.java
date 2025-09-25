public class Pokemon {
    private String name;
    private int health;
    private int strength;
    private int speed;

    public Pokemon(String name, int health, int strength, int speed) {
        if (health < 1 || health > 300 ||
            strength < 1 || strength > 300 ||
            speed < 1 || speed > 300) {
            throw new IllegalArgumentException("All parameters must be between 1 and 300 inclusive.");
        }
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getStrength() {
        return strength;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

    @Override
    public String toString() {
        return name + " (HP: " + health + ", STR: " + strength + ", SPD: " + speed + ")";
    }

    /**
     * Simule un combat entre deux Pokémon
     */
    public static void battle(Pokemon p1, Pokemon p2) {
        System.out.println("=== Combat: " + p1.getName() + " vs " + p2.getName() + " ===");
        // Le Pokémon avec la vitesse la plus élevée attaque en premier
        Pokemon attacker = p1.getSpeed() >= p2.getSpeed() ? p1 : p2;
        Pokemon defender = (attacker == p1) ? p2 : p1;

        System.out.println(attacker.getName() + " attaque en premier (vitesse " + attacker.getSpeed()
                            + " vs " + defender.getSpeed() + ")");

        // Boucle de combat
        while (p1.isAlive() && p2.isAlive()) {
            // Attaque
            System.out.println(attacker.getName() + " attaque " + defender.getName());
            defender.takeDamage(attacker.getStrength());
            System.out.println(defender.getName() + " reçoit " + attacker.getStrength()
                                + " dégâts, vie restante: " + defender.getHealth());

            // Vérifier si le défenseur est mort
            if (!defender.isAlive()) {
                System.out.println(defender.getName() + " est KO !");
                System.out.println(attacker.getName() + " a gagné !");
                break;
            }

            // Changer de rôle (le défenseur attaque maintenant)
            Pokemon temp = attacker;
            attacker = defender;
            defender = temp;
        }
    }
}
