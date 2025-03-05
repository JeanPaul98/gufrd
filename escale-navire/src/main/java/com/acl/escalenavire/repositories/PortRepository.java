package com.acl.escalenavire.repositories;

import com.acl.escalenavire.models.Port;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortRepository extends JpaRepository<Port, String> {

}