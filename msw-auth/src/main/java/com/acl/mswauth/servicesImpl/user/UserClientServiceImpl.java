package com.acl.mswauth.servicesImpl.user;

import com.acl.mswauth.converter.UserConverter;
import com.acl.mswauth.dto.register.RegisterDto;
import com.acl.mswauth.dto.user.UserDto;
import com.acl.mswauth.exception.ClientNotFoundException;
import com.acl.mswauth.model.*;
import com.acl.mswauth.playload.ApiResponseModel;
import com.acl.mswauth.record.UserRegistrationRecord;
import com.acl.mswauth.repositories.*;
import com.acl.mswauth.service.ConnectedUserService;
import com.acl.mswauth.service.KeycloakUserService;
import com.acl.mswauth.service.user.UserClientService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.http.HttpStatus.OK;

/**
 * @author kol on 10/15/24
 * @project msw-auth
 */
@Service
public class UserClientServiceImpl implements UserClientService {

    private final Logger logger = LoggerFactory.getLogger(UserClientServiceImpl.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final GroupeRepository groupeRepository;
    private final MswGroupeClientRepository groupeClientRepository;

    private final PortClientRepository portClientRepository;

    private final PortRepository portRepository;

    private final UserClientRepository userClientRepository;

    private final KeycloakUserService keycloakUserService;
    @Autowired
    ConnectedUserService connectedUserService;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private Environment env;

    public UserClientServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ClientRepository clientRepository,
                                 PasswordEncoder passwordEncoder, GroupeRepository groupeRepository, MswGroupeClientRepository groupeClientRepository, PortClientRepository portClientRepository, PortRepository portRepository, UserClientRepository userClientRepository, KeycloakUserService keycloakUserService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
        this.groupeRepository = groupeRepository;
        this.groupeClientRepository = groupeClientRepository;
        this.portClientRepository = portClientRepository;
        this.portRepository = portRepository;
        this.userClientRepository = userClientRepository;
        this.keycloakUserService = keycloakUserService;
    }

    @Override
    @Transactional
    public ResponseEntity<?> insert(RegisterDto request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getClients().get(0).getEmail());
        if (userOptional.isPresent()) {
            //si l'utilisateur existe on fait une mise a jour
                //update user
            saveUserUpdate(userOptional.get(), request);
            request.getClients().forEach(client -> {
                Optional<MswClient> mswClient = clientRepository.findByCompteClient(client.getCompteClient());
                if (mswClient.isPresent()) {
                    UserClient userClient = new UserClient();
                    userClient.setUser(userOptional.get());
                    userClient.setMswClient(mswClient.get());
                    userClient.setMail(client.getEmail());
                    userClient.setMobile(client.getTelephoneNumber());
                    userClient.setFonction(client.getFonction());
                    userClient.setCreatedAt(new Date());
                    addGroupeClient(mswClient.get(), client.getIdGroupe());
                    addPortClient(mswClient.get());
                    userClientRepository.save(userClient);
                } else  {
                    throw  new ClientNotFoundException(env.getProperty("message.application.notfound"));
                }
            });
            return new ResponseEntity<>(new ApiResponseModel(OK.name(), env.getProperty("message.application.sucess"),request.getLogin()), OK);

        } else {
            UserDto userDto = userConverter.convertToRegister(request);
            userDto.setEmail(request.getClients().get(0).getEmail());
            User user = saveUser(userDto);
            request.getClients().forEach(client -> {
                Optional<MswClient> mswClient = clientRepository.findByCompteClient(client.getCompteClient());
                if (mswClient.isPresent()) {
                    UserClient userClient = new UserClient();
                    userClient.setUser(user);
                    userClient.setMswClient(mswClient.get());
                    userClient.setMail(client.getEmail());
                    userClient.setMobile(client.getTelephoneNumber());
                    userClient.setFonction(client.getFonction());
                    userClient.setCreatedAt(new Date());
                    addGroupeClient(mswClient.get(), client.getIdGroupe());
                    addPortClient(mswClient.get());
                    userClientRepository.save(userClient);
                } else  {
                    throw  new ClientNotFoundException(env.getProperty("message.application.notfound"));
                }
            });
            return new ResponseEntity<>(new ApiResponseModel(OK.name(), env.getProperty("message.application.sucess"),request.getLogin()), OK);
        }
    }

    public void addGroupeClient(MswClient client, Long groupe) {
        Optional<MswGroupe> mswGroupe = groupeRepository.findById(groupe);
        if (mswGroupe.isPresent()) {
            logger.info("Groupe de l'utilisateur exist {}", mswGroupe.get().getNomGroupe());
            Optional<MswGroupeClient> mswGroupeClientOptional = groupeClientRepository.findByClient(client.getId());
            if(mswGroupeClientOptional.isPresent()) {
                mswGroupeClientOptional.get().setMswGroupe(mswGroupe.get());
              groupeClientRepository.save(mswGroupeClientOptional.get());
            } else {
                logger.info("Groupe de l'utilisateur n'existe pas{}", mswGroupe.get().getNomGroupe());
                MswGroupeClient mswGroupeClient = new MswGroupeClient();
                mswGroupeClient.setMswClient(client);
                mswGroupeClient.setMswGroupe(mswGroupe.get());
                groupeClientRepository.save(mswGroupeClient);
            }
        }

    }

    public void addPortClient(MswClient client) {
        Optional<MswPort> mswPort = portRepository.findByCode(env.getProperty("message.application.port.code"));
        if (mswPort.isPresent()) {
            List<MswPortClient> mswPortClient = portClientRepository.findByClient(client.getCompteClient());
            if(mswPortClient.isEmpty()) {
                MswPortClient mswPortClient1 = new MswPortClient();
                mswPortClient1.setMswClient(client);
                mswPortClient1.setMswPort(mswPort.get());
                mswPortClient1.setCodePortClient(env.getProperty("message.application.port.code"));
                portClientRepository.save(mswPortClient1);
            }
        }

    }

    private User saveUser(UserDto userDto) {

        Role role = roleRepository.findByName("user").orElseThrow();
        User user = userConverter.convertEntity(userDto);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setFullname(userDto.getLastName() + " " + userDto.getFirstName());
        user.setRoles(Set.of(role));
        user.setStatus(true);
        user.setCreatedAt(new Date());
        user.setDateInscription(new Date());

        //save keycloak

        UserRegistrationRecord userRecord = new UserRegistrationRecord(
                user.getLogin(), user.getEmail(), userDto.getFirstName(), userDto.getLastName(), userDto.getPassword()
        );

        keycloakUserService.createUser(userRecord);

        return userRepository.save(user);
    }

    private User saveUserUpdate(User userupdate,RegisterDto request) {

        userupdate.setUpdatedAt(new Date());
        userupdate.setLogin(request.getLogin());
        logger.info("Update user {} ",userupdate.getLogin());

        //save keycloak

        UserRegistrationRecord userRecord = new UserRegistrationRecord(
                userupdate.getLogin(), userupdate.getEmail(), request.getFirstName(), request.getLastName(), request.getPassword()
        );

        keycloakUserService.createUser(userRecord);

        return userRepository.save(userupdate);
    }


}
