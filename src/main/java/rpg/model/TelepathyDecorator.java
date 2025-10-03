package rpg.model;

/**
 * Décorateur ajoutant la télépathie.
 */
public class TelepathyDecorator extends CharacterDecorator {
    public TelepathyDecorator(ICharacter c) { super(c); }

    @Override
    public double getPowerLevel() {
        return super.getPowerLevel() + 4;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Telepathy";
    }
}
