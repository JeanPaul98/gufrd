package com.acl.formaliteenvironnementapi.models.enumeration;

public enum Etat {
    NON_SOUMIS("NON_SOUMIS"),
    SOUMIS("SOUMIS"),
    ACCEPTER("ACCEPTER"),
    TRAITER("TRAITER"),
    REJETER("REJETER"),
    PAYER("PAYER"),
    ANNULER("ANNULER");

    private final String label;

    private Etat(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
