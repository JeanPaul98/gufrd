package com.acl.formaliteagricultureapi.requete;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.ToString;

import java.util.Date;

/**
 * @author kol on 21/08/2024
 * @project formalite-agriculture-api
 */

public interface PhytosanitaireClientInterface {

    public Long getIdFormalite();

    public Long getIdPhytosanitaire();

    public  String getAtp();

    public String getNomNavire();

    public String getImo();

    public String getAffreteur();

    public String getNif();

    public String getEtat();

    public String getNumGenerer();

    public String getChaine();

    public String getLieuInspection();

    public String getTypePhytosanitaire();

    public double getMontantRedevance();

    public String getCompteClient();

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

    public String getNomDemandeur();

    public String getAdresseDemandeur();

    public String getProfessionDemandeur();

    public Date getDatePrevueInspection();

    public String getNomImportateur();

    public String getMotifRejet();

    public String getStatutPaiement();

    public String getStructureDemandeur();

    public String getTypeCargaison();

    @Temporal(TemporalType.TIMESTAMP)
    public Date getDatearrivee();

    //Fin Phytosanitaire

    //debut Type phyto

    public String getNumeroDossier();

    public String getTypeReference();

}
