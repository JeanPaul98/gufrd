package com.acl.vbs.repositories;

import com.acl.vbs.entities.BecLigneMse;
import com.acl.vbs.entities.BonEntreeCamion;
import com.acl.vbs.projections.VaProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BecLigneMseRepository extends JpaRepository<BecLigneMse, Long> {

    List<BecLigneMse> findAllByBonEntreeCamion(BonEntreeCamion bonEntreeCamion);

    @Query(value = "SELECT \n" +

            "                LM.desc_mse         AS descMse,\n" +
            "                LM.num_conteneur    AS numConteneur,\n" +
            "                LM.POIDS_BRUT   AS poidsBrut  ,\n" +
            "                LM.NUM_CONTENEUR   AS  numConteneur  ,\n" +
            "                m.DESC_MSE          AS descMarchandise,\n" +
            "                m.LIB_MARCHANDISE   AS libMarchandise,\n" +
            "                tc.LIB_TYPE_CONTENEUR AS libTypeConteneur \n " +
            "FROM SIPEDBA.BEC_LIGNE_MSE b\n" +
            "\n" +
            "         INNER JOIN SIPEDBA.BON_ENTREE_CAMION BEC on BEC.NUM_BON_ENTREE_CAMION = b.NUM_BON_ENTREE_CAMION\n" +
            "         INNER JOIN SIPEDBA.LIGNE_MSE LM on LM.ID_LIGNE_MSE = b.ID_LIGNE_MSE\n" +
            "         INNER JOIN bl b ON LM.id_bl = b.id_bl\n" +
            "         LEFT JOIN marchandise m ON m.CODE_MARCHANDISE = LM.CODE_MARCHANDISE\n" +
            "         LEFT JOIN conteneur c2 ON LM.NUM_CONTENEUR = c2.NUM_CONTENEUR\n" +
            "         LEFT JOIN type_conteneur tc ON tc.CODE_TYPE_CONTENEUR = c2.CODE_TYPE_CONTENEUR\n" +
            "\n" +
            "WHERE  BEC.NUM_BON_ENTREE_CAMION = :numBonEntre", nativeQuery = true)
    List<VaProjection> getByBonEntreeCamion(String numBonEntre);

}
