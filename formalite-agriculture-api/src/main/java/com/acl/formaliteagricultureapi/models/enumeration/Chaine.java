package com.acl.formaliteagricultureapi.models.enumeration;

/**
 * @author Zansouyé
 */
public enum Chaine {

    Import("IMPORT"),
    Export("EXPORT");

    private final String label;

    private Chaine(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
