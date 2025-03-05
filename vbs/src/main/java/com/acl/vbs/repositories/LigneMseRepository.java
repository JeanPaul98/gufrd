package com.acl.vbs.repositories;

import com.acl.vbs.entities.LigneMse;
import com.acl.vbs.projections.LigneMseProjection;
import com.acl.vbs.projections.VaProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LigneMseRepository extends JpaRepository<LigneMse, Long> {


    @Query(value = "SELECT\n" +
            "                   b.num_bl              AS numBl,\n" +
            "                   l.num_conteneur       AS numConteneur,\n" +
            "                   l.ID_LIGNE_MSE        AS idLigneMse,\n" +
            "                   l.poids_brut          AS poidsBrut,\n" +
            "                   l.DESC_MSE            AS descMse,\n" +
            "                   m.DESC_MSE            AS descMarchandise,\n" +
            "                   a.LIB_AIRE_STOCKAGE   AS libAireStockage ,\n" +
            "                   m.LIB_MARCHANDISE     AS libMarchandise,\n" +
            "                   tc.LIB_TYPE_CONTENEUR AS libTypeConteneur\n" +
            "\n" +
            "            FROM vas v\n" +
            "                     JOIN ligne_mse l ON v.id_ligne_mse = l.id_ligne_mse\n" +
            "                     JOIN aire_stockage a ON a.code_aire_stockage = v.code_aire_stockage\n" +
            "                     JOIN bl b ON b.id_bl = l.id_bl\n" +
            "                     JOIN marchandise m ON m.code_marchandise = l.code_marchandise\n" +
            "                     LEFT JOIN conteneur c2 ON l.NUM_CONTENEUR = c2.NUM_CONTENEUR\n" +
            "                     LEFT JOIN type_conteneur tc ON tc.CODE_TYPE_CONTENEUR = c2.CODE_TYPE_CONTENEUR\n" +
            "            WHERE b.num_bl = :numBl", nativeQuery = true)
    List<VaProjection> findLigneMseNumBl(@Param("numBl") String numBl);


//    @Query(value = "SELECT DISTINCT l.desc_mse     AS descMse,\n" +
//            "                l.ID_LIGNE_MSE        AS idLigneMse,\n"+
//            "                b.num_bl              AS numBl,\n" +
//            "                l.num_conteneur       AS numConteneur,\n" +
//            "                l.code_marchandise    AS codeMarchandise,\n" +
//            "                b.port_origine        AS portOrigine,\n" +
//            "                b.port_dest           AS portDest,\n" +
//            "                b.transitaire         AS transitaire,\n" +
//            "                l.poids_brut          AS poidsBrut,\n" +
//            "                tc.LIB_TYPE_CONTENEUR AS libTypeConteneur,\n" +
//            "                m.DESC_MSE            AS descMarchandise,\n" +
//            "                m.LIB_MARCHANDISE     AS libMarchandise,\n" +
//            "                v.NUM_CHASSIS         AS numCahssis\n" +
//            "FROM ligne_mse l\n" +
//            "         INNER JOIN bl b ON l.id_bl = b.id_bl\n" +
//            "         LEFT JOIN marchandise m ON m.CODE_MARCHANDISE = l.CODE_MARCHANDISE\n" +
//            "         LEFT JOIN conteneur c2 ON l.NUM_CONTENEUR = c2.NUM_CONTENEUR\n" +
//            "         LEFT JOIN type_conteneur tc ON tc.CODE_TYPE_CONTENEUR = c2.CODE_TYPE_CONTENEUR\n" +
//            "         LEFT JOIN vehicule v ON l.ID_LIGNE_MSE = v.ID_LIGNE_MSE\n" +
//            "WHERE (l.date_vsp IS NULL OR l.date_vep IS NULL)\n" +
//            "  AND b.transitaire IS NOT NULL\n" +
//            "  AND l.totalement_sortie = 0\n" +
//            "  AND b.NUM_BL = :numBl", nativeQuery = true)
//    List<LigneMseProjection> findLigneMseNumBl(@Param("numBl") String numBl);


    @Query(value = "SELECT DISTINCT l.desc_mse     AS descMse,\n" +
            "                l.ID_LIGNE_MSE        AS idLigneMse,\n"+
            "                b.num_bl              AS numBl,\n" +
            "                l.num_conteneur       AS numConteneur,\n" +
            "                l.code_marchandise    AS codeMarchandise,\n" +
            "                b.port_origine        AS portOrigine,\n" +
            "                b.port_dest           AS portDest,\n" +
            "                b.transitaire         AS transitaire,\n" +
            "                l.poids_brut          AS poidsBrut,\n" +
            "                tc.LIB_TYPE_CONTENEUR AS libTypeConteneur,\n" +
            "                m.DESC_MSE            AS descMarchandise,\n" +
            "                m.LIB_MARCHANDISE     AS libMarchandise,\n" +
            "                v.NUM_CHASSIS         AS numCahssis\n" +
            "FROM ligne_mse l\n" +
            "         INNER JOIN bl b ON l.id_bl = b.id_bl\n" +
            "         INNER JOIN marchandise m ON m.CODE_MARCHANDISE = l.CODE_MARCHANDISE\n" +
            "         LEFT JOIN conteneur c2 ON l.NUM_CONTENEUR = c2.NUM_CONTENEUR\n" +
            "         LEFT JOIN type_conteneur tc ON tc.CODE_TYPE_CONTENEUR = c2.CODE_TYPE_CONTENEUR\n" +
            "         INNER JOIN vehicule v ON l.ID_LIGNE_MSE = v.ID_LIGNE_MSE\n" +
            "WHERE (l.date_vsp IS NULL OR l.date_vep IS NULL)\n" +
            "  AND b.transitaire IS NOT NULL\n" +
            "  AND l.totalement_sortie = 0\n" +
            "  AND v.NUM_CHASSIS = :numChassis", nativeQuery = true)
    List<LigneMseProjection> findLigneMseChassis(@Param("numChassis") String numChassis);


    @Query(value = " SELECT l.ID_LIGNE_MSE        AS idLigneMse,\n" +
            "                l.desc_mse     AS descMse,\n" +
            "                l.ID_LIGNE_MSE        AS idLigneMse,\n"+
            "                l.num_conteneur       AS numConteneur,\n" +
            "                l.code_marchandise    AS codeMarchandise,\n" +
            "                l.poids_brut          AS poidsBrut\n" +
            " FROM ligne_mse l fetch first ? rows only ", nativeQuery = true)
    Page<LigneMse> list(Pageable pageable);

    @Query(value = "SELECT\n" +
            "                   b.num_bl              AS numBl,\n" +
            "                   l.num_conteneur       AS numConteneur,\n" +
            "                   l.ID_LIGNE_MSE        AS idLigneMse,\n" +
            "                   l.poids_brut          AS poidsBrut,\n" +
            "                   l.DESC_MSE            AS descMse,\n" +
            "                   m.DESC_MSE            AS descMarchandise,\n" +
            "                   a.LIB_AIRE_STOCKAGE   AS libAireStockage ,\n" +
            "                   m.LIB_MARCHANDISE     AS libMarchandise,\n" +
            "                   tc.LIB_TYPE_CONTENEUR AS libTypeConteneur\n" +
            "\n" +
            "            FROM vas v\n" +
            "                     JOIN ligne_mse l ON v.id_ligne_mse = l.id_ligne_mse\n" +
            "                     JOIN aire_stockage a ON a.code_aire_stockage = v.code_aire_stockage\n" +
            "                     JOIN bl b ON b.id_bl = l.id_bl\n" +
            "                     JOIN marchandise m ON m.code_marchandise = l.code_marchandise\n" +
            "                     LEFT JOIN conteneur c2 ON l.NUM_CONTENEUR = c2.NUM_CONTENEUR\n" +
            "                     LEFT JOIN type_conteneur tc ON tc.CODE_TYPE_CONTENEUR = c2.CODE_TYPE_CONTENEUR\n" +
            "            WHERE l.NUM_CONTENEUR = :numConteneur", nativeQuery = true)
    List<VaProjection> findLigneMseNumConteneur(@Param("numConteneur") String numConteneur);

//    @Query(value = "SELECT DISTINCT l.desc_mse     AS descMse,\n" +
//            "                l.ID_LIGNE_MSE        AS idLigneMse,\n"+
//            "                b.num_bl              AS numBl,\n" +
//            "                l.num_conteneur       AS numConteneur,\n" +
//            "                l.code_marchandise    AS codeMarchandise,\n" +
//            "                b.port_origine        AS portOrigine,\n" +
//            "                b.port_dest           AS portDest,\n" +
//            "                b.transitaire         AS transitaire,\n" +
//            "                l.poids_brut          AS poidsBrut,\n" +
//            "                tc.LIB_TYPE_CONTENEUR AS libTypeConteneur,\n" +
//            "                m.DESC_MSE            AS descMarchandise,\n" +
//            "                m.LIB_MARCHANDISE     AS libMarchandise,\n" +
//            "                v.NUM_CHASSIS         AS numCahssis\n" +
//            "FROM ligne_mse l\n" +
//            "         INNER JOIN bl b ON l.id_bl = b.id_bl\n" +
//            "         INNER JOIN marchandise m ON m.CODE_MARCHANDISE = l.CODE_MARCHANDISE\n" +
//            "         INNER JOIN conteneur c2 ON l.NUM_CONTENEUR = c2.NUM_CONTENEUR\n" +
//            "         INNER JOIN type_conteneur tc ON tc.CODE_TYPE_CONTENEUR = c2.CODE_TYPE_CONTENEUR\n" +
//            "         LEFT JOIN vehicule v ON l.ID_LIGNE_MSE = v.ID_LIGNE_MSE\n" +
//            "WHERE (l.date_vsp IS NULL OR l.date_vep IS NULL)\n" +
//            "  AND b.transitaire IS NOT NULL\n" +
//            "  AND l.totalement_sortie = 0\n" +
//            "  AND l.NUM_CONTENEUR = :numConteneur", nativeQuery = true)
//    List<LigneMseProjection> findLigneMseNumConteneur(@Param("numConteneur") String numConteneur);

    Optional<LigneMse> findByIdLigneMse(Long id);


}