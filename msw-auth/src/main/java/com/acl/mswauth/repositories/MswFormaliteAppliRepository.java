package com.acl.mswauth.repositories;

import com.acl.mswauth.model.MswFormaliteApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MswFormaliteAppliRepository extends JpaRepository<MswFormaliteApplication, Long> {

@Query(value = "select  d from MswFormaliteApplication d where  d.id = ?1")
    Optional<MswFormaliteApplication> findByApplication(Long id);

}
