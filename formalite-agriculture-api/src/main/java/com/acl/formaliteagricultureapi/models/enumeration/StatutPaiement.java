package com.acl.formaliteagricultureapi.models.enumeration;

/**
 * @author kol on 12/09/2024
 * @project formalite-agriculture-api
 */
public enum StatutPaiement {


    PAYER("PAYER"),
    PAYE("PAYE"),
    RIEN_A_PAYER("RIEN_A_PAYER"),
    IMPAYER("NON_PAYER"),
    EXEMPTER("EXEMPTER"),
    A_PAYER("A_PAYER"),
    ECHEC("ANNULER");

    private final String label;

    private StatutPaiement(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
