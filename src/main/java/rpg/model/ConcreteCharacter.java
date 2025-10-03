package rpg.model;

import java.util.ArrayList;
import java.util.List;
import rpg.observer.CharacterObserver;

/**
 * Classe de base représentant un personnage.
 */
public class ConcreteCharacter implements ICharacter {
    private String name;
    private int strength;
    private int agility;
    private int intelligence;
    private transient List<CharacterObserver> observers = new ArrayList<>();

    public ConcreteCharacter(String name, int strength, int agility, int intelligence) {
        this.name = name;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
    }

    @Override
    public String getName() { return name; }

    public int getStrength() { return strength; }
    public int getAgility() { return agility; }
    public int getIntelligence() { return intelligence; }

    @Override
    public double getPowerLevel() {
        return strength * 1.5 + agility * 1.2 + intelligence * 1.3;
    }

    @Override
    public String getDescription() {
        return String.format("%s [S:%d A:%d I:%d] (Power: %.1f)",
                name, strength, agility, intelligence, getPowerLevel());
    }

    @Override
    public void addObserver(CharacterObserver o) { observers.add(o); }
    @Override
    public void removeObserver(CharacterObserver o) { observers.remove(o); }
    @Override
    public void notifyObservers(String event) {
        for (CharacterObserver o : new ArrayList<>(observers)) {
            o.onCharacterChanged(this, event);
        }
    }
}
