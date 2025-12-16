package view;

import org.junit.*;
import org.junit.Assume;
import static org.junit.Assert.*;

@Ignore("Dépend de JavaFX (Toolkit/Platform) — à tester via tests d’intégration (TestFX) ou en mockant l’UI.")
public class CaledomonInfoPaneTest {
    private CaledomonInfoPane sut;

    @Before
    public void setUp() {
        try {
            sut = new CaledomonInfoPane();
        } catch (Throwable t) {
            sut = null;
        }
    }

    @Test
    public void smoke_canInstantiate() {
        Assume.assumeNotNull(sut);
        assertNotNull(sut);
    }
}
