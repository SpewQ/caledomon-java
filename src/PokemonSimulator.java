public class PokemonSimulator {
    public static void main(String[] args) {
        // Création de quelques Pokémons
        Pokemon charizard = new Pokemon("Charizard", 100, 50, 50);
        Pokemon blastoise = new Pokemon("Blastoise", 150, 25, 150);
        Pokemon venusaur  = new Pokemon("Venusaur", 300, 10, 100);

        // Simuler quelques combats
        Pokemon.battle(charizard, blastoise);
        System.out.println();

        Pokemon.battle(blastoise, venusaur);
        System.out.println();

        Pokemon.battle(venusaur, charizard);
    }
}