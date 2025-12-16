
package model;

import org.junit.Test;
import static org.junit.Assert.*;

public class TypeTest {
    @Test
    public void Type_contientDesValeurs() {
        assertNotNull(Type.values());
        assertTrue(Type.values().length > 0);
        for (Type v : Type.values()) {
            assertNotNull(v);
            assertNotNull(v.name());
        }
    }
}
