package rpg.mvc;

import java.util.*;
import rpg.model.ICharacter;

/**
 * Modèle MVC stockant la liste des personnages.
 */
public class GameModel {
    private List<ICharacter> characters = new ArrayList<>();

    public void addCharacter(ICharacter c) { characters.add(c); }
    public List<ICharacter> getCharacters() { return characters; }
}
