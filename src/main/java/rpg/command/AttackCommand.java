package rpg.command;

import rpg.model.ICharacter;

/**
 * Commande pour lancer une attaque.
 */
public class AttackCommand implements Command {
    private ICharacter attacker;
    private ICharacter defender;
    private AttackStrategy strategy;

    public AttackCommand(ICharacter attacker, ICharacter defender, AttackStrategy strategy) {
        this.attacker = attacker;
        this.defender = defender;
        this.strategy = strategy;
    }

    @Override
    public void execute() {
        strategy.attack(attacker, defender);
        attacker.notifyObservers("Attacked " + defender.getName());
        defender.notifyObservers("Defended against " + attacker.getName());
    }
}
