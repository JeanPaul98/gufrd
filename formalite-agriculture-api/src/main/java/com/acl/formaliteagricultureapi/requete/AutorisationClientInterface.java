package com.acl.formaliteagricultureapi.requete;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

/**
 * @author kol on 21/08/2024
 * @project formalite-agriculture-api
 */
public interface AutorisationClientInterface {

    public Long getIdFormalite();

    public  String getAtp();

    public String getNomNavire();

    public String getImmo();

    public String getAffreteur();

    public String getEtat();

    public String getNumGenerer();

    public String getChaine();

    public String getCompteClient();

    public String getNumeroDossier();

    public double getMontantRedevance();


    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateDemande();


    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateRejet();


    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateSoumission();


    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateTraitement();


    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateAccepte();
    //Fin formalite

    //Debut Autorisation Depotage transit

    public Long getIdAutorisation();

    public String getConteneur();

    public String getProvenance();

    public String getTypeRegime();

    public String getNumeroAgrementTransit();

    public String getPoidnet();

    public String getDesignation();

    public String getNif();

    @Temporal(TemporalType.TIMESTAMP)
    public Date getDatearrivee();

    //Fin Autorisation

    //debut Type autorisation
    public String getTypeAutorisation();

    public String getTypeReference();

    public String getMotifRejet();

    public String getNomImportateur();

    public String getStatutPaiement();

    public String getNumeroBL();

    public Date getDateArrivee();

    public Date getDateEmbarquement();

    public String getStatutDemande();

    public Long getIdAgrement();

    public String getNumeroAutorisation();


}
