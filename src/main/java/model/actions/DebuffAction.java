package model.actions;

/**
 * Action de type Debuff, réduit des stats de la cible.
 * Sert de marqueur pour animations/sons.
 */
public abstract class DebuffAction extends Action {

    public DebuffAction(String nom) {
        super();
    }

    @Override
    public boolean isBuff() { return false; }

    @Override
    public boolean isDebuff() { return true; }
}
