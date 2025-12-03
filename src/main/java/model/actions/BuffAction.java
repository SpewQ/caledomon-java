package model.actions;

/**
 * Action de type Buff, améliore les stats du lanceur.
 * Sert de marqueur pour déclencher animations/sons.
 */
public abstract class BuffAction extends Action {

    public BuffAction(String nom) {
        super();
    }

    @Override
    public boolean isBuff() { return true; }

    @Override
    public boolean isDebuff() { return false; }
}
