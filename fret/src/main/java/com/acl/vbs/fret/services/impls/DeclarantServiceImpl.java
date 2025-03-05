package com.acl.vbs.fret.services.impls;

import com.acl.vbs.fret.converters.AppConverter;
import com.acl.vbs.fret.entities.Declarant;
import com.acl.vbs.fret.repositories.DeclarantRepository;
import com.acl.vbs.fret.requests.DeclarantRequest;
import com.acl.vbs.fret.responses.DeclarantResponse;
import com.acl.vbs.fret.responses.MSWUserResponse;
import com.acl.vbs.fret.services.AuthenticationService;
import com.acl.vbs.fret.services.DeclarantSevice;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.acl.vbs.fret.converters.AppConverter.toDeclarantResponse;

@Service
@RequiredArgsConstructor
public class DeclarantServiceImpl implements DeclarantSevice {

    private final DeclarantRepository declarantRepository;
    private final AuthenticationService authenticationService;

    @Override
    public DeclarantResponse create(DeclarantRequest request) {
        MSWUserResponse user = authenticationService.getAuthInfo();
        Declarant response = new Declarant();

        BeanUtils.copyProperties(request, response);
        response.setCreatedBy(user.getFullname());

        Declarant save = declarantRepository.save(response);
        return toDeclarantResponse(save);
    }

    @Override
    public List<DeclarantResponse> list() {
        return declarantRepository.findAll().stream().map(AppConverter::toDeclarantResponse).toList();
    }
}
