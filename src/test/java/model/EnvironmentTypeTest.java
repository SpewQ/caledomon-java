
package model;

import org.junit.Test;
import static org.junit.Assert.*;

public class EnvironmentTypeTest {
    @Test
    public void EnvironmentType_contientDesValeurs() {
        assertNotNull(EnvironmentType.values());
        assertTrue(EnvironmentType.values().length > 0);
        for (EnvironmentType v : EnvironmentType.values()) {
            assertNotNull(v);
            assertNotNull(v.name());
        }
    }
}
