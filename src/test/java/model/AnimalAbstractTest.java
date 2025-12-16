
package model;

import org.junit.Test;
import static org.junit.Assert.*;

public class AnimalAbstractTest {
    @Test
    public void Animal_estAbstraite() throws Exception {
        Class<?> c = Class.forName("model.Animal");
        assertTrue("La classe doit être abstract", java.lang.reflect.Modifier.isAbstract(c.getModifiers()));
    }
}
