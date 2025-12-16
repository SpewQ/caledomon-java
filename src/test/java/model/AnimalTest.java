package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import model.animals.Cagou;

public class AnimalTest {

    private Animal a1;
    private Animal a2;

    @Before
    public void setup() {
        a1 = new Cagou();
        a2 = new Cagou();
    }

    @Test
    public void attaque_reduitPVAdversaire() {
        int pvInitial = a2.getPv();
        a1.attaquer(a2);
        assertTrue(a2.getPv() < pvInitial);
    }

    @Test
    public void defendre_changeEtatDefense() {
        a1.defendre();
        assertEquals(Etat.DEFENSE, a1.getEtat());
    }

    @Test
    public void seSoigner_augmentePV_sansDepasserMax() {
        a1.setPv(10);
        a1.soigner();
        assertTrue(a1.getPv() > 10);
        assertTrue(a1.getPv() <= a1.getMaxPv());
    }

    @Test
    public void estVivant_fonctionneCorrectement() {
        a1.setPv(1);
        assertTrue(a1.estVivant());

        a1.setPv(0);
        assertFalse(a1.estVivant());
    }

    @Test
    public void setPv_neDescendJamaisSousZero() {
        a1.setPv(-50);
        assertEquals(0, a1.getPv());
    }
}
