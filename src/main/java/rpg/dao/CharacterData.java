package rpg.dao;

import java.io.Serializable;

/**
 * Classe sérialisable représentant un personnage pour persistance.
 */
public class CharacterData implements Serializable {
    private String name;
    private double powerLevel;

    public CharacterData(rpg.model.ICharacter c) {
        this.name = c.getName();
        this.powerLevel = c.getPowerLevel();
    }

    public String getName() { return name; }
    public double getPowerLevel() { return powerLevel; }
}
