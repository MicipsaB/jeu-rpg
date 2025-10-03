package rpg.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Exception levée par le Builder si la validation échoue.
 * Contient la liste complète des messages d'erreur pour que la vue/controller
 * puisse les afficher de manière actionnable.
 */
public class ValidationException extends IllegalArgumentException {

    private final List<String> errors;

    /**
     * Constructeur principal.
     * @param errors liste non nulle de messages d'erreur (strings)
     */
    public ValidationException(List<String> errors) {
        super(buildMessage(errors));
        this.errors = new ArrayList<>(errors == null ? Collections.emptyList() : errors);
    }

    private static String buildMessage(List<String> errors) {
        if (errors == null || errors.isEmpty()) return "Validation failed (no details).";
        // Concatène les messages dans une chaîne lisible
        StringBuilder sb = new StringBuilder();
        sb.append("Validation failed :");
        for (String e : errors) {
            sb.append(" ").append(e).append(";");
        }
        return sb.toString();
    }

    /**
     * Retourne la liste immuable des erreurs.
     */
    public List<String> getErrors() {
        return Collections.unmodifiableList(errors);
    }
}
