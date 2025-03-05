package com.acl.formaliteagricultureapi.models.enumeration;

/**
 * @author Zansouyé
 */
public enum Etat {
    NON_SOUMIS("NON_SOUMIS"),

    SOUMIS("SOUMIS"),
    ACCEPTE("ACCEPTE"),

    ACCEPTER("ACCEPTER"),

    TRAITE("TRAITE"),


    TRAITER("TRAITER"),

    REJETER("REJETER"),

    REJETE("REJETE"),
    PAYER("PAYER"),
    PAYE("PAYE"),


    ANNULER("ANNULER");


    private final String label;

    private Etat(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
