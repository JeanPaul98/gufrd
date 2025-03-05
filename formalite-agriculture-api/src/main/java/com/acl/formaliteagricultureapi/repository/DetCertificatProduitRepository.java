package com.acl.formaliteagricultureapi.repository;

import com.acl.formaliteagricultureapi.models.DetCertificatProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kol on 04/09/2024
 * @project formalite-agriculture-api
 */
@Repository
public interface DetCertificatProduitRepository extends JpaRepository<DetCertificatProduit, Long> {

    @Query(value = "SELECT  d from DetCertificatProduit d where d.certificat.id= ?1")
    List<DetCertificatProduit> findByCertificatId(Long idCertificat);
}
