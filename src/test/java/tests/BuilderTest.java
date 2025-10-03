package tests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rpg.model.CharacterBuilder;
import rpg.model.ConcreteCharacter;
import rpg.model.ValidationException;
import rpg.rules.GameSettings;

import static org.junit.jupiter.api.Assertions.*;

public class BuilderTest {

    @BeforeEach
    void setup() {
        GameSettings.getInstance().setMaxStatPoints(30); // pour permettre sum=25
    }


    @Test
    void testBuildValidCharacter() {
        ConcreteCharacter c = new CharacterBuilder()
                .setName("Alice")
                .setStrength(10)
                .setAgility(10)
                .setIntelligence(5)
                .build();
        assertEquals("Alice", c.getName());
    }

    @Test
    void testNameMandatory() {
        CharacterBuilder b = new CharacterBuilder()
                .setStrength(10)
                .setAgility(5)
                .setIntelligence(5);
        ValidationException ex = assertThrows(ValidationException.class, b::build);
        assertTrue(ex.getErrors().stream().anyMatch(msg -> msg.contains("nom")));
    }

    @Test
    void testStatsExceedMax() {
        GameSettings.getInstance().setMaxStatPoints(10);
        CharacterBuilder b = new CharacterBuilder()
                .setName("Bob")
                .setStrength(20)
                .setAgility(5)
                .setIntelligence(5);
        ValidationException ex = assertThrows(ValidationException.class, b::build);
        assertTrue(ex.getErrors().stream().anyMatch(msg -> msg.contains("dépasse")));
    }
}
