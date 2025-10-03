package tests;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import rpg.command.AttackStrategy;
import rpg.command.BasicAttackStrategy;
import rpg.model.ConcreteCharacter;

/**
 * Tests unitaires pour BasicAttackStrategy.
 */
public class StrategyTest {

    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    @BeforeEach
    void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void testAttackerStrongerWins() {
        ConcreteCharacter strong = new ConcreteCharacter("Strong", 10, 5, 5);
        ConcreteCharacter weak = new ConcreteCharacter("Weak", 2, 1, 1);

        AttackStrategy strategy = new BasicAttackStrategy();
        strategy.attack(strong, weak);

        String output = outContent.toString();
        assertTrue(output.contains("Strong attaque Weak"), "Le message d'attaque doit être affiché");
        assertTrue(output.contains("Strong gagne l'échange"), "Le plus fort doit gagner l'échange");
    }

    @Test
    void testDefenderResistsIfStrongerOrEqual() {
        ConcreteCharacter attacker = new ConcreteCharacter("Attacker", 2, 2, 2);
        ConcreteCharacter defender = new ConcreteCharacter("Defender", 10, 5, 5);

        AttackStrategy strategy = new BasicAttackStrategy();
        strategy.attack(attacker, defender);

        String output = outContent.toString();
        assertTrue(output.contains("Attacker attaque Defender"), "Le message d'attaque doit être affiché");
        assertTrue(output.contains("Defender résiste"), "Le défenseur plus fort doit résister");
    }
}
