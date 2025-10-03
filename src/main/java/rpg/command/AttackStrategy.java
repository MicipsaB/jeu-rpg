package rpg.command;

import rpg.model.ICharacter;

/**
 * Interface Strategy pour les attaques.
 */
public interface AttackStrategy {
    void attack(ICharacter attacker, ICharacter defender);
}
