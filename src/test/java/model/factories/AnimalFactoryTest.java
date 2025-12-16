package model.factories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import model.Animal;
import model.animals.Cagou;
import model.animals.Gecko;
import model.animals.TricotRaye;

public class AnimalFactoryTest {

    @Test
    public void createCagou_retourneBienUnCagou() {
        Animal a = AnimalFactory.createAnimal("Cagou");
        assertNotNull(a);
        assertTrue(a instanceof Cagou);
        assertEquals("Cagou", a.getNom());
    }

    @Test
    public void createGecko_retourneBienUnGecko() {
        Animal a = AnimalFactory.createAnimal("Gecko");
        assertNotNull(a);
        assertTrue(a instanceof Gecko);
        assertEquals("Gecko", a.getNom());
    }

    @Test
    public void createTricotRaye_avecVariantes_retourneInstanceCorrecte() {
        assertTrue(AnimalFactory.createAnimal("Tricot Rayé")   instanceof TricotRaye);
        assertTrue(AnimalFactory.createAnimal("Tricot Raye")   instanceof TricotRaye);
        assertTrue(AnimalFactory.createAnimal("tricotraye")    instanceof TricotRaye);
    }

    @Test(expected = IllegalArgumentException.class)
    public void animalInconnu_lanceException() {
        AnimalFactory.createAnimal("Pokemon");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nomNull_lanceException() {
        AnimalFactory.createAnimal(null);
    }
}
