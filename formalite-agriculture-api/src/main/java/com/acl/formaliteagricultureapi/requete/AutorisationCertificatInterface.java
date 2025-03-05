package com.acl.formaliteagricultureapi.requete;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

/**
 * @author kol on 04/09/2024
 * @project formalite-agriculture-api
 */
public interface AutorisationCertificatInterface {


    /*
    Début Formalite
     */
    public Long getIdFormalite();

    public Long getIdAgrement();

    public Long getIdCertificat();

    public double getMontantRedevance();

    public String getNumeroAgrementTransit();

    public String getCompteClient();

    public String getEtat();

    public String getNumGenerer();

    public String getChaine();

    public String getMotifRejet();

    /*
    Fin formalite
     */

    /*
    Debut Certificat
     */

    public String getLieuExpedition();

    public String getIdentification() ;

    public String getTraitement() ;

    public String getExpediteur() ;

    public String getDestinataire() ;

    public String getMoyenTransport();

    public String getAdresseDestinataire();

    public String getAdresseExpediteur();

    public String getLieuDestination();

    public String getLieuDechargement();

    public Date getDateDepart();

    public String getPaysOrigine();

    public String getPaysExpediteur();

    public String getLieuOrigine();

    public String getPaysDestination();

    public String getPosteFrontalier();

    public String getLieuChargement();

    public String getNif();
    /*
    Fin certificat
     */

    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateDemande();


    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateRejet();


    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateSoumise();


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

    @Temporal(TemporalType.TIMESTAMP)
    public Date getDatearrivee();

    //Fin Phytosanitaire

    //debut Type phyto
    public String getNumeroDossier();

    public String getTypeReference();

}
