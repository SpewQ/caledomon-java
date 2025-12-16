
package view;

import org.junit.Test;
import static org.junit.Assert.*;

public class SelectionViewSignatureTest {
    @Test
    public void constructeur_sans_parametre_existe() throws Exception {
        Class<?> c = Class.forName("view.SelectionView");
        assertNotNull(c.getConstructor());
    }
}
