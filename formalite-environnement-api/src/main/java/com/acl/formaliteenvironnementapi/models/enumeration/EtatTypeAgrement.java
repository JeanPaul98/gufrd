package com.acl.formaliteenvironnementapi.models.enumeration;

/**
 * @author Zansouyé on 25/09/2024
 * @project formalite-agriculture-api
 */
public enum EtatTypeAgrement {

    CERTIFICAT("CERTIFICAT"),

    AUTORISATION("AUTORISATION");

    private final String label;

    private EtatTypeAgrement(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
