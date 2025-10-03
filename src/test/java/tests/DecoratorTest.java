package tests;

import org.junit.jupiter.api.Test;
import rpg.model.*;

import static org.junit.jupiter.api.Assertions.*;

public class DecoratorTest {

    @Test
    void testDecoratorDescriptionAndPower() {
        ICharacter base = new ConcreteCharacter("Alice", 5, 5, 5);
        ICharacter decorated = new InvisibilityDecorator(new FireResistDecorator(base));

        assertTrue(decorated.getDescription().contains("Invisibility"));
        assertTrue(decorated.getDescription().contains("Fire Resistance"));
        assertTrue(decorated.getPowerLevel() >= base.getPowerLevel());
    }


}
