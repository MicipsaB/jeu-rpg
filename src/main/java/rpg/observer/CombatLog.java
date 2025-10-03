package rpg.observer;

import rpg.model.ICharacter;

/**
 * Observer qui logge les événements de combat.
 */
public class CombatLog implements CharacterObserver {
    @Override
    public void onCharacterChanged(ICharacter character, String event) {
        System.out.println("[CombatLog] " + character.getName() + ": " + event);
    }
}
