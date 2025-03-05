package com.acl.mswauth.servicesImpl.statistique;

import com.acl.mswauth.dto.statistique.UserGroupeStatistiqueDto;
import com.acl.mswauth.interfaces.StatistiqueUserGroupeInterface;
import com.acl.mswauth.playload.ApiResponseModel;
import com.acl.mswauth.repositories.UserRepository;
import com.acl.mswauth.service.statistique.StatistiqueGroupeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kol on 10/23/24
 * @project msw-auth
 */
@Service
public class StatistiqueGroupeServiceImpl implements StatistiqueGroupeService {

    private final UserRepository userRepository;

    @Autowired
    private Environment env;

    public StatistiqueGroupeServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> getListeStatistiqueGroupe() {
        List<StatistiqueUserGroupeInterface> statistiqueUserGroupeInterfaces = userRepository.findListStatisqueGroupe();
        List<UserGroupeStatistiqueDto> userGroupeStatistiqueDtos = new ArrayList<>();
        statistiqueUserGroupeInterfaces.forEach(data-> {
            UserGroupeStatistiqueDto userGroupeStatistiqueDto = new UserGroupeStatistiqueDto(data.getNomGroupe(), data.getNombre());
            userGroupeStatistiqueDtos.add(userGroupeStatistiqueDto);
        });

        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.application.sucess"),
                userGroupeStatistiqueDtos), HttpStatus.OK);
    }
}
