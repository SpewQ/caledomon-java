
package model.actions;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Test;

import model.animals.Cagou;
import testsupport.ReflectionTestUtils;

public class SpecialActionTest {
    @Test
    public void instanciation_et_execute_ne_crash_pas() throws Exception {
        Object action = ReflectionTestUtils.newInstanceOrFail(Class.forName("model.actions.SpecialAction"));
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
