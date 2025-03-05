package com.acl.formaliteagricultureapi.repository;

import com.acl.formaliteagricultureapi.models.FeedbackSrvPaiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author kol on 10/7/24
 * @project formalite-agriculture-api
 */
@Repository
public interface FeedbackSrvPaiementRepository extends JpaRepository<FeedbackSrvPaiement,Long> {

    @Query(value = "select  d from FeedbackSrvPaiement  d where d.transactionId = ?1")
    public Optional<FeedbackSrvPaiement> findByTransactionId(String transactionId);

    @Query(value = "select  d from FeedbackSrvPaiement  d where d.formalite.id = ?1")
    public Optional<FeedbackSrvPaiement> findByFormalite(Long idFormalite);

    @Query(value = "select  d from FeedbackSrvPaiement  d where d.formalite.id = ?1 order by d.id desc ")
    public List<FeedbackSrvPaiement> findByFormaliteList(Long idFormalite);
}
