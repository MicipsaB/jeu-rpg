package rpg.mvc;

import rpg.model.ICharacter;

/**
 * Vue console simple.
 */
public class ConsoleView {
    public void showCharacter(ICharacter c) {
        System.out.println(c.getDescription());
    }
}
