package com.acl.formaliteagricultureapi.services.phytosanitaire.exportation.obtentionCertif;

import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 02/09/2024
 * @project formalite-agriculture-api
 */
@Service
public interface InspectionPhytoObtentionManyService {

    ResponseEntity<?> listPhytosanitaire(Etat etat, String ref);

    ResponseEntity<?> listStatDemande();
}
