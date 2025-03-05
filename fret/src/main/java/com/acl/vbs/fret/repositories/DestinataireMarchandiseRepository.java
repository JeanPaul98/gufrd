package com.acl.vbs.fret.repositories;

import com.acl.vbs.fret.entities.DestinataireMarchandise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinataireMarchandiseRepository extends JpaRepository<DestinataireMarchandise, Long> {
}