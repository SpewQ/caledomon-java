package model;

import static org.junit.Assert.*;
import org.junit.Test;

public class TypeTest {

    @Test
    public void typeEnum_contientTousLesTypes() {
        assertNotNull(Type.valueOf("TERRESTRE"));
        assertNotNull(Type.valueOf("AERIEN"));
        assertNotNull(Type.valueOf("MARIN"));
    }

    @Test
    public void typeEnum_nombreExactDeValeurs() {
        assertEquals(3, Type.values().length);
    }

    @Test
    public void typeEnum_ordresCorrects() {
        Type[] values = Type.values();
        assertEquals(Type.TERRESTRE, values[0]);
        assertEquals(Type.AERIEN, values[1]);
        assertEquals(Type.MARIN, values[2]);
    }
}
