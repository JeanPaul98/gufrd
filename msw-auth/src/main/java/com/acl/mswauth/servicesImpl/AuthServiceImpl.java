package com.acl.mswauth.servicesImpl;


import com.acl.mswauth.converter.UserConverter;
import com.acl.mswauth.model.Role;
import com.acl.mswauth.model.User;
import com.acl.mswauth.playload.ApiResponseModel;
import com.acl.mswauth.playload.JwtAuthenticationResponse;
import com.acl.mswauth.playload.ReturnUser;
import com.acl.mswauth.repositories.ClientRepository;
import com.acl.mswauth.repositories.RoleRepository;
import com.acl.mswauth.repositories.StructureRepository;
import com.acl.mswauth.repositories.UserRepository;
import com.acl.mswauth.request.LoginRequest;
import com.acl.mswauth.security.JwtServices;
import com.acl.mswauth.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@Service
public class AuthServiceImpl implements AuthService {

    private final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final JwtServices jwtServices;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    

    @Autowired
    private UserConverter userConverter;

    public AuthServiceImpl(JwtServices jwtServices, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtServices = jwtServices;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Connexion de l'utilisateur
     *
     * @param loginRequest
     * @return
     */
    @Override
    public ResponseEntity<?> authenticate(LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByLogin(loginRequest.getLogin());
        if (userOptional.isPresent()) {
            logger.info("User is present {} ", userOptional.get().getLogin());
            return checkUserDetails(loginRequest, userOptional);
        } else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    "User not found"), OK);
        }
    }

    /**
     * Vérifie si l'utilisateur est présent
     *
     * @param loginRequest
     * @param userOptional
     * @return
     */
    private ResponseEntity<?> checkUserDetails(LoginRequest loginRequest, Optional<User> userOptional) {
        boolean matches = passwordEncoder.matches(loginRequest.getPassword(), userOptional.get().getPassword());
        logger.info("User password Match : " + matches);
        ReturnUser user = new ReturnUser();
        if (matches) {
            String jwt = jwtServices.generateToken(userOptional.get());
            if (userOptional.get().getMswClient() != null) {
                user = new ReturnUser(userOptional.get().getLogin(), userOptional.get().getFullname(),
                                      userOptional.get().getMswClient().getCompteClient(), userOptional.get().getMswClient().getNomClient(),
                                      userOptional.get().getEmail(),  userOptional.get().getMobile(), userOptional.get().getFonction(),userOptional.get().getRoles());
            } else if(userOptional.get().getMswStructure()!=null) {
            	user = new ReturnUser(userOptional.get().getLogin(), userOptional.get().getFullname(),
                                      userOptional.get().getMswStructure().getNom(), userOptional.get().getEmail(),
                                      userOptional.get().getMobile(), userOptional.get().getFonction(), userOptional.get().getRoles());	
            }
            else {
                user = returnUser(userOptional.get().getLogin(), userOptional.get().getFullname(),
                        "admin", "administrateur",
                        userOptional.get().getEmail(), userOptional.get().getRoles());
            }
            return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, "Bearer",
                    user)
            );
        } else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    "Password Incorrect"), OK);
        }
    }

    /**
     * retourne l'utilisateur connecté
     *
     * @param login
     * @param fullname
     * @param compteClient
     * @param email
     * @param roles
     * @return
     */
    private ReturnUser returnUser(String login, String fullname, String compteClient, String nomClient,
                                  String email,  Set<Role> roles) {
        return new ReturnUser(login, fullname, compteClient, nomClient, email, roles);
    }

  

    @Override
    public ResponseEntity<?> authenticateUser(Authentication authentication) {
        Optional<User> user = userRepository.findByLogin(authentication.getName());
        return user.map(userData -> ResponseEntity.status(OK).body(new ApiResponseModel(OK.name(), "User data successfully", userData)))
                .orElseGet(() -> ResponseEntity.status(NOT_FOUND).body(new ApiResponseModel(NOT_FOUND.name(), "User not found")));
    }

    @Override
    public ResponseEntity<?> currentUser(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    "Opératiion effectué avec succès", user.get()), HttpStatus.OK
            );

        } else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    "Utilisateur non present"), HttpStatus.OK);

        }

    }

    @Override
    public String currentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {

            return authentication.getName();

        }else {
            return null;
        }
    }

}
