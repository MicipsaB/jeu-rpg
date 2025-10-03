package rpg.model;

/**
 * Décorateur ajoutant la capacité d'invisibilité.
 */
public class InvisibilityDecorator extends CharacterDecorator {
    public InvisibilityDecorator(ICharacter c) { super(c); }

    @Override
    public double getPowerLevel() {
        return super.getPowerLevel() + 2;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Invisibility";
    }
}
