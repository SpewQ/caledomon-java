package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import model.actions.AttackAction;
import model.animals.Cagou;

public class BattleTest {

    private Animal j1;
    private Animal j2;
    private Battle battle;

    @Before
    public void setup() {
        j1 = new Cagou();
        j2 = new Cagou();
        battle = new Battle(j1, j2, EnvironmentType.GRASS);
    }

    @Test
    public void combatTermine_siJPV0() {
        j1.setPv(0);
        assertTrue(battle.combatTermine());
    }

    @Test
    public void vainqueurCorrect() {
        j1.setPv(50);
        j2.setPv(0);
        assertEquals(j1, battle.getVainqueur());
    }

    @Test
    public void jouerTour_reduitPVAdversaire() {
        int pvAvant = j2.getPv();
        battle.jouerTour(new AttackAction(), new AttackAction());
        assertTrue(j2.getPv() < pvAvant || j1.getPv() < pvAvant);
    }

    @Test
    public void joueurLePlusRapideCommence() {
        j1.setVitesse(100);
        j2.setVitesse(1);

        int pvAvantJ2 = j2.getPv();
        battle.jouerTour(new AttackAction(), new AttackAction());

        assertTrue(j2.getPv() < pvAvantJ2);  // j1 attaque en premier
    }

    @Test
public void effetPoison_retire5PlusDegatsDuTour() {
    j1.setEtat(Etat.EMPOISONNE);

    int pvAvant = j1.getPv();

    battle.jouerTour(new AttackAction(), new AttackAction());

    assertEquals(pvAvant - 10, j1.getPv());
}

}
