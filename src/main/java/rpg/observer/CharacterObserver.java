package rpg.observer;

import rpg.model.ICharacter;

/**
 * Interface Observer pour écouter les changements sur les personnages.
 */
public interface CharacterObserver {
    void onCharacterChanged(ICharacter character, String event);
}
