package rpg.model;

import rpg.observer.CharacterObserver;

/**
 * Classe abstraite pour les décorateurs de personnages.
 */
public abstract class CharacterDecorator implements ICharacter {
    protected ICharacter character;
    public CharacterDecorator(ICharacter character) { this.character = character; }

    @Override
    public String getName() { return character.getName(); }
    @Override
    public double getPowerLevel() { return character.getPowerLevel(); }
    @Override
    public String getDescription() { return character.getDescription(); }

    @Override
    public void addObserver(CharacterObserver o) { character.addObserver(o); }
    @Override
    public void removeObserver(CharacterObserver o) { character.removeObserver(o); }
    @Override
    public void notifyObservers(String event) { character.notifyObservers(event); }
}
