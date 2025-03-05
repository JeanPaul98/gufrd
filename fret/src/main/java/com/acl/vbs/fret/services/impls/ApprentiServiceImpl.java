package com.acl.vbs.fret.services.impls;

import com.acl.vbs.fret.converters.AppConverter;
import com.acl.vbs.fret.entities.Apprenti;
import com.acl.vbs.fret.entities.Conducteur;
import com.acl.vbs.fret.exceptions.ConducteurNotFoundException;
import com.acl.vbs.fret.repositories.ApprentiRepository;
import com.acl.vbs.fret.repositories.ConducteurRepository;
import com.acl.vbs.fret.requests.ApprentiRequest;
import com.acl.vbs.fret.responses.ApprentiResponse;
import com.acl.vbs.fret.responses.MSWUserResponse;
import com.acl.vbs.fret.services.ApprentiService;
import com.acl.vbs.fret.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.acl.vbs.fret.converters.AppConverter.toApprentiResponse;

@Service
@RequiredArgsConstructor
public class ApprentiServiceImpl implements ApprentiService {

    private final ApprentiRepository apprentiRepository;
    private final ConducteurRepository conducteurRepository;
    private final AuthenticationService authenticationService;

    @Override
    public ApprentiResponse create(ApprentiRequest request) {
        MSWUserResponse user = authenticationService.getAuthInfo();

        Apprenti apprenti = new Apprenti();
        BeanUtils.copyProperties(request, apprenti);

        Conducteur conducteur = getConducteur(request.getConducteur());
        apprenti.setConducteur(conducteur);

        apprenti.setCreatedBy(user.getFullname());
        Apprenti save = apprentiRepository.save(apprenti);

        return toApprentiResponse(save);
    }

    @Override
    public List<ApprentiResponse> list() {
        return apprentiRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt")).stream().map(AppConverter::toApprentiResponse).toList();
    }

    private Conducteur getConducteur(Long idConducteur) {
        Optional<Conducteur> conducteur = conducteurRepository.findById(idConducteur);
        return conducteur.orElseThrow(() -> new ConducteurNotFoundException("Conducteur with id " + idConducteur + " not found"));
    }
}
