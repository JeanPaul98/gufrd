package com.acl.mswauth.servicesImpl.user;

import com.acl.mswauth.dto.user.UserStatistiqueDto;
import com.acl.mswauth.interfaces.UserInterface;
import com.acl.mswauth.model.User;
import com.acl.mswauth.playload.ApiResponseMessage;
import com.acl.mswauth.playload.ApiResponseModel;
import com.acl.mswauth.repositories.UserRepository;
import com.acl.mswauth.service.user.UserService;
import com.acl.mswauth.service.user.UserStatisitiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kol on 10/21/24
 * @project msw-auth
 */
@Service
public class UserStatistiqueServiceImpl implements UserStatisitiqueService {

private final UserRepository userRepository;


@Autowired
private Environment env;

    public UserStatistiqueServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> getUserStatisitique() {
        List<User> users = userRepository.findByStatus(true);
        if (users.isEmpty()) {
            return  new ResponseEntity<>(new ApiResponseMessage(HttpStatus.OK.name(),
                    env.getProperty("message.application.vide")), HttpStatus.OK );
        }
        else {
            List<UserInterface> userEntreprises = userRepository.findListEntreprise();
            long nombreUser = users.size();
            long nombreEntreprises = userEntreprises.size();
            UserStatistiqueDto userStatistiqueDto = new UserStatistiqueDto(nombreUser, nombreEntreprises);
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.application.sucess"), userStatistiqueDto), HttpStatus.OK);
        }
    }
}
