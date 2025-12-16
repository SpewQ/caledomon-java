
package model;

import org.junit.Test;
import static org.junit.Assert.*;

public class EtatTest {
    @Test
    public void Etat_contientDesValeurs() {
        assertNotNull(Etat.values());
        assertTrue(Etat.values().length > 0);
        for (Etat v : Etat.values()) {
            assertNotNull(v);
            assertNotNull(v.name());
        }
    }
}
