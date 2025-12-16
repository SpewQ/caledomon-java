
package controller;

import org.junit.Test;
import static org.junit.Assert.*;

public class BattleControllerTest {
    @Test
    public void constructeur_prend_un_Battle() throws Exception {
        Class<?> c = Class.forName("controller.BattleController");
        Class<?> battle = Class.forName("model.Battle");
        assertNotNull(c.getConstructor(battle));
    }
}
