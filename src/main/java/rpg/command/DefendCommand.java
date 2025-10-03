package rpg.command;

import rpg.model.ICharacter;

/**
 * Commande pour défendre.
 */
public class DefendCommand implements Command {
    private ICharacter defender;

    public DefendCommand(ICharacter defender) { this.defender = defender; }

    @Override
    public void execute() {
        System.out.println(defender.getName() + " se défend !");
        defender.notifyObservers("Defended");
    }
}
