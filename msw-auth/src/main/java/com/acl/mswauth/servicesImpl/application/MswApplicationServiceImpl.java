package com.acl.mswauth.servicesImpl.application;


import com.acl.mswauth.converter.MswApplicationConverter;
import com.acl.mswauth.model.*;
import com.acl.mswauth.playload.ApiResponseList;
import com.acl.mswauth.playload.ApiResponseModel;
import com.acl.mswauth.playload.InfosApplication;
import com.acl.mswauth.repositories.*;
import com.acl.mswauth.dto.ApplicationDto;
import com.acl.mswauth.request.UserApplicationRequest;
import com.acl.mswauth.security.impl.UserPrincipal;
import com.acl.mswauth.service.application.MswApplicationService;
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

@Service
public class MswApplicationServiceImpl implements MswApplicationService {

    private final Logger logger = LoggerFactory.getLogger(MswApplicationServiceImpl.class);

    @Autowired
    private Environment env;

    private final ApplicationRepository applicationRepository;
    private final MswApplicationConverter mswApplicationConverter;
    private final GroupeRepository groupeRepository;
    private final PortRepository portRepository;
    private final UserRepository userRepository;
    private final PortAppliRepository portAppliRepository;
    private final UserApplicationRepository userApplicationRepository;
    private final GroupeClientRepository groupeClientRepository;

