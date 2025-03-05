package com.acl.escalenavire.repositories;

import com.acl.escalenavire.dto.AnnonceEscaleInt;
import com.acl.escalenavire.dto.BlInt;
import com.acl.escalenavire.models.AnnonceEscale;
import com.acl.escalenavire.models.Bl;
import com.acl.escalenavire.models.LigneMse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlRepository extends JpaRepository<Bl, Long> {

            @Query(value = "SELECT l.ID_BL, l.ID_BL_PARENT, l.PORT_ORIGINE, l.PORT_DEST, " +
            "l.NUM_BL, l.DESCRIPTION_BL, l.NOM_PROPRIETAIRE_INCONNU, l.NOM_PROPRIETAIRE_INCONNU," +
            "l.NOM_REPRESENTANT,l.DEPOTAGE,l.DMDFACTR " +
            "FROM bl l ORDER BY l.ID_BL DESC", nativeQuery = true)
        Page<Bl> list(Pageable pageable);

    @Query(value = "SELECT l.NUM_BL FROM SIPEDBA.BL l\n" +
            "INNER JOIN SIPEDBA.MANIFESTE m ON m.CODE_MANIFESTE = l.CODE_MANIFESTE\n" +
            "INNER JOIN SIPEDBA.ANNONCE_ESCALE a ON a.ID_ANNONCE_ESCALE = m.ID_ANNONCE_ESCALE\n" +
            "WHERE a.NUMERO_AAN = 'ATP00025801'", nativeQuery = true)
    List<AnnonceEscaleInt> findNumBlByAtp(String numAtp);
}