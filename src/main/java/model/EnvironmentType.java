package model;

import java.util.Random;

/**
 * Enumération publique des types d'environnements
 */
public enum EnvironmentType {
    GRASS,
    SAND,
    ROCK,
    WATER,
    CAVE;

    private static final Random RNG = new Random();

    public static EnvironmentType random() {
        EnvironmentType[] values = values();
        return values[RNG.nextInt(values.length)];
    }
}
