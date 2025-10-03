package rpg.command;

import rpg.model.ICharacter;

/**
 * Stratégie d'attaque simple : compare les niveaux de puissance.
 */
public class BasicAttackStrategy implements AttackStrategy {
    @Override
    public void attack(ICharacter attacker, ICharacter defender) {
        System.out.println(attacker.getName() + " attaque " + defender.getName());
        if (attacker.getPowerLevel() > defender.getPowerLevel()) {
            System.out.println(attacker.getName() + " gagne l'échange !");
        } else {
            System.out.println(defender.getName() + " résiste !");
        }
    }
}
