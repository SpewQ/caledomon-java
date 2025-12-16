package model.actions;

import java.util.HashMap;
import java.util.Map;

/**
 * Action de type Debuff, réduit des stats de la cible.
 * Sert de marqueur pour animations/sons.
 */
public abstract class DebuffAction extends Action {

    public DebuffAction(String nom) {
        super();
    }

    protected Map<String, Integer> stages = new HashMap<>();
    public Map<String, Integer> getStages() { return stages; }

    @Override
    public boolean isBuff() { return false; }

    @Override
    public boolean isDebuff() { return true; }
}
