
package model;

import model.animals.Cagou;
import org.junit.Test;
import static org.junit.Assert.*;

public class BattleTest {
    @Test
    public void construction_battle_avec_deux_animaux_et_env() {
        Cagou a = new Cagou();
        Cagou b = new Cagou();
        Battle battle = new Battle(a, b, EnvironmentType.values()[0]);
        assertNotNull(battle);
    }

    @Test
    public void construction_battle_avec_deux_animaux_sans_env() {
        Cagou a = new Cagou();
        Cagou b = new Cagou();
        Battle battle = new Battle(a, b);
        assertNotNull(battle);
    }
}
