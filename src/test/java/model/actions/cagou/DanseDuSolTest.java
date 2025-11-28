package model.actions.cagou;

import static org.junit.Assert.*;

import org.junit.Test;

import model.animals.Cagou;
import model.Animal;

public class DanseDuSolTest {

    @Test
    public void danseDuSol_buffDefenseEtVitesse() {
        Animal cagou = new Cagou();

        int defAvant = cagou.getDefense();
        int vitAvant = cagou.getVitesse();

        DanseDuSol action = new DanseDuSol();
        action.executer(cagou, new Cagou());

        assertTrue(cagou.getDefense() > defAvant);
        assertTrue(cagou.getVitesse() > vitAvant);
    }
}
