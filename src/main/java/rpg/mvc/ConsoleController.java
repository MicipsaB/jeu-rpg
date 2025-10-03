package rpg.mvc;

import rpg.model.ICharacter;

/**
 * Contrôleur console simple.
 */
public class ConsoleController {
    private GameModel model;
    private ConsoleView view;

    public ConsoleController(GameModel model, ConsoleView view) {
        this.model = model;
        this.view = view;
    }

    public void addAndDisplayCharacter(ICharacter c) {
        model.addCharacter(c);
        view.showCharacter(c);
    }
}
