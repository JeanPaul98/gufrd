package com.acl.vbs.fret.services.impls;

import com.acl.vbs.fret.converters.AppConverter;
import com.acl.vbs.fret.entities.Chargeur;
import com.acl.vbs.fret.exceptions.ChargeurNotFoundException;
import com.acl.vbs.fret.repositories.ChargeurRepository;
import com.acl.vbs.fret.requests.ChargeurRequest;
import com.acl.vbs.fret.responses.ChargeurResponse;
import com.acl.vbs.fret.responses.MSWUserResponse;
import com.acl.vbs.fret.services.AuthenticationService;
import com.acl.vbs.fret.services.ChargeurService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.acl.vbs.fret.converters.AppConverter.toChargeurResponse;

@Service
@RequiredArgsConstructor
public class ChargeurServiceImpl implements ChargeurService {

    private final ChargeurRepository repository;
    private final AuthenticationService authenticationService;

    @Override
    public List<ChargeurResponse> listeChargeur() {
        return repository.findAll().stream().map(AppConverter::toChargeurResponse).toList();
    }

    @Override
    public ChargeurResponse creationChargeur(ChargeurRequest request) {
        // Récupération des informations de l'utilisateur connecté
        MSWUserResponse user = authenticationService.getAuthInfo();

        Chargeur chargeur = new Chargeur();
        BeanUtils.copyProperties(request, chargeur);
        chargeur.setCreatedBy(user.getFullname());
        chargeur.setCompteClient(user.getCompteClient());
        Chargeur chargeurSave = repository.save(chargeur);
        return toChargeurResponse(chargeurSave);
    }

    @Override
    public ChargeurResponse prendreChargeur(String nom) {
        return repository.findByNom(nom).map(AppConverter::toChargeurResponse)
                .orElseThrow(() -> new ChargeurNotFoundException("Chargeur not found"));
    }

}
