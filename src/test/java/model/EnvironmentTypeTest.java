package model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class EnvironmentTypeTest {

    @Test
    public void random_neRetourneJamaisNull() {
        EnvironmentType env = EnvironmentType.random();
        assertNotNull("EnvironmentType.random() ne doit jamais retourner null", env);
    }

    @Test
    public void random_retourneToujoursUneValeurValide() {
        EnvironmentType env = EnvironmentType.random();

        boolean isValid = false;
        for (EnvironmentType type : EnvironmentType.values()) {
            if (type == env) {
                isValid = true;
                break;
            }
        }

        assertTrue("Valeur inattendue retournée par random(): " + env, isValid);
    }

    @Test
    public void random_donneUnPeuDeDiversite() {
        boolean seenGrass = false;
        boolean seenSand = false;
        boolean seenOther = false;

        for (int i = 0; i < 50; i++) {
            EnvironmentType env = EnvironmentType.random();

            switch (env) {
                case GRASS:
                    seenGrass = true;
                    break;
                case SAND:
                    seenSand = true;
                    break;
                default:
                    seenOther = true;
                    break;
            }
        }

        assertTrue("random() devrait retourner au moins un type",
                seenGrass || seenSand || seenOther);

        assertTrue("Sur 50 tirages, on s'attend à voir plusieurs types différents",
                (seenGrass && seenSand) || seenOther);
    }
}