    public MswApplicationServiceImpl(ApplicationRepository applicationRepository, ClientRepository clientRepository,
                                     MswApplicationConverter mswApplicationConverter,
                                     GroupeRepository groupeRepository, PortRepository portRepository, UserRepository userRepository, PortAppliRepository portAppliRepository,
                                     UserApplicationRepository userApplicationRepository, GroupeClientRepository groupeClientRepository) {
        this.applicationRepository = applicationRepository;
        this.mswApplicationConverter = mswApplicationConverter;
        this.groupeRepository = groupeRepository;
        this.portRepository = portRepository;
        this.userRepository = userRepository;
        this.portAppliRepository = portAppliRepository;
        this.userApplicationRepository = userApplicationRepository;
        this.groupeClientRepository = groupeClientRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<?> create(ApplicationDto request) {
        Optional<MswApplication> ms = applicationRepository.findByReference(request.getName());
        if(ms.isPresent()) {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    "L'application existe déja ",request),HttpStatus.NOT_FOUND);
        } else {
            MswApplication mswApplication = mswApplicationConverter.convertEntity(request);
            mswApplication.setCreatedAt(new Date());
            mswApplication.setStatus(true);
            MswApplication save=  applicationRepository.save(mswApplication);
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                    "Opération réussi ",save),HttpStatus.OK);

        }

    }

    @Override
    public ResponseEntity<?> getAllApplications() {
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                "Opération réussie ",applicationRepository.findAll()),HttpStatus.OK);

    }

    /**
     * Ajout du groupe application port
     * @param request
     * @return
     */

    @Override
    @Transactional
    public ResponseEntity<?> addApplicationGroupePort(UserApplicationRequest request) {
         logger.info("Request to add application du groupe port : {}", request.getCodePort());
        Optional <MswPort> mswPort = portRepository.findByCode(request.getCodePort()) ;
        logger.info("Port : {} ", mswPort.isPresent());
        if(mswPort.isPresent()) {
            Optional<MswGroupe> groupe = groupeRepository.findByNomGroupe(request.getGroupe());
            Optional<MswApplication> mswApplication = applicationRepository.
                    findByReference(request.getApplication());
            MswUserApplication mswUserApplication = new MswUserApplication();
            mswUserApplication.setMswPort(mswPort.get());
            mswUserApplication.setGroupe(groupe.get());
            mswUserApplication.setMswApplication(mswApplication.get());
            mswUserApplication.setCreatedAt(new Date());
            userApplicationRepository.save(mswUserApplication);

            MswPortAppli portAppli = new MswPortAppli();
            portAppli.setMswApplication(mswApplication.get());
            portAppli.setMswPort(mswPort.get());
            portAppli.setCreatedAt(new Date());
            portAppli.setUrl(request.getUrl());

            portAppliRepository.save(portAppli);

            logger.info("Création effectuer avec succès");

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                    "Opération réussi ",request),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    "Le code du port n'existe pas ",request.getCodePort()),HttpStatus.NOT_FOUND);
        }


    }
    
    
    @Override
    public ResponseEntity<?> getAllApplicationsByGroupeId(Long groupeId) {
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                "Opération réussie ",applicationRepository.findAllByGroupeId(groupeId)),HttpStatus.OK);

    }
    
    @Override
    public ResponseEntity<?> getAllApplicationsByGroupeName(String groupeName) {
        return new ResponseEntity<>(new ApiResponseList(HttpStatus.OK.name(),
                "Opération réussie ",applicationRepository.findAllByGroupeName(groupeName)),HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> getAllAplicationByClient(UserPrincipal userPrincipal) {
        Optional<User> user = userRepository.findByLogin(userPrincipal.getLogin());
        if(user.isPresent()) {
            logger.info("utilisateur connecté {} ",user.get().getLogin());
            Optional<MswGroupeClient> mswgroupe = groupeClientRepository.findByClient(user.get().getMswClient().getCompteClient());
            if(mswgroupe.isPresent()) {
                logger.info("groupes de l'utilisateur {}", mswgroupe.get().getMswGroupe().getNomGroupe());
                List<MswApplication> mswApplications = applicationRepository.findAllByGroupeId(mswgroupe.get().getMswGroupe().getId());
                return new ResponseEntity<>(new ApiResponseList(HttpStatus.OK.name(),
                        "Opération réussie ",mswApplications),HttpStatus.OK);
            }

        }
        return new ResponseEntity<>(new ApiResponseList(HttpStatus.NOT_FOUND.name(),
                "Opération échoué "),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllAplicationByKeycloakUser(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()) {
            logger.info("utilisateur connecté {} ",user.get().getEmail());
            if(user.get().getMswClient() != null) {
                Optional<MswGroupeClient> mswgroupe = groupeClientRepository.
                        findByClient(user.get().getMswClient().getCompteClient());
                if(mswgroupe.isPresent()) {
                    logger.info("groupes de l'utilisateur {}", mswgroupe.get().getMswGroupe().getNomGroupe());
                    List<MswApplication> mswApplications = applicationRepository.findAllByGroupeId(mswgroupe.get().getMswGroupe().getId());
                    return new ResponseEntity<>(new ApiResponseList(HttpStatus.OK.name(),
                            "Opération réussie ",mswApplications),HttpStatus.OK);
                }
            }
            else  {
                logger.info("user , {}", user.get().getMswStructure().getNom());
                Optional<MswGroupeClient> mswgroupe2 = groupeClientRepository
                        .findByStructure(user.get().getMswStructure().getNom());
                if(mswgroupe2.isPresent()) {
                    logger.info("Msw groupe structure active , {}", mswgroupe2.get().getMswStructre().getNom());
                    List<MswApplication> mswApplications = applicationRepository.findAllByGroupeId(mswgroupe2.get().getMswGroupe().getId());
                    return new ResponseEntity<>(new ApiResponseList(HttpStatus.OK.name(),
                            "Opération réussie ",mswApplications),HttpStatus.OK);
                }

            }

        }
        return new ResponseEntity<>(new ApiResponseList(HttpStatus.NOT_FOUND.name(),
                "Opération échoué "),HttpStatus.OK);
    }




    /**
     * Liste les informations d'une application
     * @param applid
     * @return
     */
    @Override
    public ResponseEntity<?> getApplicationDetails(long applid) {
       Optional<MswPortAppli> mswPortAppli = portAppliRepository.findByApplication(applid);
       if(mswPortAppli.isPresent()) {
           InfosApplication infosApplication = new InfosApplication();
           infosApplication.setNomApplication(mswPortAppli.get().getMswApplication().getName());
            infosApplication.setNomPort(mswPortAppli.get().getMswPort().getNom());
            infosApplication.setDescription(mswPortAppli.get().getMswApplication().getDescription());
            infosApplication.setUrlApplication(mswPortAppli.get().getUrl());
           return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                   "Opération réussie ",infosApplication),HttpStatus.OK);
       }  else {
           return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                   "Opération échoué "),HttpStatus.OK);
       }

    }

    @Override
    public ResponseEntity<?> update(ApplicationDto request) {

        Optional<MswApplication> applis = applicationRepository.
                findByReference(request.getReference());
        if(applis.isPresent()) {
                applis.get().setName(request.getName());
                applis.get().setDescription(request.getDescription());
                applis.get().setReference(request.getReference());
                applis.get().setLogo(request.getLogo());
                applis.get().setOrdre(request.getOrdre());
                applis.get().setUpdatedAt(new Date());
                return  new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                        env.getProperty("message.application.sucess"),
                        applicationRepository.save(applis.get())),HttpStatus.OK);
        } else {
            return  new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.application.notfound")),HttpStatus.OK);
        }
    }


}
