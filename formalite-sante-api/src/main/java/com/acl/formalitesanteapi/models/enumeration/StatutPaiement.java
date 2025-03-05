package com.acl.formalitesanteapi.models.enumeration;

/**
 * @author kol on 12/09/2024
 * @project formalite-agriculture-api
 */
public enum StatutPaiement {


    PAYER("PAYER"),
    IMPAYER("NON_PAYER"),
    EXEMPTER("EXEMPTER"),
    ECHEC("ANNULER");

    private final String label;

    private StatutPaiement(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
