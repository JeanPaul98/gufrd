package com.acl.formaliteenvironnementapi.repository;

import com.acl.formaliteenvironnementapi.models.Formalite;
import com.acl.formaliteenvironnementapi.requete.AutorisationInterface;
import com.acl.formaliteenvironnementapi.requette.CertificatInterfaces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kol on 11/09/2024
 * @project formalite-environnement-api
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
            "             f.CHAINE as chaine, f.ETAT as etat, f.NUM_GENERE as numGenerer,\n" +
            "                f.IMO_NAVIRE as imo, f.affreteur as affreteur , f.DATE_ACCEPTATION as dateAcceptation,\n" +
            "             f.DATE_SOUMISSION as dateSoumission, f.DATE_DMD as dateDemande,\n" +
            "              f.ID_AUTORISATION as idAutorisation,\n" +
            "              f.montant_redevance as montantRedevance, f.CODE_CLIENT AS compteClient\n" +
            "\n" +
            "             from USERFME.FORMALITE f, USERFME.AUTORISATION a, USERFME.TYPE_AUTORISATION t\n" +
            "            WHERE F.ID_AUTORISATION = a.ID\n" +
            "             and  a.ID_TYPE_AUTORISATION = t.ID\n" +
            "                   and f.ETAT = ?1 and t.REF = ?2\n" +
            "                  order by f.DATE_DMD desc", nativeQuery = true)
    List<AutorisationInterface> findInspection(String eta, String ref);
}
