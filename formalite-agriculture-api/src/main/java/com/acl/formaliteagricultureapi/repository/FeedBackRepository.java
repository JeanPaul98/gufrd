package com.acl.formaliteagricultureapi.repository;

import com.acl.formaliteagricultureapi.models.FeedBackPublic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author kol on 10/6/24
 * @project formalite-agriculture-api
 */
@Repository
public interface FeedBackRepository extends JpaRepository<FeedBackPublic, Long> {

    @Query(value = "select  d from FeedBackPublic  d where d.formalite.id = ?1")
    Optional<FeedBackPublic> findByIdFormalite(Long idFormalite);

    @Query(value = "select  d from FeedBackPublic  d where d.record= ?1")
    Optional<FeedBackPublic> findByRecord(String record);
}
