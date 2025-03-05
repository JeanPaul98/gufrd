package com.acl.mswauth.model.enumeration;

/**
 * @author kol on 10/28/24
 * @project msw-auth
 */
public enum Statut {
    NON_TROUVE("NON_TROUVE"),
    EN_ATTENTE("EN_ATENTE"),
    TERMINE("TERMINE");


    private final String label;

    private Statut(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
