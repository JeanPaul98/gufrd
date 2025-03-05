package com.acl.formaliteagricultureapi.repository;

import com.acl.formaliteagricultureapi.models.Formalite;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.requete.AutorisationCertificatInterface;
import com.acl.formaliteagricultureapi.requete.AutorisationClientInterface;
import com.acl.formaliteagricultureapi.requete.FormaliteStatistiqueInterface;
import com.acl.formaliteagricultureapi.requete.PhytosanitaireClientInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Zansouyé
 */
@Repository
public interface FormaliteRepository extends JpaRepository<Formalite, Long> {


    @Query(value = "select  f.id as idFormalite, f.NUM_ATP_NAVIRE as atp , f.NOM_NAVIRE as nomNavire, f.AFFRETEUR as affreteur, f.IMO_NAVIRE as immo,\n" +
            "f.CHAINE as chaine, f.ETAT as etat, f.NUM_GENERE as numGenerer , f.DATE_SOUMISE as dateSoumission, " +
            "f.DATE_REJET as dateRejet, f.DATE_TRAITEMENT as dateTraitement, f.DATE_ACCEPTE as dateAccepte, f.ID_AUTORISATION_AGREMENT " +
            "as idAgrement , f.TYPE_REGIME as typeRegime , f.NUMERO_AGREMENT_TRANSIT as numeroAgrementTransit, " +
            "f.NIF as nif , f.NUMERO_DOSSIER as numeroDossier,  \n" +
            " f.montant_redevance as montantRedevance, f.CODE_CLIENT as compteClient, f.STATUT_DEMANDE as statutDemande , " +
            "f.MOTIF_ANNULATION as motifRejet , f.NOM_IMPORTATEUR as nomImportateur, f.STATUT_PAIEMENT as statutPaiement,  \n" +
            "f.DATE_DMD as dateDemande, a.CONTENEUR as conteneur, a.DATE_ARRIVEE as dateArrivee, t.LIBELLE as typeAutorisation,\n" +
            "a.ID as idAutorisation, a.PROVENANCE as provenance, a.NUMERO_BL as numeroBL, " +
            "a.NUMERO_AUTORISATION as numeroAutorisation,  " +
            " a.DATE_EMBARQUEMENT as dateEmbarquement, a.DATE_ARRIVEE as dateArrivee , t.REF as typeReference\n" +
            "from userfma.formalite f , userfma.autorisation a , userfma.type_autorisation t\n" +
            "where f.ID_AUTORISATION= a.ID\n" +
            "and a.ID_TYPE_AUTORISATION = t.ID\n " +
            "and f.etat = ?1 and t.ref= ?2 " +
            "order by f.DATE_DMD desc", nativeQuery = true)
    List<AutorisationClientInterface> findAutorisationClientList(String eta, String ref);



    @Query(value = "select  f.id as idFormalite, f.NUM_ATP_NAVIRE as atp , f.NOM_NAVIRE as nomNavire, f.AFFRETEUR as affreteur, f.IMO_NAVIRE as immo,\n" +
            "f.CHAINE as chaine, f.ETAT as etat, f.NUM_GENERE as numGenerer , f.DATE_SOUMISE as dateSoumission, " +
            "f.DATE_REJET as dateRejet, f.DATE_TRAITEMENT as dateTraitement, f.DATE_ACCEPTE as dateAccepte, f.ID_AUTORISATION_AGREMENT " +
            "as idAgrement , f.NUMERO_DOSSIER as numeroDossier, " +
            " f.montant_redevance as montantRedevance, f.CODE_CLIENT as compteClient, f.STATUT_DEMANDE as statutDemande , " +
            "f.MOTIF_ANNULATION as motifRejet , f.NOM_IMPORTATEUR as nomImportateur, f.STATUT_PAIEMENT as statutPaiement,  \n" +
            "f.DATE_DMD as dateDemande, a.CONTENEUR as conteneur, a.DATE_ARRIVEE as dateArrivee, t.LIBELLE as typeAutorisation,\n" +
            "a.ID as idAutorisation, a.PROVENANCE as provenance, " +
            " a.DATE_EMBARQUEMENT as dateEmbarquement, a.DATE_ARRIVEE as dateArrivee  ,t.REF as typeReference\n" +
            "from userfma.formalite f , userfma.autorisation a , userfma.type_autorisation t\n" +
            "where f.ID_AUTORISATION= a.ID\n" +
            "and a.ID_TYPE_AUTORISATION = t.ID\n " +
            "and f.etat = ?1 and t.ref= ?2\n " +
            "and f.CODE_CLIENT = ?3\n" +
            "order by f.DATE_DMD desc", nativeQuery = true)
    List<AutorisationClientInterface> findAutorisationClientListByCompte(String eta, String ref, String compteClient);

