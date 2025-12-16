
package model;

import org.junit.Test;
import static org.junit.Assert.*;

public class AnimalTest {
    @Test
    public void animal_estAbstraite() {
        assertTrue(java.lang.reflect.Modifier.isAbstract(Animal.class.getModifiers()));
    }
}
