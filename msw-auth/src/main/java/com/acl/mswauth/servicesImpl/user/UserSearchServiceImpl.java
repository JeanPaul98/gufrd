package com.acl.mswauth.servicesImpl.user;

import com.acl.mswauth.interfaces.UserInterface;
import com.acl.mswauth.playload.ApiResponseMessage;
import com.acl.mswauth.playload.ApiResponseModel;
import com.acl.mswauth.repositories.UserRepository;
import com.acl.mswauth.service.user.UserSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kol on 10/24/24
 * @project msw-auth
 */
@Service
public class UserSearchServiceImpl implements UserSearchService {

    private final UserRepository userRepository;

    @Autowired
    private Environment env;


    public UserSearchServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> getSearchResult(String keyword, int page, int size) {

        Pageable pageable =  PageRequest.of(page, size);
        List<UserInterface> userInterfaceList = userRepository.findAllListUsersActive();

        List<UserInterface>  filterList= userInterfaceList.stream().filter(
                c-> c.getNomEntreprise().startsWith(keyword) || c.getLogin().startsWith(keyword)
                || c.getGroupe().startsWith(keyword) || c.getFonction().startsWith(keyword)
                || c.getEmail().startsWith(keyword)
        ).collect(Collectors.toList());

        Page<UserInterface> userInterfacePage = new PageImpl<>(filterList, pageable, 0);
        if(filterList.isEmpty()){
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.application.vide"),  userInterfacePage),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.application.sucess"),  userInterfacePage),
                    HttpStatus.OK);
        }

    }
}
