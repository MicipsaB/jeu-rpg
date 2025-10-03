package rpg.dao;

import java.io.*;
import java.util.*;

/**
 * DAO pour persister les personnages via sérialisation.
 */
public class CharacterDAO implements DAO<CharacterData> {
    private static final String FILE = "characters.db";

    @Override
    public void save(CharacterData item) throws Exception {
        List<CharacterData> list = findAll();
        list.add(item);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
            oos.writeObject(list);
        }
    }

    @Override
    public CharacterData findByName(String name) throws Exception {
        for (CharacterData c : findAll()) {
            if (c.getName().equals(name)) return c;
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CharacterData> findAll() throws Exception {
        File f = new File(FILE);
        if (!f.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE))) {
            return (List<CharacterData>) ois.readObject();
        }
    }
}
