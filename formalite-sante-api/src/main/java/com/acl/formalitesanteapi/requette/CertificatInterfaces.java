package com.acl.formalitesanteapi.requette;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

/**
 * @author kol on 09/09/2024
 * @project formalite-sante-api
 */
public interface CertificatInterfaces {

    public Long getIdFormalite();

    public  String getAtp();

    public String getNomNavire();

    public String getImmo();

    public String getAffreteur();

    public String getEtat();

    public String getNumGenerer();

    public String getChaine();

    public String getCompteClient();

    public String getNumeroEnregistrement();

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

    //Debut certificat transit

    public Long getIdCertificat();

}
