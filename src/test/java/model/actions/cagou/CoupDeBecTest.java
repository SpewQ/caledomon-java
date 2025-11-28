package model.actions.cagou;

import static org.junit.Assert.*;

import org.junit.Test;

import model.animals.Cagou;
import model.Animal;

public class CoupDeBecTest {

    @Test
    public void coupDeBec_infligeDegatsCorrects() {
        Animal attaquant = new Cagou();
        Animal cible = new Cagou();

        int pvAvant = cible.getPv();

        CoupDeBec action = new CoupDeBec();
        action.executer(attaquant, cible);

        // Dégâts = attaque(15) - défense(10) = 5 (stats du Cagou)
        assertEquals(pvAvant - 5, cible.getPv());
    }
}
