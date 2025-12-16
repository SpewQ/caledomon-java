
package controller;

import org.junit.Test;
import static org.junit.Assert.*;

public class SelectionControllerTest {
    @Test
    public void constructeur_prend_un_Stage_et_SelectionView() throws Exception {
        Class<?> c = Class.forName("controller.SelectionController");
        Class<?> stage = Class.forName("javafx.stage.Stage");
        Class<?> view = Class.forName("view.SelectionView");
        assertNotNull(c.getConstructor(stage, view));
    }
}
