package controller;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Battle;
import model.EnvironmentType;
import model.animals.Cagou;
import model.actions.AttackAction;
import view.BattleView;

public class BattleControllerTest {

    private BattleController controller;
    private Battle battle;
    private BattleView view;

    @Before
    public void setup() {
        battle = new Battle(new Cagou(), new Cagou(), EnvironmentType.GRASS);
        controller = new BattleController(battle);
        view = mock(BattleView.class);
        controller.setView(view);
    }

    @Test
    public void startBattle_initialiseVue() {
        controller.startBattle();

        verify(view, atLeastOnce()).setEnvironment(any());
        verify(view).bindNames(anyString(), anyString());
        verify(view).refreshHp(anyInt(), anyInt(), anyInt(), anyInt());
        verify(view).setImages(anyString(), anyString());
    }

    @Test
    public void onPlayerAction_executeActionEtMetAJourVue() {
        controller.startBattle();
        controller.onPlayerAction(new AttackAction());

        verify(view, atLeastOnce()).refreshHp(anyInt(), anyInt(), anyInt(), anyInt());
        verify(view, atLeastOnce()).addLog(anyString());
    }
}
