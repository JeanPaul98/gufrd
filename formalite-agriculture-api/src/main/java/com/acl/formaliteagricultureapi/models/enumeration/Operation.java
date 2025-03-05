package com.acl.formaliteagricultureapi.models.enumeration;

public enum Operation {

    EMPOTAGE("EMPOTAGE"),
    DEPOTAGE("DEPOTAGE"),
    AUTRE("AUTRE");

    private final String label;
    private Operation(String label) {
        this.label = label;
    }
    public String getLabel() {
        return label;
    }
}
