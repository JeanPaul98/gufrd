package com.acl.formaliteagricultureapi.models.enumeration;

/**
 * @author kol on 03/10/2024
 * @project formalite-agriculture-api
 */
public enum TypeRegime {

    CONSOMMATION("CONSOMMATION"),
    TRANSIT("TRANSIT");

    private final String label;

    private TypeRegime(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
