package rpg.command;

import java.io.*;
import java.util.*;

/**
 * Gestionnaire de commandes avec historique et replay.
 */
public class CommandManager {
    private List<Command> history = new ArrayList<>();

    public void execute(Command cmd) {
        cmd.execute();
        history.add(cmd);
    }

    public void saveHistory(String file) throws Exception {
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            for (Command c : history) {
                pw.println(c.getClass().getSimpleName());
            }
        }
    }

    public void replay() {
        for (Command c : history) {
            c.execute();
        }
    }

    public List<Command> getHistory() {
        return history;
    }

}
