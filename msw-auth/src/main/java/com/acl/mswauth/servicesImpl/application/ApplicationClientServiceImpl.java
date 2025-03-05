package com.acl.mswauth.servicesImpl.application;

import com.acl.mswauth.model.*;
import com.acl.mswauth.playload.ApiResponseList;
import com.acl.mswauth.playload.ApiResponseMessage;
import com.acl.mswauth.playload.ApiResponseModel;
import com.acl.mswauth.repositories.*;
import com.acl.mswauth.service.AuthService;
import com.acl.mswauth.service.application.ApplicationClientService;
import com.acl.mswauth.service.utils.UtilService;
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
 * @author kol on 10/20/24
 * @project msw-auth
 */
@Service
public class ApplicationClientServiceImpl implements ApplicationClientService {

    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;
    private final GroupeClientRepository groupeClientRepository;
    private final UserClientRepository userClientRepository;


    private final MswConnectedUserRepository mswConnectedUserRepository;

    @Autowired
    private AuthService authService;


    @Autowired
    private Environment env;

    private final ClientRepository clientRepository;

    private final Logger logger = LoggerFactory.getLogger(ApplicationClientServiceImpl.class);

    public ApplicationClientServiceImpl(UserRepository userRepository, ApplicationRepository applicationRepository, GroupeClientRepository groupeClientRepository, UserClientRepository userClientRepository, MswConnectedUserRepository mswConnectedUserRepository, ClientRepository clientRepository) {
        this.userRepository = userRepository;
        this.applicationRepository = applicationRepository;
        this.groupeClientRepository = groupeClientRepository;
        this.userClientRepository = userClientRepository;
        this.mswConnectedUserRepository = mswConnectedUserRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public ResponseEntity<?> getApplicationClients(String email) {

        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()) {
            logger.info("utilisateur connecté {} ",user.get().getEmail());

            List<UserClient> userClients = userClientRepository.findByUserId(user.get().getId());
            logger.info("Size {} ",userClients.size());

            if(!userClients.isEmpty()) {
                Optional<MswGroupeClient> mswgroupe = groupeClientRepository.
                        findByClient(userClients.get(0).getMswClient().getCompteClient());

                if(mswgroupe.isPresent()) {

                    List<MswApplication> mswApplications = applicationRepository.
                            findAllByGroupeId(mswgroupe.get().getMswGroupe().getId());

                    return new ResponseEntity<>(new ApiResponseList(HttpStatus.OK.name(),
                            "Opération réussie ",mswApplications),HttpStatus.OK);
                }
                else {

                    return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                            "Client n'est pas dans le groupe ",mswgroupe.get().getMswClient().getCompteClient()),
                            HttpStatus.OK);
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
                } else  {
                    return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                            "Opération échoué ",user.get().getMswStructure().getNom()),HttpStatus.OK);
                }

            }
        }
        else  {
            return new ResponseEntity<>(new ApiResponseList(HttpStatus.NOT_FOUND.name(),
                    "Opération échoué "),HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<?> getApplicationByCompteClient(String compte) {

        logger.info("user compte client envoyer , {}", compte);

        Optional<MswClient> optionalClient = clientRepository.findByCompteClient(compte);
        logger.info("entrer dans le compte client demarage {}", optionalClient.isPresent());
        if (optionalClient.isPresent()) {
            List<MswGroupeClient> dtoClient = groupeClientRepository.
                    findByClientCompte(optionalClient.get().getCompteClient());

            logger.info("User session {} , {}", compte, dtoClient.get(0).getMswClient().getCompteClient());
            saveConnectedUser(compte, dtoClient.get(0).getMswClient().getCompteClient());


            if (!dtoClient.isEmpty()) {
                List<MswApplication> mswApplications = applicationRepository.
                        findAllByGroupeId(dtoClient.get(0).getMswGroupe().getId());

                return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                        env.getProperty("message.application.sucess"), mswApplications), HttpStatus.OK);

            } else {
                return new ResponseEntity<>(new ApiResponseMessage(HttpStatus.NOT_FOUND.name(),
                        env.getProperty("message.application.echec")), HttpStatus.OK);
            }

        } else {

            Optional<User> optionalUser = userRepository.findByEmail(authService.currentUserEmail());

            if (optionalUser.get().getMswStructure()!=null) {

                Optional<MswGroupeClient> mswGroupeClient = groupeClientRepository.findByStructureId(optionalUser.get().getMswStructure().getId());

                if (mswGroupeClient.isPresent()) {

                    List<MswApplication> mswApplications = applicationRepository.findAllByGroupeId(mswGroupeClient.get().getMswGroupe().getId());

                    return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                            env.getProperty("message.application.sucess"), mswApplications), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                            env.getProperty("message.application.echec")), HttpStatus.OK);
                }


            } else {

                return new ResponseEntity<>(new ApiResponseMessage(HttpStatus.NOT_FOUND.name(),
                        env.getProperty("message.application.echec")), HttpStatus.OK);
            }


            // Si c'est une structure


        }

    }

    public void saveConnectedUser(String compteClient, String nomEntreprise) {
        String userConnected = authService.currentUserEmail();

        Optional<User> user = userRepository.findByEmail(userConnected);
        if(user.isPresent()) {
            Optional<MswConnectedUser> mswConnectedUser = mswConnectedUserRepository.
                    findByUserLogin(user.get().getLogin());
            if(mswConnectedUser.isPresent()) {
                mswConnectedUser.get().setDateConnexion(new Date());
                mswConnectedUser.get().setCompteClient(compteClient);
                mswConnectedUser.get().setEntreprise(nomEntreprise);
                mswConnectedUserRepository.save(mswConnectedUser.get());
            }else  {
                logger.info("Msw connected not found , {} ",userConnected );
            }
        }else {
            logger.info("user not found , {}", userConnected);
        }

    }
}
