package com.acl.formaliteagricultureapi.requete;

import java.util.Date;

/**
 * @author kol on 10/21/24
 * @project formalite-agriculture-api
 */
public interface FormaliteStatistiqueInterface {

    public String getEntreprise();

    public String getNavire();

    public String getAffreteur();

    public String getNomDemandeur();

    public String getNomStructure();

    public String getTypeInspection();

    public Date getDateDeclaration();

    public Date getDateTraitement();
}
