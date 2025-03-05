package com.acl.formaliteagricultureapi.serviceImpl.agrement;

import com.acl.formaliteagricultureapi.converter.DemandeAutorisationAgrementConverter;
import com.acl.formaliteagricultureapi.dto.agrement.DemandeAutorisationAgrementDto;
import com.acl.formaliteagricultureapi.dto.agrement.TraiterDemandeAutorisationAgrement;
import com.acl.formaliteagricultureapi.dto.agrement.UpdateDemandeAutorisationAgrement;
import com.acl.formaliteagricultureapi.dto.piecejointe.PieceJointeFormaliteDto;
import com.acl.formaliteagricultureapi.models.*;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.models.enumeration.StatutPaiement;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.*;
import com.acl.formaliteagricultureapi.services.agrement.DemandeAutorisationAgrementCreateService;
import com.acl.formaliteagricultureapi.services.utils.UtilServices;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Zansouyé on 26/09/2024
 * @project formalite-agriculture-api
 */
@Service
public class DemandeAutorisationAgrementCreateServiceServiceImpl implements DemandeAutorisationAgrementCreateService {

    private Logger logger= LoggerFactory.getLogger(DemandeAutorisationAgrementCreateServiceServiceImpl.class);

    private final DemandeAutorisationAgrementRepository demandeAutorisationAgrementRepository;

    private final SocieteRepository societeRepository;

    private final AgrementRepository agrementRepository;

    private final TypePieceJointeRepository typePieceJointeRepository;

    private final PieceJointeRepository pieceJointeRepository;

    @Autowired
    private UtilServices utilServices;

    public DemandeAutorisationAgrementCreateServiceServiceImpl(DemandeAutorisationAgrementRepository
                                                                       demandeAutorisationAgrementRepository,
                                                               SocieteRepository societeRepository,
                                                               AgrementRepository agrementRepository,
                                                               TypePieceJointeRepository
                                                                       typePieceJointeRepository,
                                                               PieceJointeRepository
                                                                       pieceJointeRepository) {
        this.demandeAutorisationAgrementRepository = demandeAutorisationAgrementRepository;
        this.societeRepository = societeRepository;
        this.agrementRepository=agrementRepository;
        this.typePieceJointeRepository=typePieceJointeRepository;
        this.pieceJointeRepository=pieceJointeRepository;
    }

    @Autowired
    private Environment env;

    @Autowired
    private DemandeAutorisationAgrementConverter demandeAutorisationAgrementConverter;

    @Transactional
    @Override
    public ResponseEntity<?> createDemandeAutorisationAgrement(DemandeAutorisationAgrementDto demandeAutorisationAgrementDto) {

        Optional<Societe>optionalSociete= societeRepository.
                findById(demandeAutorisationAgrementDto.getIdSociete());

        Optional<Agrement>optionalAgrement= agrementRepository.
                findById(demandeAutorisationAgrementDto.getIdAgrement());

        if(optionalAgrement.isPresent() && optionalSociete.isPresent()){
            DmdAutorisationAgrement dmdAutorisationAgrement= new DmdAutorisationAgrement(
                    demandeAutorisationAgrementDto.getNumero(), demandeAutorisationAgrementDto.
                    getActivite(), demandeAutorisationAgrementDto.getObservation(),
                    demandeAutorisationAgrementDto.getDateAgrement(), demandeAutorisationAgrementDto.
                    getDateExpiration(), demandeAutorisationAgrementDto.getCompteClient(),
                    optionalSociete.get(), optionalAgrement.get());

            dmdAutorisationAgrement.setCreatedAt(new Date());
            dmdAutorisationAgrement.setDateDemande(new Date());
            dmdAutorisationAgrement.setEtat(Etat.NON_SOUMIS);

            DmdAutorisationAgrement save= demandeAutorisationAgrementRepository.
                    save(dmdAutorisationAgrement);

            //Enregistrement de la pièce jointe
         //   savePiecejointe(demandeAutorisationAgrementDto.getPieceJointeFormaliteDtos(),
          //          save);

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"), save), HttpStatus.OK);

        }else{
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.notfound")), HttpStatus.OK);
        }
    }
    @Transactional
    @Override
    public ResponseEntity<?> createDemandeAutorisationAgrementSansPieceJointe(DemandeAutorisationAgrementDto demandeAutorisationAgrementDto) {

        Optional<Societe>optionalSociete= societeRepository.
                findById(demandeAutorisationAgrementDto.getIdSociete());

        Optional<Agrement>optionalAgrement= agrementRepository.
                findById(demandeAutorisationAgrementDto.getIdAgrement());

        if(optionalAgrement.isPresent() && optionalSociete.isPresent()){
            DmdAutorisationAgrement dmdAutorisationAgrement= new DmdAutorisationAgrement(
                    demandeAutorisationAgrementDto.getNumero(), demandeAutorisationAgrementDto.
                    getActivite(), demandeAutorisationAgrementDto.getObservation(),
                    demandeAutorisationAgrementDto.getDateAgrement(), demandeAutorisationAgrementDto.
                    getDateExpiration(), demandeAutorisationAgrementDto.getCompteClient(),
                    optionalSociete.get(), optionalAgrement.get());

            dmdAutorisationAgrement.setNumero(utilServices.genererNumeroDemande(env.getProperty("message.code.genere.agrement")));
            dmdAutorisationAgrement.setCreatedAt(new Date());
            dmdAutorisationAgrement.setDateDemande(new Date());
            dmdAutorisationAgrement.setEtat(Etat.SOUMIS);
            dmdAutorisationAgrement.setDateSoumise(new Date());
            dmdAutorisationAgrement.setCode(utilServices.generateUUID());
            dmdAutorisationAgrement.setStatutPaiement(StatutPaiement.IMPAYER);
            DmdAutorisationAgrement save= demandeAutorisationAgrementRepository.
                    save(dmdAutorisationAgrement);

            //Enregistrement de la pièce jointe
            //   savePiecejointe(demandeAutorisationAgrementDto.getPieceJointeFormaliteDtos(),
            //          save);

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"), save), HttpStatus.OK);

        }else{
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.notfound")), HttpStatus.OK);
        }
    }


