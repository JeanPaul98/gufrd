package com.acl.mswauth.repositories;

import com.acl.mswauth.model.MswPays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaysRepository extends JpaRepository<MswPays, String> {

    Optional<MswPays> findMswPaysByCodeAndAndNom(String code, String nom);

    Optional<MswPays> findMswPaysByCode(String code);
}
