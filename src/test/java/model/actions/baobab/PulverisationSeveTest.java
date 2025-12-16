
package model.actions.baobab;

import model.animals.Cagou;
import org.junit.Test;
import static org.junit.Assert.*;
import testsupport.ReflectionTestUtils;

public class PulverisationSeveTest {
    @Test
    public void instanciation_et_execute_ne_crash_pas() throws Exception {
        Object action = ReflectionTestUtils.newInstanceOrFail(Class.forName("model.actions.baobab.PulverisationSeve"));
        assertNotNull(action);

        java.lang.reflect.Method execute = ReflectionTestUtils.findExecute2Args(action.getClass());
        if (execute == null) {
            // Certaines classes peuvent être des helpers sans execute(?, ?)
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
