package com.acl.escalenavire.repositories;
import com.acl.escalenavire.dto.LigneMseDto;
import com.acl.escalenavire.dto.LigneMseInt;
import com.acl.escalenavire.models.LigneMse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LigneMseRepository extends JpaRepository<LigneMse, Long> {

    @Query(value = "SELECT l.NUM_CONTENEUR, l.POIDS_BRUT,l.PLOMB,l.DESC_MSE, m.LIB_MARCHANDISE" +
            " FROM ligne_mse l INNER JOIN bl b ON l.id_bl = b.id_bl INNER JOIN marchandise m ON l.code_marchandise = m.code_marchandise" +
            " WHERE b.num_bl = :numBl", nativeQuery = true)
    List<LigneMseInt> findLigneMseNumBl(@Param("numBl") String numBl);

    @Query(value = "SELECT l.id_ligne_mse,l.code_condition, l.code_marchandise, l.num_conteneur, l.desc_mse," +
            " l.poids_brut,l.plomb, l.type_ligne_mse, l.pays_origine, l.manutentionnaire,l.code_situ_mse " +
            "FROM ligne_mse l ", nativeQuery = true)
            Page<LigneMseInt> list(Pageable pageable);
}


