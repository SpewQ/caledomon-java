
package view;

import org.junit.Test;
import static org.junit.Assert.*;

public class BattleViewTest {
    @Test
    public void constructeur_prend_un_BattleController() throws Exception {
        Class<?> c = Class.forName("view.BattleView");
        Class<?> bc = Class.forName("controller.BattleController");
        assertNotNull(c.getConstructor(bc));
    }
}
