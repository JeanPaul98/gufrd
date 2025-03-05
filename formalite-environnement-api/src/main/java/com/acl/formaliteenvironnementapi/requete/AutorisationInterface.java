package com.acl.formaliteenvironnementapi.requete;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

/**
 * @author kol on 11/09/2024
 * @project formalite-environnement-api
 */
public interface AutorisationInterface {

    public Long getIdFormalite();

    public Long getIdAutorisation();

    public  String getAtp();

    public String getNomNavire();

    public String getImo();

    public String getAffreteur();

    public String getEtat();

    public String getNumGenerer();

    public String getChaine();

    public String getCompteClient();

    public double getMontantRedevance();


    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateSoumission();

    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateAcceptation();


    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateRejet();

    public Date getDateDemande();


}
