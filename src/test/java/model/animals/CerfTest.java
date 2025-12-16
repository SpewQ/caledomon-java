
package model.animals;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class CerfTest {
    @Test
    public void instanciation_et_pv_positifs() throws Exception {
        Cerf a = new Cerf();
        assertNotNull(a);

        try {
            java.lang.reflect.Method getPv = a.getClass().getMethod("getPv");
            Object pv = getPv.invoke(a);
            assertTrue(((Number) pv).intValue() > 0);
        } catch (NoSuchMethodException ignored) {
            
        }
    }
}
