package rpg.rules;

import rpg.model.ConcreteCharacter;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Singleton thread-safe (DCL) contenant les règles globales du jeu.
 *
 * - Centralise toutes les limites (maxStatPoints, maxCharactersPerGroup).
 * - Fournit une méthode de validation qui retourne la liste complète des erreurs
 *   (plutôt qu'un simple boolean), afin que l'appelant puisse afficher toutes
 *   les corrections nécessaires à l'utilisateur.
 *
 * Configuration :
 * - Possibilité de charger depuis un fichier properties (loadFromFile).
 * - Possibilité d'ajuster à l'exécution via setters (utile pour les tests).
 */
public class GameSettings {

    private static volatile GameSettings instance;

    private int maxStatPoints = 30;
    private int maxCharactersPerGroup = 10;

    private GameSettings() { }

    public static GameSettings getInstance() {
        if (instance == null) {
            synchronized (GameSettings.class) {
                if (instance == null) instance = new GameSettings();
            }
        }
        return instance;
    }

    /**
     * Charger la configuration depuis un fichier .properties (optionnel).
     * Exemples de propriétés : maxStatPoints=40
     *
     * @param path chemin vers le fichier properties
     * @throws IOException si le fichier n'est pas lisible
     */
    public static void loadFromFile(String path) throws IOException {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(path)) {
            props.load(fis);
        }
        GameSettings gs = getInstance();
        if (props.getProperty("maxStatPoints") != null) {
            gs.maxStatPoints = Integer.parseInt(props.getProperty("maxStatPoints"));
        }
        if (props.getProperty("maxCharactersPerGroup") != null) {
            gs.maxCharactersPerGroup = Integer.parseInt(props.getProperty("maxCharactersPerGroup"));
        }
    }

    // Getters / setters (centralise la source de vérité)
    public int getMaxStatPoints() { return maxStatPoints; }
    public void setMaxStatPoints(int maxStatPoints) { this.maxStatPoints = maxStatPoints; }

    public int getMaxCharactersPerGroup() { return maxCharactersPerGroup; }
    public void setMaxCharactersPerGroup(int maxCharactersPerGroup) { this.maxCharactersPerGroup = maxCharactersPerGroup; }

    /**
     * Valide un ConcreteCharacter et retourne la liste complète des erreurs.
     * - Vérifie le nom (non vide, pas "Unnamed" si l'on exige un nom explicite)
     * - Vérifie la somme des stats vs maxStatPoints
     * - Vérifie l'absence de stats négatives
     *
     * Important : cette méthode ne lève pas d'exception — elle retourne la liste
     * des problèmes (vide si valide) ; c'est l'appelant (ex : Builder) qui décidera
     * d'échouer en lançant une ValidationException.
     *
     * @param c candidate à valider (ConcreteCharacter)
     * @return liste des messages d'erreur (vide si OK)
     */
    public List<String> validate(ConcreteCharacter c) {
        List<String> errors = new ArrayList<>();

        // Nom : obligatoire et non générique
        if (c.getName() == null || c.getName().trim().isEmpty()) {
            errors.add("Le nom est obligatoire (non vide).");
        } else {
            // Si le builder a laissé la valeur par défaut "Unnamed", considérer comme absent
            if ("Unnamed".equals(c.getName().trim())) {
                errors.add("Le nom doit être explicite (évitez 'Unnamed').");
            }
        }

        // Stats non négatives
        if (c.getStrength() < 0 || c.getAgility() < 0 || c.getIntelligence() < 0) {
            errors.add("Les caractéristiques (strength/agility/intelligence) doivent être non négatives.");
        }

        // Somme des stats
        int sum = c.getStrength() + c.getAgility() + c.getIntelligence();
        if (sum > maxStatPoints) {
            errors.add(String.format("La somme des caractéristiques (%d) dépasse la limite (%d).",
                    sum, maxStatPoints));
        }

        return errors;
    }

    /**
     * Compatibilité : méthode simple renvoyant true si aucune erreur.
     */
    public boolean isValid(ConcreteCharacter c) {
        return validate(c).isEmpty();
    }
}
