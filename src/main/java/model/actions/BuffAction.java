package model.actions;

import java.util.HashMap;
import java.util.Map;

/**
 * Action de type Buff, améliore les stats du lanceur.
 * Sert de marqueur pour déclencher animations/sons.
 */
public abstract class BuffAction extends Action {

    public BuffAction(String nom) {
        super();
    }

    protected Map<String, Integer> stages = new HashMap<>();
    public Map<String, Integer> getStages() { return stages; }

    @Override
    public boolean isBuff() { return true; }

    @Override
    public boolean isDebuff() { return false; }
}
