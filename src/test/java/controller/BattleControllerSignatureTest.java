
package controller;

import org.junit.Test;
import static org.junit.Assert.*;

public class BattleControllerSignatureTest {
    @Test
    public void constructeur_existe() throws Exception {
        Class<?> c = Class.forName("controller.BattleController");
        Class<?> battle = Class.forName("model.Battle");
        assertNotNull(c.getConstructor(battle));
    }
}
