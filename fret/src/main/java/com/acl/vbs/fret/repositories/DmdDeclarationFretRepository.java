package com.acl.vbs.fret.repositories;

import com.acl.vbs.fret.entities.DmdDeclarationFret;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DmdDeclarationFretRepository extends JpaRepository<DmdDeclarationFret, Long> {
    List<DmdDeclarationFret> findAllByDeclarantCompteClient(String compteClient);

    List<DmdDeclarationFret> findAllByChargeurCompteClient(String compteClient);
}