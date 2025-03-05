package com.acl.escalenavire.repositories;

import com.acl.escalenavire.models.Navire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NavireRepository extends JpaRepository<Navire, Long> {
}