package com.acl.mswauth.servicesImpl.client;

import com.acl.mswauth.controller.GroupeController;
import com.acl.mswauth.converter.PortConverter;
import com.acl.mswauth.dto.ClientPortGroupeDto;
import com.acl.mswauth.dto.PortDto;
import com.acl.mswauth.model.MswClient;
import com.acl.mswauth.model.MswPort;
import com.acl.mswauth.model.MswPortClient;
import com.acl.mswauth.dto.PortClientDto;
import com.acl.mswauth.playload.ApiResponseModel;
import com.acl.mswauth.repositories.ClientRepository;
import com.acl.mswauth.repositories.PortClientRepository;
import com.acl.mswauth.service.client.ClientManyService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author kol on 16/09/2024
 * @project msw-auth
 */
@Service
public class ClientManyServiceImp implements ClientManyService {

    private final PortClientRepository portClientRepository;

    private final ClientRepository clientRepository;

    private final Logger logger = LoggerFactory.getLogger(ClientManyServiceImp.class);

    @Autowired
    PortConverter portConverter;

    @Autowired
    private Environment env;

    public ClientManyServiceImp(PortClientRepository portClientRepository, ClientRepository clientRepository) {
        this.portClientRepository = portClientRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public ResponseEntity<?> getPortByClient(String codeClient) {

        logger.info("getPortByClient, {}", codeClient);

        List<MswPortClient> portClients = portClientRepository.findByClient(codeClient);

        List<PortDto> portDtos = new ArrayList<>();

        if (!portClients.isEmpty()) {
            portClients.forEach(data -> {
                PortDto portDto = new PortDto();
                portDto.setCode(data.getMswPort().getCode());
                portDto.setNom(data.getMswPort().getNom());
                portDto.setLocode(data.getMswPort().getLocode());
                portDto.setCodePays(data.getMswPort().getMswPays().getCode());
                portDtos.add(portDto);
            });
        return     new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.application.sucess"), portDtos),HttpStatus.OK);
        } else {
         return    new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.application.vide"), portDtos),HttpStatus.OK);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> addClientToPort(PortClientDto request) {

        Optional<MswClient> mswClient = clientRepository.findByCompteClient(request.getCompteClient());
        if (mswClient.isPresent()) {
            request.getPortDtos().forEach(data -> {
                MswPort  mswPort = portConverter.convertEntity(data);
                logger.info("addClientToPort, {}", mswPort.getCode() + " " + mswPort.getNom());
                MswPortClient portClient = new MswPortClient();
                portClient.setMswPort(mswPort);
                portClient.setMswClient(mswClient.get());
                portClient.setCodePortClient(mswPort.getCode());
                portClientRepository.save(portClient);
            });
            return     new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.application.sucess"), request),HttpStatus.OK);
        }else {
            return    new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.application.notfound")),HttpStatus.OK);
        }
    }

}
