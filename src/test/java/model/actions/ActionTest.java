
package model.actions;

import org.junit.Test;
import static org.junit.Assert.*;

public class ActionTest {
    @Test
    public void action_estAbstraite() {
        assertTrue(java.lang.reflect.Modifier.isAbstract(Action.class.getModifiers()));
    }
}