    @Override
    public ResponseEntity<?> soumettreDemandeAutorisationAgrement(UpdateDemandeAutorisationAgrement updateDemandeAutorisationAgrement) {
        Optional<DmdAutorisationAgrement> optionalDmdAutorisationAgrement = demandeAutorisationAgrementRepository.
                findById(updateDemandeAutorisationAgrement.getIdDmdAutorisationAgrement());
        if (optionalDmdAutorisationAgrement.isPresent()) {
            optionalDmdAutorisationAgrement.get().setEtat(Etat.SOUMIS);
            optionalDmdAutorisationAgrement.get().setDateSoumise(new Date());
            optionalDmdAutorisationAgrement.get().setUpdateAt(new Date());
            optionalDmdAutorisationAgrement.get().setCompteClient(updateDemandeAutorisationAgrement.getCompteClient());
            DmdAutorisationAgrement save = demandeAutorisationAgrementRepository.
                    save(optionalDmdAutorisationAgrement.get());
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes")),HttpStatus.OK );

        } else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.echec")),HttpStatus.OK );
        }
    }

    @Override
    public ResponseEntity<?> traiterDemandeAutorisationAgrement(TraiterDemandeAutorisationAgrement traiterDemandeAutorisationAgrement) {
        Optional<DmdAutorisationAgrement> optionalDmdAutorisationAgrement = demandeAutorisationAgrementRepository.
                findById(traiterDemandeAutorisationAgrement.getIdDmdAutorisationAgrement());
        if (optionalDmdAutorisationAgrement.isPresent()) {
            optionalDmdAutorisationAgrement.get().setEtat(Etat.TRAITER);
            optionalDmdAutorisationAgrement.get().setDateTraiter(new Date());
            optionalDmdAutorisationAgrement.get().setUpdateAt(new Date());
            optionalDmdAutorisationAgrement.get().setCompteAgentTraiter(traiterDemandeAutorisationAgrement.getCompteClient());
            DmdAutorisationAgrement save = demandeAutorisationAgrementRepository.
                    save(optionalDmdAutorisationAgrement.get());

            //Enregistrement de la pièce jointe
            savePiecejointe(traiterDemandeAutorisationAgrement.getPieceJointeFormaliteDtos(),
                    save);
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes")),HttpStatus.OK );

        } else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.echec")),HttpStatus.OK );
        }
    }

    public void savePiecejointe(List<PieceJointeFormaliteDto> detPiecesJoinets,
                                DmdAutorisationAgrement dmdAutorisationAgrement) {
        detPiecesJoinets.forEach(data -> {
            PieceJointe piecejointe = new PieceJointe();
            Optional<TypePieceJointe> typePieceJointe = typePieceJointeRepository.findById(data.getIdTypePieceJointe());
            if(typePieceJointe.isPresent()) {
                piecejointe.setTypePieceJointe(typePieceJointe.get());
                piecejointe.setDmdAutorisationAgrement(dmdAutorisationAgrement);
                piecejointe.setUrlPj(data.getUrlPj());
                piecejointe.setNomOriginePieceJointe(typePieceJointe.get().getLibelle());
                piecejointe.setNomGenerePieceJointe(data.getNomOriginePieceJointe());
                pieceJointeRepository.save(piecejointe);
            } else  {
                throw new IllegalArgumentException(env.getProperty("message.exception.reference"));
            }

        });
    }
}
