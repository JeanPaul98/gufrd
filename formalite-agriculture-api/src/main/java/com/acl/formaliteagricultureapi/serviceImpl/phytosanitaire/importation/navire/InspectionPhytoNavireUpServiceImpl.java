package com.acl.formaliteagricultureapi.serviceImpl.phytosanitaire.importation.navire;

import com.acl.formaliteagricultureapi.dto.imports.navire.PhytosanitaireNavireClientListDto;
import com.acl.formaliteagricultureapi.models.Formalite;
import com.acl.formaliteagricultureapi.models.PhytoSanitaire;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.DetPhytosanitaireProduitRepository;
import com.acl.formaliteagricultureapi.repository.FormaliteRepository;
import com.acl.formaliteagricultureapi.repository.PhytosanitaireRepository;
import com.acl.formaliteagricultureapi.services.phytosanitaire.importation.navire.InspectionPhytoNavireUpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

/**
 * @author kol on 20/09/2024
 * @project formalite-agriculture-api
 */
@Service
public class InspectionPhytoNavireUpServiceImpl implements InspectionPhytoNavireUpService {


    private final Logger logger = LoggerFactory.getLogger(InspectionPhytoNavireUpServiceImpl.class);

    private final PhytosanitaireRepository phytosanitaireRepository;

    private final FormaliteRepository formaliteRepository;



    @Autowired
    private Environment env;

    public InspectionPhytoNavireUpServiceImpl(PhytosanitaireRepository phytosanitaireRepository, FormaliteRepository formaliteRepository) {
        this.phytosanitaireRepository = phytosanitaireRepository;
        this.formaliteRepository = formaliteRepository;
    }


    @Override
    public ResponseEntity<?> updateDemande(PhytosanitaireNavireClientListDto request) throws Exception {

        updatePhytosanitaire(request.getIdPhytosanitaire(),request.getNomDemandeur(),
                request.getProfessionDemandeur(), request.getAdresseDemandeur(),
                request.getLieuInspection(), request.getDatePrevueInspection());


        return  updateFormalite(request.getIdFormalite(),request.getAtp(),request.getNomNavire(),
                request.getImo(), request.getAffreteur());

    }

    /**
     * Mise a jour de la formalite
     * @param idFormalite
     * @param atp
     * @param nomNavire
     * @param imo
     * @param affreteur
     * @return
     */
    public ResponseEntity<?> updateFormalite(Long idFormalite, String atp, String nomNavire, String imo,
                                             String affreteur) {
        Optional<Formalite> optionalFormalite = formaliteRepository.findById(idFormalite);
        if (optionalFormalite.isPresent()) {
            optionalFormalite.get().setAtp(atp);
            optionalFormalite.get().setNomNavire(nomNavire);
            optionalFormalite.get().setImo(imo);
            optionalFormalite.get().setAffreteur(affreteur);
            optionalFormalite.get().setEtat(Etat.NON_SOUMIS);
            optionalFormalite.get().setUpdateAt(new Timestamp(System.currentTimeMillis()));
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"), formaliteRepository.save(optionalFormalite.get())),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.not.found.entity")), HttpStatus.OK);
        }
    }

    public void updatePhytosanitaire(Long idPhytosanitaire, String nomDemandeur , String professionDemandeur,
                                     String adresseDemandeur, String lieuInspection, Date datePrevueInspection ) throws Exception {
        Optional<PhytoSanitaire> optionalPhytoSanitaire = phytosanitaireRepository.findById(idPhytosanitaire);
        if (optionalPhytoSanitaire.isPresent()) {
            optionalPhytoSanitaire.get().setLieuInspection(lieuInspection);
            optionalPhytoSanitaire.get().setNomDemandeur(nomDemandeur);
            optionalPhytoSanitaire.get().setProfessionDemandeur(professionDemandeur);
            optionalPhytoSanitaire.get().setDatePrevueInspection(datePrevueInspection);
            optionalPhytoSanitaire.get().setAdresseDemandeur(adresseDemandeur);
            phytosanitaireRepository.save(optionalPhytoSanitaire.get());
        } else {
            throw  new Exception(env.getProperty("message.not.found.entity"));

        }
    }
}
