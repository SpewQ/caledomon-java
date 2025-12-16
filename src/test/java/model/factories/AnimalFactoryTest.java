
package model.factories;

import model.Animal;
import org.junit.Test;
import static org.junit.Assert.*;

public class AnimalFactoryTest {
    @Test
    public void createAnimal_reconnait_tous_les_noms_connus() {
        String[] noms = new String[] {
            "baobab", "cagou", "cerf", "dawa", "gecko", "notou", "picot", "roussette", "tortue", "tricot raye", "tricot rayé", "tricotraye", "ver"
        };

        for (String nom : noms) {
            Animal a = AnimalFactory.createAnimal(nom);
            assertNotNull("AnimalFactory doit créer un animal pour: " + nom, a);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void createAnimal_nom_inconnu_declenche_exception() {
        AnimalFactory.createAnimal("inconnu_xyz");
    }
}
