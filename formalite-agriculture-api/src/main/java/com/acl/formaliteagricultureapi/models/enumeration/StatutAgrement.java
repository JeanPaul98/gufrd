package com.acl.formaliteagricultureapi.models.enumeration;

/**
 * @author Zansouyé on 26/09/2024
 * @project formalite-agriculture-api
 */
public enum StatutAgrement {

    EN_COURS("EN_COURS"),
    SUSPENDU("SUSPENDU"),
    RETIRER("RETIRER"),
    EXPIRER("EXPIRER"),
    RENOUVELER("RENOUVELER");

    private final String label;

    private StatutAgrement(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
