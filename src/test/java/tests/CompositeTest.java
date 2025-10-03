package tests;

import org.junit.jupiter.api.Test;
import rpg.composite.GroupComposite;
import rpg.composite.CharacterLeaf;
import rpg.model.ConcreteCharacter;

import static org.junit.jupiter.api.Assertions.*;

public class CompositeTest {

    @Test
    void testGroupPowerAggregation() {
        GroupComposite group = new GroupComposite("Team");
        group.add(new CharacterLeaf(new ConcreteCharacter("Alice", 5, 5, 5)));
        group.add(new CharacterLeaf(new ConcreteCharacter("Bob", 3, 3, 3)));

        assertTrue(group.getPowerLevel() >= 16); // somme des powerLevels
    }
}
