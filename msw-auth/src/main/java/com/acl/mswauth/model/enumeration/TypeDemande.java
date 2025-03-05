package com.acl.mswauth.model.enumeration;

/**
 * @author kol on 10/28/24
 * @project msw-auth
 */
public enum TypeDemande {
    OPERATEUR("OPERATEUR"),
    CLIENT_PAL("CLIENT_PAL");

    private final String label;

    private TypeDemande(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
