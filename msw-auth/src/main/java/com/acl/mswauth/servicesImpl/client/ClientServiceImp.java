package com.acl.mswauth.servicesImpl.client;

import com.acl.mswauth.converter.ClientConverter;
import com.acl.mswauth.dto.ClientDto;
import com.acl.mswauth.model.MswClient;
import com.acl.mswauth.model.MswPays;
import com.acl.mswauth.model.User;
import com.acl.mswauth.playload.ApiResponseModel;
import com.acl.mswauth.repositories.ClientRepository;
import com.acl.mswauth.repositories.PaysRepository;
import com.acl.mswauth.repositories.UserRepository;
import com.acl.mswauth.service.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImp implements ClientService {

    private final ClientRepository clientRepository;
    private final PaysRepository paysRepository;
    private final ClientConverter clientConverter;

    @Autowired
    private Environment env;

    private final UserRepository userRepository;

    public ClientServiceImp(ClientRepository clientRepository, PaysRepository paysRepository, ClientConverter clientConverter, UserRepository userRepository) {
        this.clientRepository = clientRepository;
        this.paysRepository = paysRepository;
        this.clientConverter = clientConverter;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> create(ClientDto clientDto) {
        MswClient mswClient = clientConverter.convertEntity(clientDto);
        Optional<MswPays> mswPays = paysRepository.findMswPaysByCode(clientDto.getCodePays());
        if (mswPays.isPresent()) {
            mswClient.setMswPays(mswPays.get());
            mswClient.setCompteClient(clientDto.getCodeClient());
            MswClient save = clientRepository.save(mswClient);
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                    "Opération réussi ", save), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    "Le client n'existe pas  ", clientDto.getCodePays()), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> getAllClients() {
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                "Opération réussie ", clientRepository.findAll()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllUserByClient(String codeClient) {
        Optional<MswClient> mswClient = clientRepository.findByCompteClient(codeClient);
        if (mswClient.isPresent()) {
            List<User> users = userRepository.findByCodeClient(codeClient);
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                    "Opération réussie ", users), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    "Le client n'existe pas  "), HttpStatus.NOT_FOUND);

        }
    }

    @Override
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                "Opération réussie ", userRepository.findAll()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getClient(String compteClient) {

        Optional<User> users = userRepository.findByLogin(compteClient);
        if (users.isPresent()) {
            MswClient client = users.get().getMswClient();
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                    "Opération réussie ", client), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    "Client not found "), HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<?> update(ClientDto request) {
        Optional<MswClient> applis = clientRepository.
                findById(request.getId());

        if (applis.isPresent()) {
            applis.get().setCompteClient(request.getCodeClient());
            applis.get().setNomClient(request.getNomClient());
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.application.sucess"),
                    clientRepository.save(applis.get())), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.application.notfound")), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> getAllPaginateClient(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MswClient> mswClientPageable = clientRepository.allCLients(pageable);
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.application.sucess"),
              mswClientPageable), HttpStatus.OK);
    }
}
