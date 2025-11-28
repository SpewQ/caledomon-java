package model.actions.cagou;

import static org.junit.Assert.*;

import org.junit.Test;

import model.animals.Cagou;
import model.Animal;
import model.Etat;

public class CriAlerteTest {

    @Test
    public void criAlerte_metEnDefense() {
        Animal attaquant = new Cagou();
        Animal cible = new Cagou();

        CriAlerte action = new CriAlerte();
        action.executer(attaquant, cible);

        // Baisse l'attaque de la cible (valeur inconnue, mais on vérifie la logique)
        assertTrue(cible.getAttaque() < new Cagou().getAttaque());
    }
}