    @Query(value = "select  f.id as idFormalite, f.NUM_ATP_NAVIRE as atp, f.NOM_NAVIRE as nomNavire,\n" +
            "        f.CHAINE as chaine, f.ETAT as etat, f.NUM_GENERE as numGenerer, f.NUMERO_DOSSIER as numeroDossier, \n" +
             " f.IMO_NAVIRE as imo, f.affreteur as affreteur , f.DATE_ACCEPTE as dateAccepte ," +
            "        f.DATE_SOUMISE as dateSoumission, f.DATE_DMD as dateDemande,\n" +
            "        f.ID_INSPECTION as idPhytosanitaire, a.NOM_DEMANDEUR as nomDemandeur, " +
            "f.DATE_TRAITEMENT as dateTraitement,f.DATE_REJET as dateRejet, f.STATUT_PAIEMENT as statutPaiement, " +
            " f.MOTIF_ANNULATION as motifRejet , f.NOM_IMPORTATEUR as nomImportateur , f.DATE_REJET as dateRejet ," +
            " f.montant_redevance as montantRedevance, f.CODE_CLIENT AS compteClient, \n" +
            "        a.ADRESSE_DEMANDEUR as adresseDemandeur," +
            "a.PROFESSION_DEMANDEUR as professionDemandeur, a.NOM_DESTINATAIRE as nomDestinataire,\n" +
            "        a.ADRESSE_DESTINATAIRE as adresseDestinataire , t.LIBELLE as typePhytosanitaire, \n" +
            "a.STRUCTURE_DEMANDEUR as structureDemandeur, a.TYPE_CARGAISON as typeCargaison , \n" +
            "   a.LIEU_INSPECTION as lieuInspection, a.DATE_PREVUE_INSPECTION as datePrevueInspection " +
            "from USERFMA.FORMALITE f, USERFMA.PHYTOSANITAIRE a, USERFMA.TYPE_INSPPHYTO t\n" +
            "WHERE F.ID_INSPECTION = a.ID\n" +
            "and  a.ID_TYPE_INSPECT_PHYTO = t.ID\n" +
            "and f.ETAT = ?1 and t.REF = ?2\n" +
            "order by f.DATE_DMD desc", nativeQuery = true)
    List<PhytosanitaireClientInterface> findPhytosanitaireClientList(String eta, String ref);


    @Query(value = "select  f.id as idFormalite, f.NUM_ATP_NAVIRE as atp, f.NOM_NAVIRE as nomNavire,\n" +
            "        f.CHAINE as chaine, f.ETAT as etat, f.NUM_GENERE as numGenerer, f.NIF as nif , " +
            "f.ID_AUTORISATION_AGREMENT as agrement , f.NUMERO_DOSSIER as numeroDossier,    \n" +
            " f.IMO_NAVIRE as imo, f.affreteur as affreteur , f.DATE_ACCEPTE as dateAccepte , " +
            "        f.DATE_SOUMISE as dateSoumission,f.DATE_REJET as dateRejet, f.DATE_DMD as dateDemande,\n" +
            "        f.ID_INSPECTION as idPhytosanitaire, a.NOM_DEMANDEUR as nomDemandeur, " +
            "f.DATE_TRAITEMENT as dateTraitement, f.STATUT_PAIEMENT as statutPaiement, " +
            " f.MOTIF_ANNULATION as motifRejet , f.NOM_IMPORTATEUR as nomImportateur , f.DATE_REJET as dateRejet ," +
            " f.montant_redevance as montantRedevance, f.CODE_CLIENT AS compteClient, \n" +
            "        a.ADRESSE_DEMANDEUR as adresseDemandeur," +
            "a.PROFESSION_DEMANDEUR as professionDemandeur, a.NOM_DESTINATAIRE as nomDestinataire,\n" +
            "        a.ADRESSE_DESTINATAIRE as adresseDestinataire , t.LIBELLE as typePhytosanitaire, \n" +
            "a.STRUCTURE_DEMANDEUR as structureDemandeur, a.TYPE_CARGAISON as typeCargaison , \n" +
            "   a.LIEU_INSPECTION as lieuInspection, a.DATE_PREVUE_INSPECTION as datePrevueInspection " +
            "from USERFMA.FORMALITE f, USERFMA.PHYTOSANITAIRE a, USERFMA.TYPE_INSPPHYTO t\n" +
            "WHERE F.ID_INSPECTION = a.ID\n" +
            "and  a.ID_TYPE_INSPECT_PHYTO = t.ID\n" +
            "and f.ETAT = ?1 and t.REF = ?2\n" +
            "and f.CODE_CLIENT = ?3\n" +
            "order by f.DATE_DMD desc", nativeQuery = true)
    List<PhytosanitaireClientInterface> findPhytosanitaireClientByCompte(String eta, String ref,String compte);


