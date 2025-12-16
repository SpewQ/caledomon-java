
package model.actions.tortue;

import model.animals.Cagou;
import org.junit.Test;
import static org.junit.Assert.*;
import testsupport.ReflectionTestUtils;

public class CoupDeCarapaceTest {
    @Test
    public void instanciation_et_execute_ne_crash_pas() throws Exception {
        Object action = ReflectionTestUtils.newInstanceOrFail(Class.forName("model.actions.tortue.CoupDeCarapace"));
        assertNotNull(action);

        java.lang.reflect.Method execute = ReflectionTestUtils.findExecute2Args(action.getClass());
        if (execute == null) {
            
            return;
        }

        Cagou attaquant = new Cagou();
        Cagou defenseur = new Cagou();

        try {
            execute.invoke(action, attaquant, defenseur);
        } catch (java.lang.reflect.InvocationTargetException ite) {
            Throwable cause = ite.getCause();
            fail("execute a levé une exception: " + cause);
        }
    }
}
