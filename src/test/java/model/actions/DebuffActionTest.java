
package model.actions;

import org.junit.Test;
import static org.junit.Assert.*;

public class DebuffActionTest {
    @Test
    public void debuffAction_estAbstraite() {
        assertTrue(java.lang.reflect.Modifier.isAbstract(DebuffAction.class.getModifiers()));
    }
}
