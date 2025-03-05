package com.acl.mswauth.servicesImpl.user;

import com.acl.mswauth.converter.UserConverter;
import com.acl.mswauth.dto.register.RegisterStructureDto;
import com.acl.mswauth.dto.user.UserDto;
import com.acl.mswauth.model.*;
import com.acl.mswauth.playload.ApiResponseMessage;
import com.acl.mswauth.playload.ApiResponseModel;
import com.acl.mswauth.record.UserRegistrationRecord;
import com.acl.mswauth.repositories.*;
import com.acl.mswauth.service.KeycloakUserService;
import com.acl.mswauth.service.user.UserStructureService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author kol on 11/8/24
 * @project msw-auth
 */
@Service
public class UserStructureServiceImpl implements UserStructureService {

    private final Logger logger = LoggerFactory.getLogger(UserStructureServiceImpl.class);

    private final RoleRepository roleRepository;

    private final KeycloakUserService keycloakUserService;

    private final UserRepository userRepository;

    private final StructureRepository structureRepository;

    private final GroupeClientRepository groupeClientRepository;

    private final PortClientRepository portClientRepository;

    private final GroupeRepository groupeRepository;

    private final PortRepository portRepository;

    @Autowired
    private Environment env;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserStructureServiceImpl(RoleRepository roleRepository, KeycloakUserService keycloakUserService, UserRepository userRepository, StructureRepository structureRepository, GroupeClientRepository groupeClientRepository, PortClientRepository portClientRepository, GroupeRepository groupeRepository, PortRepository portRepository) {
        this.roleRepository = roleRepository;
        this.keycloakUserService = keycloakUserService;
        this.userRepository = userRepository;
        this.structureRepository = structureRepository;
        this.groupeClientRepository = groupeClientRepository;
        this.portClientRepository = portClientRepository;
        this.groupeRepository = groupeRepository;
        this.portRepository = portRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<?> insert(RegisterStructureDto request) {
        Optional<MswStructure> mswStructure = structureRepository.findById(request.getIdStructure());
        if (mswStructure.isPresent()) {
            Optional<User> optionalUser = userRepository.findByLogin(request.getLogin());
            if (optionalUser.isPresent()) {
            return     new ResponseEntity<>(new ApiResponseMessage(HttpStatus.CONFLICT.name(), "User already exists"),
                        HttpStatus.OK);
            }else {

                addPortClient(mswStructure.get());


                UserDto userDto = userConverter.convertToRegisterStructure(request);
                logger.info("Insert user structure {}", userDto);

                User user = saveUser(userDto, mswStructure.get(), request.getIdGroupe());

                return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), "Successfully registered", request),
                        HttpStatus.OK);
            }
        }else {
            return new ResponseEntity<>(new ApiResponseMessage(HttpStatus.NOT_FOUND.name(), "Structure not exists"),
                    HttpStatus.OK);
        }
    }

    private User saveUser(UserDto userDto, MswStructure structure, Long idGroupe) {

        Role role = roleRepository.findByName("user").orElseThrow();
        User user = userConverter.convertEntity(userDto);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setFullname(userDto.getLastName() + " " + userDto.getFirstName());
        user.setRoles(Set.of(role));
        user.setMswStructure(structure);
        user.setStatus(true);
        user.setCreatedAt(new Date());
        user.setDateInscription(new Date());

        addGroupeClient(structure,idGroupe);
        logger.info("Save user {}", structure.getNom());
        //save keycloak

        UserRegistrationRecord userRecord = new UserRegistrationRecord(
                user.getLogin(), user.getEmail(), userDto.getFirstName(), userDto.getLastName(), userDto.getPassword()
        );

        logger.info("Save user structure  dans keycloak {}", userDto);

        keycloakUserService.createUser(userRecord);

        return userRepository.save(user);
    }

    public void addGroupeClient(MswStructure client, Long groupe) {
        Optional<MswGroupe> mswGroupe = groupeRepository.findById(groupe);
        if (mswGroupe.isPresent()) {
            Optional<MswGroupeClient> mswGroupeClientOptional = groupeClientRepository.findByStructureId(client.getId());
            if(mswGroupeClientOptional.isPresent()) {
                logger.info("Groupe existant {}", mswGroupe.get().getNomGroupe());
                mswGroupeClientOptional.get().setMswGroupe(mswGroupe.get());
                groupeClientRepository.save(mswGroupeClientOptional.get());
            } else {
                logger.info("Ajouter un nouveau groupe {}", mswGroupe.get().getNomGroupe());
                MswGroupeClient mswGroupeClient = new MswGroupeClient();
                mswGroupeClient.setMswStructre(client);
                mswGroupeClient.setMswGroupe(mswGroupe.get());
                groupeClientRepository.save(mswGroupeClient);
            }
        }

    }

    public void addPortClient(MswStructure client) {

        Optional<MswPort> mswPort = portRepository.findByCode(env.getProperty("message.application.port.code"));
        logger.info("Port present {}", mswPort.isPresent());
        if (mswPort.isPresent()) {
            List<MswPortClient> mswPortClient = portClientRepository.findByStructureId(client.getId());
            if(mswPortClient.isEmpty()) {
                logger.info("Ajouter un nouveau port clinet {}", mswPortClient);
                MswPortClient mswPortClient1 = new MswPortClient();
                mswPortClient1.setMswStructure(client);
                mswPortClient1.setMswPort(mswPort.get());
                mswPortClient1.setCodePortClient(env.getProperty("message.application.port.code"));
                portClientRepository.save(mswPortClient1);
            }
        }

    }
}
