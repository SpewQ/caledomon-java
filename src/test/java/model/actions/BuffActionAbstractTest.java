
package model.actions;

import org.junit.Test;
import static org.junit.Assert.*;

public class BuffActionAbstractTest {
    @Test
    public void BuffAction_estAbstraite() throws Exception {
        Class<?> c = Class.forName("model.actions.BuffAction");
        assertTrue("La classe doit être abstract", java.lang.reflect.Modifier.isAbstract(c.getModifiers()));
    }
}
