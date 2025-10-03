package tests;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import rpg.dao.CharacterDAO;
import rpg.dao.CharacterData;
import rpg.model.ConcreteCharacter;

import java.io.File;
import java.util.List;

/**
 * Tests unitaires pour CharacterDAO adaptés à la classe CharacterData fournie.
 */
public class DAOTest {

    private CharacterDAO dao;
    private static final String FILE = "characters.db";

    @BeforeEach
    void setup() throws Exception {
        // Supprime le fichier de persistance s'il existe pour garantir l'isolation des tests
        File f = new File(FILE);
        if (f.exists()) {
            if (!f.delete()) {
                // si impossible, tente de vider via DAO (mais normalement la suppression suffit)
                throw new IllegalStateException("Impossible de supprimer le fichier de test: " + FILE);
            }
        }
        dao = new CharacterDAO();
    }

    @Test
    void testSaveAndFindByName() throws Exception {
        ConcreteCharacter cc = new ConcreteCharacter("TestHero", 10, 5, 3);
        CharacterData data = new CharacterData(cc);

        dao.save(data);

        CharacterData found = dao.findByName("TestHero");
        assertNotNull(found, "Le personnage sauvegardé doit être retrouvé");
        assertEquals("TestHero", found.getName());
        assertEquals(cc.getPowerLevel(), found.getPowerLevel(), 1e-9, "Le powerLevel doit être préservé");
    }

    @Test
    void testFindAllReturnsAllSavedCharacters() throws Exception {
        CharacterData c1 = new CharacterData(new ConcreteCharacter("Hero1", 5, 3, 2));
        CharacterData c2 = new CharacterData(new ConcreteCharacter("Hero2", 8, 4, 6));

        dao.save(c1);
        dao.save(c2);

        List<CharacterData> all = dao.findAll();
        assertEquals(2, all.size(), "Deux personnages doivent être présents");
        assertTrue(all.stream().anyMatch(cd -> "Hero1".equals(cd.getName())));
        assertTrue(all.stream().anyMatch(cd -> "Hero2".equals(cd.getName())));
    }

    @Test
    void testFindAllOnEmptyFileReturnsEmptyList() throws Exception {
        List<CharacterData> all = dao.findAll();
        assertNotNull(all, "La liste ne doit pas être null");
        assertTrue(all.isEmpty(), "La liste doit être vide si aucun personnage n'est sauvegardé");
    }

    @Test
    void testFindByNameReturnsNullIfNotFound() throws Exception {
        dao.save(new CharacterData(new ConcreteCharacter("HeroX", 4, 4, 4)));
        CharacterData result = dao.findByName("Unknown");
        assertNull(result, "La recherche d'un personnage inexistant doit retourner null");
    }
}
