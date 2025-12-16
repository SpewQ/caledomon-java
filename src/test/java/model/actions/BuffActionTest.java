
package model.actions;

import org.junit.Test;
import static org.junit.Assert.*;

public class BuffActionTest {
    @Test
    public void buffAction_estAbstraite() {
        assertTrue(java.lang.reflect.Modifier.isAbstract(BuffAction.class.getModifiers()));
    }
}
