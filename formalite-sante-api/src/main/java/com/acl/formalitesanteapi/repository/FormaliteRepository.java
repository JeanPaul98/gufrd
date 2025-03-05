package com.acl.formalitesanteapi.repository;

import com.acl.formalitesanteapi.models.Formalite;
import com.acl.formalitesanteapi.requette.CertificatInterfaces;
import com.acl.formalitesanteapi.requette.InspectionInterfaces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kol on 08/09/2024
 * @project formalite-sante-api
 */
@Repository
public interface FormaliteRepository extends JpaRepository<Formalite, Long> {


    @Query(value = "select  f.id as idFormalite, f.NUM_ATP_NAVIRE as atp, f.NOM_NAVIRE as nomNavire,\n" +
            "              f.CHAINE as chaine, f.ETAT as etat, f.NUM_GENERE as numGenerer,\n" +
            "             f.IMO_NAVIRE as imo, f.affreteur as affreteur , f.DATE_ACCEPTATION as dateAccepte,\n" +
            "                  f.DATE_SOUMISSION as dateSoumise, f.DATE_DMD as dateDemande,\n" +
            "                f.ID_CERTIFICAT as idCertificat,\n" +
            "             f.montant_redevance as montantRedevance, f.CODE_CLIENT AS compteClient,\n" +
            "               a.NUMERO_ENREGISTREMENT as numeroEnregistrement\n" +
            "            from USERFMS.FORMALITE f, USERFMS.CERTIFICAT a, USERFMS.TYPE_CERTIFICAT  t\n" +
            "            WHERE F.ID_CERTIFICAT = a.ID\n" +
            "            and  a.ID_TYPE_CERTIFICAT = t.ID\n" +
            "            and f.ETAT = ?1 and t.REF = ?2\n" +
            "            order by f.DATE_DMD desc", nativeQuery = true)
    List<CertificatInterfaces> findCertificat(String eta, String ref);


    @Query(value = "select  f.id as idFormalite, f.NUM_ATP_NAVIRE as atp, f.NOM_NAVIRE as nomNavire,\n" +
            "              f.CHAINE as chaine, f.ETAT as etat, f.NUM_GENERE as numGenerer,\n" +
            "             f.IMO_NAVIRE as imo, f.affreteur as affreteur , f.DATE_ACCEPTATION as dateAcceptation,\n" +
            "                  f.DATE_SOUMISSION as dateSoumission, f.DATE_DMD as dateDemande,\n" +
            "                f.ID_INSPECTION as idInspection,\n" +
            "             f.montant_redevance as montantRedevance, f.CODE_CLIENT AS compteClient,\n" +
            "               a.NATIONALITE as nationalite, a.PROVENANCE as provenance,\n" +
            "               a.DEMANDEUR as demandeur, a.ADRESSE_DEMANDEUR as adresseDemandeur,\n" +
            "               a.COMMANDANT as commandant, a.DESTINATION as destination , a.DATE_DECLARATION as dateDeclaration, " +
            "               a.PAVILLON as pavillon, a.NRT as nrt, a.DATE_REINSPECTION , a.TONNAGE as tonnage ," +
            "               a.CARGAISON as cargaison ," +
            "                  a.OP_DESINFECTION as opDesinfection, a.OP_DERATISATION as opDeratisation , " +
            "                  a.OP_DESINSECTISATION as opDesinsectisation " +
            "            from USERFMS.FORMALITE f, USERFMS.INSPECTION a, USERFMS.TYPE_INSPECTION  t\n" +
            "            WHERE F.ID_INSPECTION = a.ID\n" +
            "            and  a.ID_TYPE_INSPECTION = t.ID\n" +
            "            and f.ETAT = ?1 and t.REF = ?2\n" +
            "            order by f.DATE_DMD desc", nativeQuery = true)
    List<InspectionInterfaces> findInspection(String eta, String ref);

}
