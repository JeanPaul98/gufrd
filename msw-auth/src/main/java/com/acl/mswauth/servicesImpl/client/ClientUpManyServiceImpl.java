package com.acl.mswauth.servicesImpl.client;

import com.acl.mswauth.dto.ClientPortGroupeDto;

import com.acl.mswauth.dto.GroupeDto;
import com.acl.mswauth.dto.GroupeDtoUpdate;
import com.acl.mswauth.model.*;
import com.acl.mswauth.playload.ApiResponseModel;
import com.acl.mswauth.repositories.*;
import com.acl.mswauth.service.client.ClientUpManyService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author kol on 01/10/2024
 * @project msw-auth
 */
@Service
public class ClientUpManyServiceImpl implements ClientUpManyService {

    @Autowired
    private Environment env;

    private final PortClientRepository portClientRepository;

    private final ClientRepository clientRepository;

    private final GroupeClientRepository groupeClientRepository;

    private final GroupeRepository groupeRepository;

    private final PortRepository portRepository;

    public ClientUpManyServiceImpl(PortClientRepository portClientRepository, ClientRepository clientRepository, GroupeClientRepository groupeClientRepository, GroupeRepository groupeRepository, PortRepository portRepository) {
        this.portClientRepository = portClientRepository;
        this.clientRepository = clientRepository;
        this.groupeClientRepository = groupeClientRepository;
        this.groupeRepository = groupeRepository;
        this.portRepository = portRepository;
    }


    @Override
    @Transactional
    public ResponseEntity<?> addClientGroupePort(ClientPortGroupeDto request) {
        Optional<MswClient> optionalMswClient = clientRepository.findByCompteClient(request.getCompteClient());

        if (optionalMswClient.isPresent()) {
            //Ajouter le client dans un groupe
            for(GroupeDtoUpdate dataGroupe : request.getGroupes()) {
                Optional<MswGroupe> groupe = groupeRepository.findById(dataGroupe.getId());

                if (groupe.isPresent()) {
                    //Ajouter a un groupe

                    MswGroupeClient mswGroupeClient = new MswGroupeClient();
                    mswGroupeClient.setMswClient(optionalMswClient.get());
                    mswGroupeClient.setMswGroupe(groupe.get());
                    groupeClientRepository.save(mswGroupeClient);


                }
                else {
                   return   new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                            env.getProperty("message.application.notfound")),HttpStatus.OK);
                }

            }
            //fin de la boucle for

            Optional<MswPort> mswPort = portRepository.findByCode(request.getCodePort());

            if (mswPort.isPresent()) {
                MswPortClient mswPortClient = new MswPortClient();
                mswPortClient.setMswPort(mswPort.get());
                mswPortClient.setMswClient(optionalMswClient.get());
                mswPortClient.setCodePortClient(mswPort.get().getCode());
                portClientRepository.save(mswPortClient);

                return    new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                        env.getProperty("message.application.sucess"),request),HttpStatus.OK);
            }
            else {
                return    new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                        env.getProperty("message.application.notfound")),HttpStatus.OK);
            }
        } else  {

            return    new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.application.notfound")),HttpStatus.OK);

        }

    }


}
