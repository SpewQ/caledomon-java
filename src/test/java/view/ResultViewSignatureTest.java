
package view;

import org.junit.Test;
import static org.junit.Assert.*;

public class ResultViewSignatureTest {
    @Test
    public void classe_charge() throws Exception {
        Class<?> c = Class.forName("view.ResultView");
        assertNotNull(c.getConstructor(Class.forName("java.lang.String"), Class.forName("java.lang.String")));
    }
}
