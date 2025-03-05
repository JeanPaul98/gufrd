package com.acl.mswauth.servicesImpl.port;

import com.acl.mswauth.converter.PortConverter;
import com.acl.mswauth.model.*;
import com.acl.mswauth.playload.ApiResponseModel;
import com.acl.mswauth.repositories.*;
import com.acl.mswauth.request.PortRequest;
import com.acl.mswauth.service.port.PortClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PortClientServiceImp implements PortClientService {

    private final Logger logger = LoggerFactory.getLogger(PortServiceImp.class);


    private final PortRepository portRepository;

    private final ClientRepository clientRepository;
    private final PortClientRepository portClientRepository;

    public PortClientServiceImp(PortRepository portRepository, ClientRepository clientRepository, PortClientRepository portClientRepository) {
        this.portRepository = portRepository;
        this.clientRepository = clientRepository;
        this.portClientRepository = portClientRepository;
    }


    @Override
    public ResponseEntity<?> addPortClients(String compteClient, String ports) {

        ObjectMapper mapper = new ObjectMapper();
        Optional<MswClient> mswClient = clientRepository.findByCompteClient(compteClient);
        try {
            PortRequest[] portRequests = mapper.readValue(ports, PortRequest[].class);

            if (mswClient.isPresent()) {
            for(PortRequest port : portRequests) {
                Optional<MswPort> mswPort = portRepository.findByCode(port.getCode());
                MswPortClient mswPortClient = new MswPortClient();
                if (mswPort.isPresent()) {
                    logger.info("Port  {}", mswPort.get().getCode());
                    mswPortClient.setMswClient(mswClient.get());
                    mswPortClient.setMswPort(mswPort.get());
                    mswPortClient.setCodePortClient(mswPort.get().getCode());
                    logger.info("Port client{}", mswPortClient.getMswPort().getCode());
                    portClientRepository.save(mswPortClient);
                }
            }
                return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                        "Opération réussi ",portRequests),HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                        "Le client n'existe pas  ", compteClient),HttpStatus.NOT_FOUND);
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