    @Query(value = "select  f.id as idFormalite, f.NUM_ATP_NAVIRE as atp, f.NOM_NAVIRE as nomNavire,\n" +
            "        f.CHAINE as chaine, f.ETAT as etat, f.NUM_GENERE as numGenerer,f.STATUT_PAIEMENT as statutPaiement, " +
            " f.ID_AUTORISATION_AGREMENT as agrement, f.NUMERO_DOSSIER as numeroDossier,  \n" +
            " f.IMO_NAVIRE as imo, f.affreteur as affreteur , f.DATE_ACCEPTE as dateAccepte ," +
            "        f.DATE_SOUMISE as dateSoumise,f.DATE_REJET as dateRejet, f.DATE_DMD as dateDemande, f.NIF as nif , \n" +
            "        f.ID_CERTIFICAT as idCertificat,f.MOTIF_ANNULATION as motifRejet , " +
            " f.montant_redevance as montantRedevance, f.CODE_CLIENT AS compteClient, \n" +
            "        a.traitement as traitement , a.IDENTIFICATION as identification, " +
            "a.MOYEN_TRANSPORT as moyenTransport, a.EXPEDITEUR as expediteur ,\n" +
            "        a.NOM_DESTINAT as destinataire , t.LIBELLE as typeCertificat, \n" +
            "   a.LIEU_EXPEDITION as lieuExpedition , a.LIEU_DECHARGEMENT as lieuDechargement," +
            " a.ADRESSE_DESTINAT as adresseDestinataire, a.ADRESSE_EXPE as adresseExpediteur, " +
            "a.PAYS_EXPEDITEUR as paysExpediteur, a.POSTE_FRONTALIER_PREVU as posteFrontalier, a.PAYS_ORIGINE as" +
            " paysOrigine, a.LIEU_ORIGINE as lieuOrigine, a.PAYS_DESTINATAIRE as paysDestination , a.LIEU_CHARGEMENT " +
            "as lieuChargement , a.DATE_DEPART as dateDepart " +
            "from USERFMA.FORMALITE f, USERFMA.CERTIFICAT a, USERFMA.TYPE_CERTIFICAT t\n" +
            "WHERE F.ID_CERTIFICAT = a.ID\n" +
            "and  a.ID_TYPE_CERTIFICAT = t.ID\n" +
            "and f.ETAT = ?1 and t.REF = ?2\n" +
            "order by f.DATE_DMD desc", nativeQuery = true)
    List<AutorisationCertificatInterface> findCerficatClientList(String eta, String ref);


    Optional<Formalite> findById(Long id);




@Query(value = "select  d from  Formalite  d where  d.numGenere = ?1")
    Optional<Formalite> findByNumGenerer(String numero);

   Optional<Formalite> findByEtat(String etat);

    @Query(value = "select d.NOM_IMPORTATEUR as entreprise ,d.NOM_NAVIRE as navire, d.AFFRETEUR as affreteur, p.NOM_DEMANDEUR as nomDemandeur, s.LIBELLE as nomStructure , tp.LIBELLE as typeInspection,\n" +
            "        d.ETAT as etat, d.DATE_DMD as dateDeclaration, d.DATE_TRAITEMENT as dateTraitement\n" +
            "from USERFMA.FORMALITE d , USERFMA.PHYTOSANITAIRE p, USERFMA.STRUCTURE s , USERFMA.TYPE_INSPPHYTO tp\n" +
            "WHERE  d.ID_STRUCTURE = s.ID and p.ID_TYPE_INSPECT_PHYTO = tp.ID\n" +
            "and d.ID_INSPECTION = p.ID " +
            "and d.ETAT = ?1  ", nativeQuery = true)
    List<FormaliteStatistiqueInterface> findAllStatistiqueByPhyto(String etat);
}
