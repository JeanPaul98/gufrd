package com.acl.mswauth.servicesImpl.port;


import com.acl.mswauth.converter.PortConverter;
import com.acl.mswauth.dto.MswConnectedUserDto;
import com.acl.mswauth.model.*;
import com.acl.mswauth.playload.ApiResponseModel;
import com.acl.mswauth.repositories.*;
import com.acl.mswauth.request.PortAppliRequest;
import com.acl.mswauth.request.PortClientModelList;
import com.acl.mswauth.request.PortClientRequest;
import com.acl.mswauth.dto.PortDto;
import com.acl.mswauth.service.ConnectedUserService;
import com.acl.mswauth.service.port.PortService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PortServiceImp implements PortService {

    private final Logger logger = LoggerFactory.getLogger(PortServiceImp.class);

    @Autowired
    private ConnectedUserService connectedUserService;

    private final PortRepository portRepository;
    private final PortConverter portConverter;
    private final PaysRepository paysRepository;
    private final ClientRepository clientRepository;
    private final ApplicationRepository applicationRepository;
    private final PortClientRepository portClientRepository;
    private final PortAppliRepository portAppliRepository;
    private final UserRepository userRepository;
    private final MswConnectedUserRepository mswConnectedUserRepository;

    public PortServiceImp(PortRepository portRepository, PortConverter portConverter, PaysRepository paysRepository, ClientRepository clientRepository, ApplicationRepository applicationRepository, PortClientRepository portClientRepository, PortAppliRepository portAppliRepository, UserRepository userRepository, MswConnectedUserRepository mswConnectedUserRepository) {
        this.portRepository = portRepository;
        this.portConverter = portConverter;
        this.paysRepository = paysRepository;
        this.clientRepository = clientRepository;
        this.applicationRepository = applicationRepository;
        this.portClientRepository = portClientRepository;
        this.portAppliRepository = portAppliRepository;
        this.userRepository = userRepository;
        this.mswConnectedUserRepository = mswConnectedUserRepository;
    }

    @Override
    public ResponseEntity<?> create(PortDto request) {
        Optional<MswPays> mswPays = paysRepository.findMswPaysByCode(request.getCodePays());
        if (mswPays.isPresent()) {
            MswPort mswPort = portConverter.convertEntity(request);
            mswPort.setMswPays(mswPays.get());
           MswPort save = portRepository.save(mswPort);

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                    "Opération réussi ",save),HttpStatus.OK);

        } else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    "Le code pays n'existe pas ", request.getCodePays()),HttpStatus.NOT_FOUND);

        }


    }

    @Override
    public ResponseEntity<?> getAllPorts() {
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                "Opération réussie ",portRepository.findAll()),HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> addApplication(PortAppliRequest request) {
        logger.info("Request to add application groupe port : {}", request.getCodePort());
        Optional <MswPort> mswPort = portRepository.findByCode(request.getCodePort()) ;
        logger.info("Port : {} ", mswPort.isPresent());
        if(mswPort.isPresent()) {
            Optional<MswApplication> mswApplication = applicationRepository.findByReference(request.getReference());
            MswPortAppli mswPortAppli = new MswPortAppli();
            mswPortAppli.setMswPort(mswPort.get());
            mswPortAppli.setMswApplication(mswApplication.get());
            mswPortAppli.setCreatedAt(new Date());
            mswPortAppli.setUrl(request.getUrl());
            portAppliRepository.save(mswPortAppli);
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                    "Opération réussi ",request),HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                "Le code du port n'existe pas ",request.getCodePort()),HttpStatus.NOT_FOUND);

    }

    @Override
    public ResponseEntity<?> addClient(PortClientRequest request) {
        logger.info("Request to add application groupe port : {}", request.getCodePortClient());
        Optional <MswPort> mswPort = portRepository.findByCode(request.getCodePort()) ;
        logger.info("Port : {} ", mswPort.isPresent());
        if(mswPort.isPresent()) {
            Optional<MswClient> mswClient = clientRepository.findByCompteClient(request.getCompteClient());
            MswPortClient mswPortClient = new MswPortClient();
            mswPortClient.setMswPort(mswPort.get());
            mswPortClient.setMswClient(mswClient.get());
            mswPortClient.setCodePortClient(request.getCodePortClient());
            MswPortClient save = portClientRepository.save(mswPortClient);
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                    "Opération réussi ",save),HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                "Le code du port n'existe pas ",request.getCodePort()),HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> getAllApplicationByPort(String port) {
        logger.info("Request to add application groupe port : {}", port);
        Optional <MswPort> mswPort = portRepository.findByCode(port) ;
        logger.info("Port : {} ", mswPort.isPresent());
        if(mswPort.isPresent()) {
            List<MswPortAppli> ports = portAppliRepository.findByPort(port);
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                    "Opération réussi ",ports),HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                "Le code du port n'existe pas ",port),HttpStatus.OK);
    }

    /**
     * Retourne le compte client et le port de l'utilisateur
     * et store l'utilisateur connecter
     * @param email
     * @return
     */
    @Override
    public ResponseEntity<?> getPortClient(String email) {

        Optional<User> user = userRepository.findByEmail(email);
        logger.info("user {} ," ,user.isPresent());
        if(user.isPresent()) {
            if(user.get().getMswClient()!=null) {
                Optional<MswClient> mswClient = clientRepository.findByCompteClient(user.get().getMswClient().getCompteClient());
                if(mswClient.isPresent()) {
                    List<PortClientModelList> ports = new ArrayList<>();
                    List<MswPortClient> mswPortClient = portClientRepository.findByClient(mswClient.get().getCompteClient());
                    MswConnectedUserDto mswConnectedUserDto = new MswConnectedUserDto(user.get().getMswClient().getCompteClient(),
                            user.get().getLogin(), user.get().getFullname(),
                            mswPortClient.get(0).getCodePortClient(), mswClient.get().getMswPays().getCode(),
                            user.get().getMswClient().getNomClient());
                    MswConnectedUser currentConnected=connectedUserService.save(mswConnectedUserDto);
                    logger.info("User current session {}", currentConnected.getSessionId() );

                    // Information de L'utilisateur connecter connexion

                    mswPortClient.forEach(mspc -> {
                        PortClientModelList portClientModelList = new PortClientModelList();
                        portClientModelList.setCodePort(mspc.getCodePortClient());
                        portClientModelList.setCodeClient(mspc.getMswClient().getCompteClient());
                        portClientModelList.setNomClient(mspc.getMswClient().getNomClient());
                        portClientModelList.setPays(mspc.getMswPort().getMswPays().getNom());
                        portClientModelList.setNomPort(mspc.getMswPort().getNom());
                        portClientModelList.setCodePays(mspc.getMswPort().getMswPays().getCode());
                        portClientModelList.setLoginName(mswConnectedUserDto.getUserLogin());
                        portClientModelList.setSessionId(currentConnected.getSessionId());
                        ports.add(portClientModelList);
                    });

                    return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                            "Opération réussi ",ports),HttpStatus.OK);
                }
                else {
                    return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                            "Le client avec utilisateur n'existe pas ",email),HttpStatus.NOT_FOUND);
                }
            }else {
                Optional<User> mswUser = userRepository.findOneStructure(user.get().getMswStructure().getId(), user.get().getEmail());
               if (mswUser.isPresent()) {
                   List<PortClientModelList> ports = new ArrayList<>();
                   MswConnectedUserDto mswConnectedUserDto = new MswConnectedUserDto();

                   // Information de connexion
                   mswConnectedUserDto.setUserLogin(user.get().getLogin());
                   mswConnectedUserDto.setCompteClient(user.get().getLogin());
                   mswConnectedUserDto.setPortCode("LFW");
                   mswConnectedUserDto.setCountryCode(mswUser.get().getMswStructure().getMswPays().getCode());
                   MswConnectedUser currentConnected=connectedUserService.save(mswConnectedUserDto);
                   logger.info("User current session {}", currentConnected.getSessionId() );

                   //Afficher les information de l'utilisateur connecter
                   PortClientModelList portClientModelList = new PortClientModelList();
                   portClientModelList.setCodePort("LFW");
                   portClientModelList.setCodeClient(user.get().getLogin());
                   portClientModelList.setNomClient(user.get().getFullname());
                   portClientModelList.setPays(user.get().getMswStructure().getMswPays().getNom());
                   portClientModelList.setNomPort("LOME");
                   portClientModelList.setCodePays(user.get().getMswStructure().getMswPays().getCode());
                   portClientModelList.setSessionId(currentConnected.getSessionId());
                    ports.add(portClientModelList);

                   return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                           "Opération réussi ",ports),HttpStatus.OK);

               }else  {
                   return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                           "Le client avec utilisateur n'existe pas ",email),HttpStatus.NOT_FOUND);
               }

            }

        }
        else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    "Email n'existe pas ",email),HttpStatus.OK);
        }
    }



    @Override
    public ResponseEntity<?> getPortPays(String codePays) {
        Optional<MswPays> mswPays = paysRepository.findMswPaysByCode(codePays);
        if(mswPays.isPresent()) {
            List<MswPort> mswPorts = portRepository.findByCodePays(mswPays.get().getCode());
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    "Opération réussi ",mswPorts),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    "Le code du pays n'existe pas ",codePays),HttpStatus.NOT_FOUND);
        }
    }


}
