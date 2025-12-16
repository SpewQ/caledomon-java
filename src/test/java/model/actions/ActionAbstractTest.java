
package model.actions;

import org.junit.Test;
import static org.junit.Assert.*;

public class ActionAbstractTest {
    @Test
    public void Action_estAbstraite() throws Exception {
        Class<?> c = Class.forName("model.actions.Action");
        assertTrue("La classe doit être abstract", java.lang.reflect.Modifier.isAbstract(c.getModifiers()));
    }
}
