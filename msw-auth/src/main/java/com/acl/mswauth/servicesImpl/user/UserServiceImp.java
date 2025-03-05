package com.acl.mswauth.servicesImpl.user;

import static org.springframework.http.HttpStatus.OK;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.acl.mswauth.model.*;
import com.acl.mswauth.playload.ApiResponseList;
import com.acl.mswauth.record.UserRegistrationRecord;
import com.acl.mswauth.repositories.*;
import com.acl.mswauth.service.ConnectedUserService;
import com.acl.mswauth.service.KeycloakUserService;
import jakarta.transaction.Transactional;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.acl.mswauth.converter.UserConverter;
import com.acl.mswauth.dto.user.UserDto;
import com.acl.mswauth.playload.ApiResponseModel;
import com.acl.mswauth.request.RegisterRequest;
import com.acl.mswauth.service.user.UserService;

@Service
public class UserServiceImp  implements UserService{
	
	    private final Logger logger = LoggerFactory.getLogger(UserServiceImp.class);
	
	    private final UserRepository userRepository;
	    private final RoleRepository roleRepository;
	    private final ClientRepository clientRepository;
	    private final PasswordEncoder passwordEncoder;
	    private final StructureRepository structureRepository;
        private final KeycloakUserService keycloakUserService;
        private final UserClientRepository userClientRepository;
	    
	    @Autowired
	    private UserConverter userConverter;

        @Autowired
        ConnectedUserService connectedUserService;
	    

