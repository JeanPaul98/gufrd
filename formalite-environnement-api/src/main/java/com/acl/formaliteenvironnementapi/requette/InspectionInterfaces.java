package com.acl.formaliteenvironnementapi.requette;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

/**
 * @author kol on 09/09/2024
 * @project formalite-sante-api
 */
public interface InspectionInterfaces {

    public Long getIdFormalite();

    public  String getAtp();

    public String getNomNavire();

    public String getImo();

    public String getAffreteur();

    public String getEtat();

    public String getNumGenerer();

    public String getChaine();

    public String getCompteClient();

    public String getNationalite();

    public Long getIdInspection();

    public String getProvenance();

    public String getDestination();

    public String getCommandant();

    public int getCargaison();

    public String getPavillon();

    public Date getDateReinspection();

    public String getNrt();

    public  int getTonnage();

    public int getOpDesinfection();

    public  int getOpDeratisation();

    public int getOpDesinsectisation();

    public String getDemandeur();

    public String getRemarque();

    public String getAdresseDemandeur();

    public Date getDateDeclaration();


    public double getMontantRedevance();


    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateSoumission();

    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateAcceptation();


    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateRejet();

   public Date getDateDemande();
}
