package com.acl.formaliteagricultureapi.models.enumeration;

/**
 * @author kol on 22/09/2024
 * @project formalite-agriculture-api
 */
public enum StatutDemande {

    DEMANDE("DEMANDE"),
    AUTORISER("AUTORISER"),
    DEMANDE_DEPOTAGE("DEMANDE_DEPOTAGE"),
    AUTORISATION_DEPOTAGE("AUTORISATION_DEPOTAGE"),
    DEPOTAGE("DEPOTAGE");

    private final String label;

    private StatutDemande(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
