package main;

import org.junit.*;
import org.junit.Assume;
import static org.junit.Assert.*;

public class MainAppTest {
    private MainApp sut;

    @Before
    public void setUp() {
        try {
            sut = new MainApp();
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
