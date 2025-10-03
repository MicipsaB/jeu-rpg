package rpg.model;

/**
 * Décorateur ajoutant la résistance au feu.
 */
public class FireResistDecorator extends CharacterDecorator {
    public FireResistDecorator(ICharacter c) { super(c); }

    @Override
    public double getPowerLevel() {
        return super.getPowerLevel() + 3;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Fire Resistance";
    }
}
