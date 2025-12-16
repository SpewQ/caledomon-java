
package model.actions;

import org.junit.Test;
import static org.junit.Assert.*;

public class DebuffActionAbstractTest {
    @Test
    public void DebuffAction_estAbstraite() throws Exception {
        Class<?> c = Class.forName("model.actions.DebuffAction");
        assertTrue("La classe doit être abstract", java.lang.reflect.Modifier.isAbstract(c.getModifiers()));
    }
}
