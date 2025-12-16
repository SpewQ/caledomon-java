
package view;

import org.junit.Test;
import static org.junit.Assert.*;

public class ResultViewTest {
    @Test
    public void constructeur_prend_deux_strings() throws Exception {
        Class<?> c = Class.forName("view.ResultView");
        assertNotNull(c.getConstructor(String.class, String.class));
    }
}
