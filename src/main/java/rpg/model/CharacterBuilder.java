package rpg.model;

import rpg.rules.GameSettings;

import java.util.List;

/**
 * Builder pour ConcreteCharacter.
 *
 * Contrats et comportements :
 * - Valeurs par défaut :
 *     name -> "Unnamed" (si l'utilisateur n'a pas appelé setName)
 *     stats -> 0
 * - Validation : build() appelle GameSettings.getInstance().validate(candidate)
 *     et remonte **toutes** les erreurs sous forme d'une ValidationException.
 * - Réutilisabilité : le builder peut être réutilisé après appel de reset().
 *     Sinon, la pratique recommandée est de créer une nouvelle instance par personnage.
 *
 * Exemple d'usage :
 *   CharacterBuilder b = new CharacterBuilder()
 *       .setName("Alice")
 *       .setStrength(10)
 *       .setAgility(5)
 *       .setIntelligence(5);
 *   ConcreteCharacter alice = b.build(); // lève ValidationException si invalid
 *
 */
public class CharacterBuilder {

    // Valeurs internes (defaults expliqués ci-dessus)
    private String name = null;        // si null -> "Unnamed" au build
    private int strength = 0;
    private int agility = 0;
    private int intelligence = 0;

    public CharacterBuilder() { }

    // Fluent setters
    public CharacterBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CharacterBuilder setStrength(int strength) {
        this.strength = strength;
        return this;
    }

    public CharacterBuilder setAgility(int agility) {
        this.agility = agility;
        return this;
    }

    public CharacterBuilder setIntelligence(int intelligence) {
        this.intelligence = intelligence;
        return this;
    }

    /**
     * Reset des valeurs du builder pour permettre la réutilisation de la même instance.
     * Après reset(), le builder revient aux valeurs par défaut (name=null => "Unnamed" au build).
     *
     * @return this (fluent)
     */
    public CharacterBuilder reset() {
        this.name = null;
        this.strength = 0;
        this.agility = 0;
        this.intelligence = 0;
        return this;
    }

    /**
     * Construit un ConcreteCharacter **validé**.
     * - Applique d'abord les valeurs par défaut (name -> "Unnamed" si absent).
     * - Demande à GameSettings de valider le candidat et récupère la liste complète d'erreurs.
     * - Si la liste n'est pas vide, lève ValidationException contenant toutes les erreurs.
     *
     * Contrat : build() retourne un ConcreteCharacter conforme aux règles globales,
     * sinon elle lève ValidationException (avec des messages exploitables).
     *
     * @return ConcreteCharacter valide
     * @throws ValidationException si la validation échoue (contient la liste des messages)
     */
    public ConcreteCharacter build() {
        // appliquer valeurs par défaut
        String finalName = (this.name == null) ? "Unnamed" : this.name.trim();

        // Créer un candidat (statut non validé)
        ConcreteCharacter candidate = new ConcreteCharacter(finalName,
                Math.max(0, this.strength),
                Math.max(0, this.agility),
                Math.max(0, this.intelligence));

        // Validation via GameSettings (retourne la liste de tous les problèmes)
        List<String> errors = GameSettings.getInstance().validate(candidate);

        if (errors != null && !errors.isEmpty()) {
            // Lancer une exception contenant toutes les erreurs (message actionnable)
            throw new ValidationException(errors);
        }

        // Si aucune erreur, renvoyer l'objet valide
        return candidate;
    }
}
