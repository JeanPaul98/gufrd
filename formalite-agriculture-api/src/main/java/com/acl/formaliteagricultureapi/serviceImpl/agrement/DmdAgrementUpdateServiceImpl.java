package com.acl.formaliteagricultureapi.serviceImpl.agrement;

import com.acl.formaliteagricultureapi.converter.AutorisationAgrementConverter;
import com.acl.formaliteagricultureapi.dto.agrement.AutorisationAgrementDto;
import com.acl.formaliteagricultureapi.dto.agrement.DmdAgrementUpdateDto;
import com.acl.formaliteagricultureapi.models.AutorisationAgrement;
import com.acl.formaliteagricultureapi.models.DmdAutorisationAgrement;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.models.enumeration.StatutAgrement;
import com.acl.formaliteagricultureapi.playload.ApiResponseMessage;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.AutorisationAgrementRepository;
import com.acl.formaliteagricultureapi.repository.DemandeAutorisationAgrementRepository;
import com.acl.formaliteagricultureapi.services.agrement.DmdAgrementUpdateService;
import com.acl.formaliteagricultureapi.services.auth.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

/**
 * @author kol on 11/18/24
 * @project formalite-agriculture-api
 */
@Service
@Slf4j
public class DmdAgrementUpdateServiceImpl implements DmdAgrementUpdateService {

    private final DemandeAutorisationAgrementRepository demandeAutorisationAgrementRepository;

    private final AuthService authService;

    private final AutorisationAgrementConverter autorisationAgrementConverter;

    private final AutorisationAgrementRepository autorisationAgrementRepository;

    @Autowired
    private Environment env;

    public DmdAgrementUpdateServiceImpl(DemandeAutorisationAgrementRepository demandeAutorisationAgrementRepository, AuthService authService, AutorisationAgrementConverter autorisationAgrementConverter, AutorisationAgrementRepository autorisationAgrementRepository) {
        this.demandeAutorisationAgrementRepository = demandeAutorisationAgrementRepository;
        this.authService = authService;
        this.autorisationAgrementConverter = autorisationAgrementConverter;
        this.autorisationAgrementRepository = autorisationAgrementRepository;
    }

    @Override
    public ResponseEntity<?> accepter(DmdAgrementUpdateDto request) {
        Optional<DmdAutorisationAgrement> autorisationAgrement = demandeAutorisationAgrementRepository.
                findByCode(request.getCode());
        if (autorisationAgrement.isPresent()) {
            autorisationAgrement.get().setDateAccepte(new Date());
            autorisationAgrement.get().setEtat(Etat.ACCEPTER);
            autorisationAgrement.get().setUpdateAt(new Date());
            autorisationAgrement.get().setCompteAgentAccepter(authService.connectedUser());

            demandeAutorisationAgrementRepository.save(autorisationAgrement.get());

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                    request.getCode()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponseMessage(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.not.found.entity")),HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<?> rejecter(DmdAgrementUpdateDto request) {
        Optional<DmdAutorisationAgrement> autorisationAgrement = demandeAutorisationAgrementRepository.
                findByCode(request.getCode());
        if (autorisationAgrement.isPresent()) {
            autorisationAgrement.get().setDateRejet(new Date());
            autorisationAgrement.get().setEtat(Etat.REJETER);
            autorisationAgrement.get().setUpdateAt(new Date());
            autorisationAgrement.get().setMotifRejet(request.getMessage());
            autorisationAgrement.get().setCompteAgentAccepter(authService.connectedUser());

            demandeAutorisationAgrementRepository.save(autorisationAgrement.get());

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                    request.getCode()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponseMessage(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.not.found.entity")),HttpStatus.OK);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> traiter(DmdAgrementUpdateDto request) {
        Optional<DmdAutorisationAgrement> autorisationAgrement = demandeAutorisationAgrementRepository.
                findByCode(request.getCode());

        if (autorisationAgrement.isPresent()) {
            autorisationAgrement.get().setDateTraiter(new Date());
            autorisationAgrement.get().setEtat(Etat.TRAITER);
            autorisationAgrement.get().setDateAgrement(new Date());
            autorisationAgrement.get().setUpdateAt(new Date());
            autorisationAgrement.get().setCompteAgentAccepter(authService.connectedUser());

         DmdAutorisationAgrement dmd =   demandeAutorisationAgrementRepository.save(autorisationAgrement.get());

         saveAutorisationAgrement(dmd.getId(), autorisationAgrement.get());

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                    request.getCode()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponseMessage(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.not.found.entity")),HttpStatus.OK);
        }
    }

    private void saveAutorisationAgrement(Long idDmande, DmdAutorisationAgrement request) {

        Optional<DmdAutorisationAgrement> dmdAutorisationAgrement = demandeAutorisationAgrementRepository.findById(idDmande);

        if (dmdAutorisationAgrement.isPresent()) {
            AutorisationAgrementDto autorisationAgrementDto = new AutorisationAgrementDto(request.getNumero(), request.getActivite(),
                    request.getDateAgrement());

            AutorisationAgrement autorisationAgrement = autorisationAgrementConverter.convertEntityToDto(autorisationAgrementDto);
            autorisationAgrement.setCreatedAt(new Date());
            autorisationAgrement.setStatutAgrement(StatutAgrement.EN_COURS);
            autorisationAgrement.setEtat(Etat.TRAITER);

            autorisationAgrement.setDmdAutorisationAgrement(dmdAutorisationAgrement.get());
            autorisationAgrementRepository.save(autorisationAgrement);
        }


    }
}
