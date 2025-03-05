package com.acl.vbs.repositories;

import com.acl.vbs.entities.BonEntreeCamion;
import com.acl.vbs.projections.BonEntreeCamionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BonEntreeCamionRepository extends JpaRepository<BonEntreeCamion, String> {
    @Query(value = """
            SELECT *
            FROM (SELECT bec.NUM_BON_ENTREE_CAMION                             as numBonEntreeCamion,
                         bec.DATE_BON_ENTREE                                   as dateBonEntree,
                         bec.IMMATRICULATION                                   as immatriculation,
                         bec.NOM_CONDUCTEUR                                    as nomConducteur,
                         bec.remorque                                          as remorque,
                         bec.POIDS_VIDE                                        as poidsVide,
                         bec.POIDS_CHARGE                                      as poidsCharge,
                         bec.STATUT_PAYEMENT                                   as statutPayement,
                         bec.DATE_PAYEMENT                                     as datePayement,
                         bec.OBSERVATION_PESAGE                                as observationPesage,
                         c3.RAISON_SOCIAL_CLIENT                               as raisonSocialTransitaire,
                         c3.NOM_RESPONSABLE                                    as nomTransitaire,
                         p.NOM_PAYS                                            as libellePays,
                         CASE
                             WHEN bec.POIDS_CHARGE IS NULL THEN NULL
                             WHEN (bec.POIDS_CHARGE - NVL(bec.POIDS_VIDE, 0)) > 25 THEN
                                 (bec.POIDS_CHARGE - NVL(bec.POIDS_VIDE, 0)) * S.REDEVANCE
                             ELSE
                                 5650
                             END                                               as redevance,
                         ROW_NUMBER() OVER (ORDER BY bec.DATE_BON_ENTREE DESC) AS rownum_
                  FROM SIPEDBA.BON_ENTREE_CAMION bec
                           INNER JOIN SIPEDBA.SITE S on bec.SITE_PESAGE = S.ID_SITE
                           INNER JOIN SIPEDBA.CONCESSIONNAIRE C on S.CONCESSIONNAIRE = C.ID_CLIENT
                           INNER JOIN SIPEDBA.CLIENT C2 on C.ID_CLIENT = C2.ID_CLIENT
                           LEFT JOIN SIPEDBA.TRANSITAIRE t on bec.TRANSITAIRE = t.ID_CLIENT
                           LEFT JOIN SIPEDBA.CLIENT c3 on t.ID_CLIENT = c3.ID_CLIENT
                           INNER JOIN SIPEDBA.CAMION c4 on bec.IMMATRICULATION = c4.IMMATRICULATION
                           INNER JOIN SIPEDBA.PAYS p on c4.CODE_PAYS = p.CODE_PAYS
                  WHERE C2.COMPTE_CLIENT = :compteClient
                    AND bec.CODE_SENS_TRAFIC = :sensTrafic)
            WHERE rownum_ BETWEEN :start AND :end""", nativeQuery = true)
    List<BonEntreeCamionProjection> listCamionsAttendus(String compteClient, String sensTrafic, int start, int end);

    @Query(value = """
            SELECT bec.NUM_BON_ENTREE_CAMION as numBonEntreeCamion,
                   bec.DATE_BON_ENTREE       as dateBonEntree,
                   bec.IMMATRICULATION       as immatriculation,
                   bec.NOM_CONDUCTEUR        as nomConducteur,
                   bec.remorque              as remorque,
                   bec.POIDS_VIDE            as poidsVide,
                   bec.POIDS_CHARGE          as poidsCharge,
                   bec.STATUT_PAYEMENT       as statutPayement,
                   bec.DATE_PAYEMENT         as datePayement,
                   bec.OBSERVATION_PESAGE    as observationPesage,
                   c3.RAISON_SOCIAL_CLIENT   as raisonSocialTransitaire,
                   c3.NOM_RESPONSABLE        as nomTransitaire,
                   p.NOM_PAYS                as libellePays,
                   CASE
                       WHEN bec.POIDS_CHARGE IS NULL THEN NULL
                       WHEN (bec.POIDS_CHARGE - NVL(bec.POIDS_VIDE, 0)) > 25 THEN
                           (bec.POIDS_CHARGE - NVL(bec.POIDS_VIDE, 0)) * S.REDEVANCE
                       ELSE
                           5650
                       END                   as redevance
            FROM SIPEDBA.BON_ENTREE_CAMION bec
                     INNER JOIN SIPEDBA.SITE S on bec.SITE_PESAGE = S.ID_SITE
                     INNER JOIN SIPEDBA.CONCESSIONNAIRE C on S.CONCESSIONNAIRE = C.ID_CLIENT
                     INNER JOIN SIPEDBA.CLIENT C2 on C.ID_CLIENT = C2.ID_CLIENT
                     LEFT JOIN SIPEDBA.TRANSITAIRE t on bec.TRANSITAIRE = t.ID_CLIENT
                     LEFT JOIN SIPEDBA.CLIENT c3 on t.ID_CLIENT = c3.ID_CLIENT
                     INNER JOIN SIPEDBA.CAMION c4 on bec.IMMATRICULATION = c4.IMMATRICULATION
                     INNER JOIN SIPEDBA.PAYS p on c4.CODE_PAYS = p.CODE_PAYS
            WHERE C2.COMPTE_CLIENT = :compteClient
              AND bec.CODE_SENS_TRAFIC = :sensTrafic
            ORDER BY bec.DATE_BON_ENTREE DESC""", nativeQuery = true)
    List<BonEntreeCamionProjection> listCamionsAttendus(String compteClient, String sensTrafic);

    @Query(value = """
            SELECT *
            FROM (SELECT bec.*, ROW_NUMBER() OVER (ORDER BY bec.DATE_BON_ENTREE DESC) AS rownum_
                  FROM SIPEDBA.BON_ENTREE_CAMION bec
                           JOIN SIPEDBA.TRANSITAIRE t ON bec.TRANSITAIRE = t.ID_CLIENT
                           JOIN SIPEDBA.CLIENT c ON t.ID_CLIENT = c.ID_CLIENT
                  WHERE c.COMPTE_CLIENT = :compteClient)
            WHERE rownum_ BETWEEN :start AND :end""", nativeQuery = true)
    List<BonEntreeCamion> findAllByTransitaireClientCompteClient(String compteClient, int start, int end);

    @Query(value = """
            SELECT bec.*
            FROM SIPEDBA.BON_ENTREE_CAMION bec
                     JOIN SIPEDBA.TRANSITAIRE t ON bec.TRANSITAIRE = t.ID_CLIENT
                     JOIN SIPEDBA.CLIENT c ON t.ID_CLIENT = c.ID_CLIENT
            WHERE c.COMPTE_CLIENT = :compteClient
            ORDER BY bec.DATE_BON_ENTREE DESC""", nativeQuery = true)
    List<BonEntreeCamion> findAllByTransitaireClientCompteClient(String compteClient);

    Optional<BonEntreeCamion> findByNumBonEntreeCamion(String numBonEntreeCamion);

    @Query(value = "SELECT COUNT(*) " +
            "FROM SIPEDBA.BON_ENTREE_CAMION bec " +
            "         JOIN SIPEDBA.TRANSITAIRE t ON bec.TRANSITAIRE = t.ID_CLIENT " +
            "         JOIN SIPEDBA.CLIENT c ON t.ID_CLIENT = c.ID_CLIENT " +
            "WHERE c.COMPTE_CLIENT = :compteClient",
            nativeQuery = true)
    long countByCompteClient(String compteClient);

    @Query(value = """
            SELECT COUNT(*)
            FROM SIPEDBA.BON_ENTREE_CAMION bec
                     INNER JOIN SIPEDBA.SITE S on bec.SITE_PESAGE = S.ID_SITE
                     INNER JOIN SIPEDBA.CONCESSIONNAIRE C on S.CONCESSIONNAIRE = C.ID_CLIENT
                     INNER JOIN SIPEDBA.CLIENT C2 on C.ID_CLIENT = C2.ID_CLIENT
                     LEFT JOIN SIPEDBA.TRANSITAIRE t on bec.TRANSITAIRE = t.ID_CLIENT
                     LEFT JOIN SIPEDBA.CLIENT c3 on t.ID_CLIENT = c3.ID_CLIENT
                     INNER JOIN SIPEDBA.CAMION c4 on bec.IMMATRICULATION = c4.IMMATRICULATION
                     INNER JOIN SIPEDBA.PAYS p on c4.CODE_PAYS = p.CODE_PAYS
            WHERE C2.COMPTE_CLIENT = :compteClient
              AND bec.CODE_SENS_TRAFIC = :sensTrafic""", nativeQuery = true)
    long countPesageBon(String compteClient, String sensTrafic);
}