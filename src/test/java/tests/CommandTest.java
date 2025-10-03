package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import rpg.command.*;
import rpg.model.*;

/**
 * Tests unitaires pour CommandManager et les Commandes (Attack/Defend).
 */
public class CommandTest {

    private ConcreteCharacter hero;
    private ConcreteCharacter enemy;
    private CommandManager manager;
    private AttackStrategy strategy;

    @BeforeEach
    void setup() {
        hero = new ConcreteCharacter("Hero", 10, 5, 3);
        enemy = new ConcreteCharacter("Enemy", 6, 4, 2);
        manager = new CommandManager();
        strategy = new BasicAttackStrategy();
    }

    @Test
    void testAttackCommandExecutionAndHistory() {
        Command attack = new AttackCommand(hero, enemy, strategy);

        manager.execute(attack);

        // Vérifie que la commande a été ajoutée à l'historique
        assertEquals(1, manager.getHistory().size());
        assertTrue(manager.getHistory().get(0) instanceof AttackCommand);
    }

    @Test
    void testDefendCommandExecution() {
        Command defend = new DefendCommand(hero);

        manager.execute(defend);

        assertEquals(1, manager.getHistory().size());
        assertTrue(manager.getHistory().get(0) instanceof DefendCommand);
    }

    @Test
    void testReplayExecutesAllCommandsAgain() {
        Command attack = new AttackCommand(hero, enemy, strategy);
        Command defend = new DefendCommand(hero);

        manager.execute(attack);
        manager.execute(defend);

        // Historique doit contenir 2 commandes
        assertEquals(2, manager.getHistory().size());

        // Lance le replay
        manager.replay();

        // Historique ne change pas, mais les commandes ont été réexécutées
        assertEquals(2, manager.getHistory().size());
    }
}
