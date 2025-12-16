package testsupport;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public final class ReflectionTestUtils {
    private ReflectionTestUtils() {}

    public static Object newInstanceOrFail(Class<?> clazz) {
        try {
            Constructor<?> c = clazz.getDeclaredConstructor();
            c.setAccessible(true);
            return c.newInstance();
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Pas de constructeur sans paramètre pour " + clazz.getName(), e);
        } catch (Exception e) {
            throw new AssertionError("Impossible d'instancier " + clazz.getName() + ": " + e, e);
        }
    }

    public static Method findExecute2Args(Class<?> clazz) {
        for (Method m : clazz.getMethods()) {
            if (!m.getName().equals("execute")) continue;
            if (m.getParameterCount() != 2) continue;
            return m;
        }
        return null;
    }

    public static boolean isAbstract(Class<?> clazz) {
        return Modifier.isAbstract(clazz.getModifiers());
    }
}
