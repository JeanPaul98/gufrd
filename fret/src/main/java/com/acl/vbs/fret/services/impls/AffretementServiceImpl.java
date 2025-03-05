package com.acl.vbs.fret.services.impls;

import com.acl.vbs.fret.converters.AppConverter;
import com.acl.vbs.fret.entities.*;
import com.acl.vbs.fret.exceptions.CamionNotFoundException;
import com.acl.vbs.fret.exceptions.ConducteurNotFoundException;
import com.acl.vbs.fret.exceptions.DmdDeclaratrionFretNotFoundException;
import com.acl.vbs.fret.repositories.AffretementRepository;
import com.acl.vbs.fret.repositories.CamionRepository;
import com.acl.vbs.fret.repositories.ConducteurRepository;
import com.acl.vbs.fret.repositories.DmdDeclarationFretRepository;
import com.acl.vbs.fret.requests.AffretementRequest;
import com.acl.vbs.fret.responses.AffretementResponse;
import com.acl.vbs.fret.responses.MSWUserResponse;
import com.acl.vbs.fret.services.AffretementService;
import com.acl.vbs.fret.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.acl.vbs.fret.converters.AppConverter.toAffretementResponse;

@Service
@RequiredArgsConstructor
public class AffretementServiceImpl implements AffretementService {

    private final AuthenticationService authenticationService;
    private final AffretementRepository affretementRepository;
    private final ConducteurRepository conducteurRepository;
    private final DmdDeclarationFretRepository dmdDeclarationFretRepository;
    private final CamionRepository camionRepository;  

    @Override
    public AffretementResponse create(AffretementRequest request){
        MSWUserResponse user = authenticationService.getAuthInfo(); 
        Affretement affretement = new Affretement();

        Conducteur conducteur = getConducteur(request.getConducteur());
        affretement.setConducteur(conducteur);

        Camion camion = getCamion(request.getCamion());
        affretement.setCamion(camion);

        DmdDeclarationFret dmdDeclarationFret = getDmdDeclarationFret(request.getDeclarationFret());
        affretement.setDeclarationFret(dmdDeclarationFret);

        BeanUtils.copyProperties(request, affretement);
        affretement.setCreatedBy(user.getFullname());
        Affretement save = affretementRepository.save(affretement);

        return toAffretementResponse(save);
    }

    @Override
    public List<AffretementResponse> getAll(){
        return affretementRepository.findAll().stream().map(AppConverter::toAffretementResponse).toList();
    }

    private Conducteur getConducteur(Long id) {
        return conducteurRepository.findById(id)
                .orElseThrow(() -> new ConducteurNotFoundException("Conducteur avec" + id + "n'est pas trouvé"));
    }

    private Camion getCamion(Long id) {
        return camionRepository.findById(id)
                .orElseThrow(() -> new CamionNotFoundException("Camion avec" + id + "n'est pas trouvé"));
    }

    private DmdDeclarationFret getDmdDeclarationFret (Long id){
        return dmdDeclarationFretRepository.findById(id)
                .orElseThrow(()-> new DmdDeclaratrionFretNotFoundException("Demande de Déclaration du Fret avec l\'" + id + "n\'est pas trouvé"));
    }
}
