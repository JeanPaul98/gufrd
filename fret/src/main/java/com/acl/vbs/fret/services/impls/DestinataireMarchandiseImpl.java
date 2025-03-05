package com.acl.vbs.fret.services.impls;

import com.acl.vbs.fret.converters.AppConverter;
import com.acl.vbs.fret.entities.DestinataireMarchandise;
import com.acl.vbs.fret.exceptions.UnauthorizedAccessException;
import com.acl.vbs.fret.repositories.DestinataireMarchandiseRepository;
import com.acl.vbs.fret.requests.DestinataireMarchandiseRequest;
import com.acl.vbs.fret.responses.DestinataireMarchandiseResponse;
import com.acl.vbs.fret.responses.MSWUserResponse;
import com.acl.vbs.fret.services.AuthenticationService;
import com.acl.vbs.fret.services.DestinataireMarchandiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.acl.vbs.fret.converters.AppConverter.toDestinataireMarchandiseResponse;

@Service
@RequiredArgsConstructor
public class DestinataireMarchandiseImpl implements DestinataireMarchandiseService {

    private final AuthenticationService authenticationService;
    private final DestinataireMarchandiseRepository destinataireMarchandiseRepository;

    @Override
    public DestinataireMarchandiseResponse create(DestinataireMarchandiseRequest request) {
        MSWUserResponse user = authenticationService.getAuthInfo();
        DestinataireMarchandise response = new DestinataireMarchandise();

        if (!Objects.equals(user.getGroupe(), "TRANSITAIRES")) {
            throw new UnauthorizedAccessException("Accès non autorisé");
        }

        BeanUtils.copyProperties(request, response);
        response.setCreatedBy(user.getFullname());

        DestinataireMarchandise save = destinataireMarchandiseRepository.save(response);
        return toDestinataireMarchandiseResponse(save);
    }

    @Override
    public List<DestinataireMarchandiseResponse> getAll() {
        return destinataireMarchandiseRepository.findAll().stream()
                .map(AppConverter::toDestinataireMarchandiseResponse).toList();
    }

}