	  public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository,
                            ClientRepository clientRepository, PasswordEncoder passwordEncoder,
                            StructureRepository structureRepository, KeycloakUserService keycloakUserService, UserClientRepository userClientRepository) {
			super();
			this.userRepository = userRepository;
			this.roleRepository = roleRepository;
			this.clientRepository = clientRepository;
			this.passwordEncoder = passwordEncoder;
			this.structureRepository = structureRepository;

          this.keycloakUserService = keycloakUserService;
          this.userClientRepository = userClientRepository;
      }

	/**
     * Création d'un utilisateur
     *
     * @param request
     * @return
     */

    @Override
    @Transactional
    public ResponseEntity<?> storeUser(RegisterRequest request) {

        UserDto userDto = userConverter.convertRequest(request);

        Optional<MswClient> mswClient = clientRepository.findByCompteClient(request.getCodeClient());
        if (mswClient.isPresent()) {

            Role role = roleRepository.findByName("user").orElseThrow();
            User user = userConverter.convertEntity(userDto);
            user.setMswClient(mswClient.get());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setFullname(request.getLastName() + " " + request.getFirstName());
            user.setRoles(Set.of(role));
            user.setStatus(false);
            user.setCreatedAt(new Date());

            user.setDateInscription(new Date());
            userRepository.save(user);
            UserRegistrationRecord userRecord = new UserRegistrationRecord(
                    user.getLogin(),user.getEmail(),request.getFirstName(),request.getLastName(),userDto.getPassword()
            );
            keycloakUserService.createUser(userRecord);
            return ResponseEntity.ok(new ApiResponseModel(OK.name(),
                    "User created successfully", request));
        } else {
            return ResponseEntity.ok(new ApiResponseModel(OK.name(),
                    "Client n'existe pas"));
        }
    }
    
    @Override
    public ResponseEntity<?> storeStructureUser(RegisterRequest request) {

        UserDto userDto = userConverter.convertRequest(request);
        Optional<MswStructure> mswStructure = structureRepository.findById(request.getStructureId());
        if (mswStructure.isPresent()) {
            logger.info("  Role user {} ", request.getRole());
            Optional<Role> role = roleRepository.findByName("consultant");
            if (role.isPresent()) {
                User user = userConverter.convertEntity(userDto);
                user.setMswStructure(mswStructure.get());
                user.setPassword(passwordEncoder.encode(request.getPassword()));
                user.setRoles(Set.of(role.get()));
                user.setStatus(true);
                user.setFullname(request.getLastName() + " " + request.getFirstName());
                user.setCreatedAt(new Date());
                user.setDateInscription(new Date());
                userRepository.save(user);

               UserRegistrationRecord userRecord = new UserRegistrationRecord(
                        user.getLogin(),user.getEmail(),request.getFirstName(),request.getLastName(),userDto.getPassword()
                );
                keycloakUserService.createUser(userRecord);
                return ResponseEntity.ok(new ApiResponseModel(OK.name(),
                        "User created successfully", request));
            } else {
                return ResponseEntity.ok(new ApiResponseModel(OK.name(),
                        "Role Not found", request));
            }

        } else {
            return ResponseEntity.ok(new ApiResponseModel(OK.name(),
                    "Structure n'existe pas"));
        }
    }

	@Override
	public ResponseEntity<?>  findAll() {
		return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                "Opération réussie ",userRepository.findAll()),HttpStatus.OK);
	}

    @Override
    @Transactional
    public ResponseEntity<?> deleteUser(long id) {
        logger.info("User , {}", id);
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            deleteUser(user.get().getLogin());
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                    "Opération réussie ",user.get()),HttpStatus.OK);
        } else {
            return ResponseEntity.ok(new ApiResponseModel(OK.name(),
                    "L'utilisateur  n'existe pas"));
        }

    }

    public void deleteUser(String userId){
        UsersResource usersResource = keycloakUserService.getUsersResource();
        logger.info("User , {}", userId);
        List<UserRepresentation> user = usersResource.search(userId, true);
        logger.info("Username keycloak , {}, {}", user.get(0).getUsername() , user.get(0).getEmail());
        usersResource.get(user.get(0).getId()).remove();
    }

    @Override
    public ResponseEntity<?> actifUser(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            user.get().setStatus(!user.get().isStatus());
            userRepository.save(user.get());
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                    "Opération réussie ",user.get()),HttpStatus.OK);
        } else {
            return ResponseEntity.ok(new ApiResponseModel(OK.name(),
                    "L'utilisateur  n'existe pas"));
        }    }

    /**
     * Valide user
     * @param etat
     * @return
     */
    @Override
    public ResponseEntity<?> findValideUser(boolean etat) {
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                "Opération réussie ",userRepository.findByStatus(etat)),HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> findCurrentUser(String currentUserName) {

        Optional<User> user = userRepository.findByEmail(currentUserName);


        if(user.isPresent()) {
            logger.info("utilisateur connecté {} ",user.get().getLogin());
                return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                        "Opération réussie ",user.get()),HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponseList(HttpStatus.NOT_FOUND.name(),
                "Opération échoué "),HttpStatus.OK);
    }


    @Override
	public ResponseEntity<?>  findByCodeClient(String codeClient) {
		return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                "Opération réussie ",userRepository.findByCodeClient(codeClient)),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?>  findByStructure(Long idStructure) {
		return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                "Opération réussie ",userRepository.findByStructure(idStructure)),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?>  findByStatus(boolean status) {
		return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                "Opération réussie ",userRepository.findByStatus(status)),HttpStatus.OK);
	}

    @Override
    public ResponseEntity<?> deleteConnectedUser(String currentUserName) {
        Optional<User> user = userRepository.findByEmail(currentUserName);
        if(user.isPresent()) {
            logger.info("utilisateur connecté {} ",user.get().getLogin());
            connectedUserService.deleteByLogin(user.get().getLogin());
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    "Opération réussie "),HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponseList(HttpStatus.NOT_FOUND.name(),
                "Opération échoué "),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findByStatusPageable(boolean status, int page, int size) {
        Pageable pageable =  PageRequest.of(page, size);

        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                "Opération réussie ",userRepository.findByStatus(status,pageable)),HttpStatus.OK);


    }

    @Override
    public ResponseEntity<?> findCurrentUserDetail(String currentUserName) {
        Optional<User> user = userRepository.findByEmail(currentUserName);


        if(user.isPresent()) {

            logger.info("utilisateur connecté {} ",user.get().getLogin());
            List<UserClient> userClients =  userClientRepository.findByClientLogin(user.get().getLogin());


            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    "Opération réussie ",user.get()),HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponseList(HttpStatus.NOT_FOUND.name(),
                "Opération échoué "),HttpStatus.OK);
    }
}
