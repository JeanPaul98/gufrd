package com.acl.formalitesanteapi.serviceimpl.desinfection;

import com.acl.formalitesanteapi.dto.inspection.desinfection.DemandeDesinfectionDto;
import com.acl.formalitesanteapi.models.Formalite;
import com.acl.formalitesanteapi.models.Inspection;
import com.acl.formalitesanteapi.models.Structure;
import com.acl.formalitesanteapi.models.TypeInspection;
import com.acl.formalitesanteapi.models.enumeration.Chaine;
import com.acl.formalitesanteapi.models.enumeration.Etat;
import com.acl.formalitesanteapi.playload.ApiResponseModel;
import com.acl.formalitesanteapi.repository.FormaliteRepository;
import com.acl.formalitesanteapi.repository.InspectionRepository;
import com.acl.formalitesanteapi.repository.StructureRepository;
import com.acl.formalitesanteapi.repository.TypeInspectionRepository;
import com.acl.formalitesanteapi.services.inspection.desinfection.DemandeDesinfectionSanitaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

/**
 * @author kol on 09/09/2024
 * @project formalite-sante-api
 */
@Service
public class DemandeDesinfectionServiceImpl implements DemandeDesinfectionSanitaireService {

    private final FormaliteRepository formaliteRepository;

    private final StructureRepository structureRepository;

    private final InspectionRepository inspectionRepository;

    private final TypeInspectionRepository typeInspectionRepository;

    @Autowired
    private Environment env;

    public DemandeDesinfectionServiceImpl(FormaliteRepository formaliteRepository, StructureRepository structureRepository, InspectionRepository inspectionRepository, TypeInspectionRepository typeInspectionRepository) {
        this.formaliteRepository = formaliteRepository;
        this.structureRepository = structureRepository;
        this.inspectionRepository = inspectionRepository;
        this.typeInspectionRepository = typeInspectionRepository;
    }



    @Override
    public ResponseEntity<?> create(DemandeDesinfectionDto request) {
        Inspection inspection = saveInspection(request.getProvenance(), request.getNrt(), request.getPavillon(),
                request.getRemarque(), request.getReinspection(), request.getCargaison(), request.getTonnage(),
                request.isOpDesinfection(), request.isOpDeratisation(), request.isOpDesinsectisation());


        //Enregistrement de la formalite
        return saveFormalite(request.getAtp(), request.getCompteClient(), request.getImo(), request.getNomNavire(),
                request.getAffreteur(), inspection);
    }


    private Inspection saveInspection(String provenance, String nrt, String
            pavillon, String remarque, Date dateReinspection, int cargaison, int tonnage ,
                                      boolean isDesinfection,boolean isDeratisation ,boolean isDesinsectisation) {

        Inspection inspection = new Inspection(provenance, nrt,pavillon, remarque, dateReinspection, cargaison,tonnage
               );
        inspection.setOpDesinfection(isDesinfection);
        inspection.setOpDeratisation(isDeratisation);
        inspection.setOpDesinfitisation(isDesinsectisation);

        Optional<TypeInspection> typeInspection = typeInspectionRepository.
                findByRef(env.getProperty("message.type.inspection.ref.desinfection"));

        if (typeInspection.isPresent()) {
            inspection.setTypeInspection(typeInspection.get());
        }else  {
            throw new IllegalArgumentException(env.getProperty("message.exception.reference"));
        }
        inspection.setCreatedAt(new Date());
        return    inspectionRepository.save(inspection);
    }


    private ResponseEntity<?> saveFormalite(String atp ,String compteClient, String imo, String nomNavire,
                                            String affreteur,
                                            Inspection inspection) {

        Formalite formalite = new Formalite(atp,nomNavire,imo,affreteur, compteClient);
        formalite.setChaine(Chaine.Import);

        formalite.setNumGenere("INSPNAV" + System.currentTimeMillis());
        formalite.setInspection(inspection);

        Optional<Structure> optionalStructure = structureRepository.findByCode(env.getProperty("message.structure.code"));

        if (!optionalStructure.isPresent()) {
            throw new IllegalArgumentException(env.getProperty("message.exception.reference"));
        }
        formalite.setStructure(optionalStructure.get());
        formalite.setEtat(Etat.NON_SOUMIS);
        formalite.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        formalite.setDateDmd(new Timestamp(System.currentTimeMillis()));
        Formalite saveFormalite = formaliteRepository.save(formalite);
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                env.getProperty("message.succes"), saveFormalite.getId()), HttpStatus.OK);

    }
}
