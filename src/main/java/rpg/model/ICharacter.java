package rpg.model;

import rpg.observer.CharacterObserver;

/**
 * Interface de base pour un personnage, permettant d'appliquer le Decorator.
 */
public interface ICharacter {
    String getName();
    double getPowerLevel();
    String getDescription();

    void addObserver(CharacterObserver o);
    void removeObserver(CharacterObserver o);
    void notifyObservers(String event);
}
