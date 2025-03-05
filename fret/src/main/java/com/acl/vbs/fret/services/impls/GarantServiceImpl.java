package com.acl.vbs.fret.services.impls;

import com.acl.vbs.fret.converters.AppConverter;
import com.acl.vbs.fret.entities.Chargeur;
import com.acl.vbs.fret.entities.Garant;
import com.acl.vbs.fret.repositories.GarantRepository;
import com.acl.vbs.fret.requests.GarantRequest;
import com.acl.vbs.fret.responses.GarantResponse;
import com.acl.vbs.fret.responses.MSWUserResponse;
import com.acl.vbs.fret.services.AuthenticationService;
import com.acl.vbs.fret.services.GarantService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.acl.vbs.fret.converters.AppConverter.toGarantResponse;

@Service
public class GarantServiceImpl implements GarantService {

    private final AuthenticationService authenticationService;
    private final GarantRepository garantRepository;

    public GarantServiceImpl(AuthenticationService authenticationService, GarantRepository garantRepository) {
        this.authenticationService = authenticationService;
        this.garantRepository = garantRepository;
    }

    @Override
    public GarantResponse create(GarantRequest request) {
        MSWUserResponse user = authenticationService.getAuthInfo();
        Garant response = new Garant();
        BeanUtils.copyProperties(request, response);
        response.setCreatedBy(user.getFullname());
        Garant garant = garantRepository.save(response);
        return toGarantResponse(garant);
    }

    @Override
    public List<GarantResponse> list() {
        return garantRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt")).stream().map(AppConverter::toGarantResponse).toList();
    }

}
