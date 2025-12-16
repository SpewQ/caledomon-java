
package view;

import org.junit.Test;
import static org.junit.Assert.*;

public class BattleViewSignatureTest {
    @Test
    public void classe_charge() throws Exception {
        Class<?> c = Class.forName("view.BattleView");
        assertNotNull(c.getConstructor(Class.forName("controller.BattleController")));
    }
}
