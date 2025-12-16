package model.actions.cagou;

import static org.junit.Assert.*;

import org.junit.Test;

import model.animals.Cagou;
import model.Animal;

public class SautDeBrousseTest {

    @Test
    public void sautDeBrousse_infligePlusDeDegatsQuUnCoupNormal() {
        Animal attaquant = new Cagou();
        Animal cible1 = new Cagou();
        Animal cible2 = new Cagou();

        int pvAvant1 = cible1.getPv();
        int pvAvant2 = cible2.getPv();

        // Coup normal = CoupDeBec
        new CoupDeBec().executer(attaquant, cible1);

        // Saut de brousse
        new SautDeBrousse().executer(attaquant, cible2);

        int degatsNormal = pvAvant1 - cible1.getPv();
        int degatsSaut = pvAvant2 - cible2.getPv();

        assertTrue(degatsSaut > degatsNormal);
    }
}
