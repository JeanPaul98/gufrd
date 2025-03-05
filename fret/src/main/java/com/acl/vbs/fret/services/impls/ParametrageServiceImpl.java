package com.acl.vbs.fret.services.impls;

import com.acl.vbs.fret.converters.AppConverter;
import com.acl.vbs.fret.entities.Chargeur;
import com.acl.vbs.fret.entities.Parametrage;
import com.acl.vbs.fret.exceptions.ChargeurNotFoundException;
import com.acl.vbs.fret.exceptions.ParametrageNotFoundException;
import com.acl.vbs.fret.repositories.ChargeurRepository;
import com.acl.vbs.fret.repositories.ParametrageRepository;
import com.acl.vbs.fret.requests.ParametrageRequest;
import com.acl.vbs.fret.responses.MSWUserResponse;
import com.acl.vbs.fret.responses.ParametrageResponse;
import com.acl.vbs.fret.services.AuthenticationService;
import com.acl.vbs.fret.services.ParametrageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.acl.vbs.fret.converters.AppConverter.toParametrageResponse;

@Service
@RequiredArgsConstructor
public class ParametrageServiceImpl implements ParametrageService {

    private final ParametrageRepository repository;
    private final ChargeurRepository chargeurRepository;
    private final AuthenticationService authenticationService;

    @Override
    public List<ParametrageResponse> listeParametrage() {
        return repository.findAll().stream().map(AppConverter::toParametrageResponse).toList();
    }

    @Override
    public ParametrageResponse creationParametrage(ParametrageRequest request) {
        // Récupération des informations de l'utilisateur connecté
        MSWUserResponse user = authenticationService.getAuthInfo();

        Parametrage parametrage = new Parametrage();
        Chargeur chargeur = getChargeur(user.getCompteClient());
        BeanUtils.copyProperties(request, parametrage);
        parametrage.setChargeur(chargeur);
        parametrage.setCreatedBy(user.getFullname());

        Parametrage saved = repository.save(parametrage);
        return toParametrageResponse(saved);
    }

    @Override
    public ParametrageResponse prendreParametrage(Long id) {
        return repository.findById(id).map(AppConverter::toParametrageResponse)
                .orElseThrow(() -> new ParametrageNotFoundException("Parametrage with id " + id + " not found"));
    }

    private Chargeur getChargeur(String compteClient) {
        return chargeurRepository.findByCompteClient(compteClient)
                .orElseThrow(() -> new ChargeurNotFoundException("Chargeur with compte " + compteClient + " not found"));
    }
}
