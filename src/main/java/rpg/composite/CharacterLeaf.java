package rpg.composite;

import rpg.model.ICharacter;

/**
 * Feuille représentant un personnage individuel.
 */
public class CharacterLeaf implements PartyComponent {
    private ICharacter character;

    public CharacterLeaf(ICharacter c) { this.character = c; }

    @Override
    public double getPowerLevel() { return character.getPowerLevel(); }

    @Override
    public String getName() { return character.getName(); }
}
