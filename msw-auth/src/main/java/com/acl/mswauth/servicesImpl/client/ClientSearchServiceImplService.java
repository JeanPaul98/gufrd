package com.acl.mswauth.servicesImpl.client;

import com.acl.mswauth.interfaces.UserInterface;
import com.acl.mswauth.model.MswClient;
import com.acl.mswauth.playload.ApiResponseModel;
import com.acl.mswauth.repositories.ClientRepository;
import com.acl.mswauth.service.client.ClientSearchService;
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
 * @author kol on 11/28/24
 * @project msw-auth
 */
@Service
public class ClientSearchServiceImplService implements ClientSearchService {

    private final ClientRepository clientRepository;

    @Autowired
    private Environment env;

    public ClientSearchServiceImplService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ResponseEntity<?> getSearchResult(String keyword, int page, int size) {

        Pageable pageable =  PageRequest.of(page, size);
        Page<MswClient> clients = clientRepository.findAll(pageable);

        List<MswClient>  filterList= clients.getContent().stream().filter(
                c-> c.getCompteClient().startsWith(keyword) || c.getNomClient().startsWith(keyword)
                        || c.getNif().startsWith(keyword) || c.getNomResponsable().startsWith(keyword)
                        || c.getClientEmail().startsWith(keyword)
        ).collect(Collectors.toList());

        Page<MswClient> userInterfacePage = new PageImpl<>(filterList, pageable, 0);
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
