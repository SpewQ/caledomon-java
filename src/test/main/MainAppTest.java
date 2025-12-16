package main;

import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class MainAppTest {

    private MainApp sut;

    @Before
    public void setUp() {
        sut = new MainApp();
    }

    @Test
    public void testSetUp_notNullWhenInitialized() {
        assertNotNull(sut);
    }

    @Ignore("Méthode start(Stage) dépend de JavaFX (non testable en unitaire)")
    @Test
    public void testStart() {
        
    }
}
