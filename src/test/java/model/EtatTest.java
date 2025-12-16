package model;

import static org.junit.Assert.*;
import org.junit.Test;

public class EtatTest {

    @Test
    public void etatEnum_contientTousLesEtats() {
        assertNotNull(Etat.valueOf("NORMAL"));
        assertNotNull(Etat.valueOf("DEFENSE"));
        assertNotNull(Etat.valueOf("EMPOISONNE"));
        assertNotNull(Etat.valueOf("PARALYSE"));
    }

    @Test
    public void etatEnum_nombreExactDesValeurs() {
        assertEquals(4, Etat.values().length);
    }
}
