package com.acl.vbs.fret.services.impls;

import com.acl.vbs.fret.converters.AppConverter;
import com.acl.vbs.fret.entities.Camion;
import com.acl.vbs.fret.entities.Transporteur;
import com.acl.vbs.fret.exceptions.CamionNotFoundException;
import com.acl.vbs.fret.exceptions.TransporteurNotFoundException;
import com.acl.vbs.fret.repositories.CamionRepository;
import com.acl.vbs.fret.repositories.TransporteurRepository;
import com.acl.vbs.fret.requests.CamionRequest;
import com.acl.vbs.fret.responses.CamionResponse;
import com.acl.vbs.fret.responses.MSWUserResponse;
import com.acl.vbs.fret.services.AuthenticationService;
import com.acl.vbs.fret.services.CamionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.acl.vbs.fret.converters.AppConverter.toCamionResponse;

@Service
@AllArgsConstructor
public class CamionServiceImpl implements CamionService {

    private final CamionRepository repository;
    private final AuthenticationService authenticationService;
    private final TransporteurRepository transporteurRepository;

    @Override
    public List<CamionResponse> listeCamion() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"))
                .stream().map(AppConverter::toCamionResponse).toList();
    }

    @Override
    public CamionResponse creationCamion(CamionRequest request) {
        // Récupération des informations de l'utilisateur connecté
        MSWUserResponse user = authenticationService.getAuthInfo();

        Transporteur transporteur = getTransporteur(request.getTransporteur());

        Camion camion = new Camion();
        BeanUtils.copyProperties(request, camion);
        camion.setCreatedBy(user.getFullname());
        camion.setTransporteur(transporteur);
        camion.setDisponible(true);

        Camion saved = repository.save(camion);
        return toCamionResponse(saved);
    }

    @Override
    public CamionResponse prendreCamion(String immatriculation) {
        return repository.findByImmatriculation(immatriculation).map(AppConverter::toCamionResponse)
                .orElseThrow(() -> new CamionNotFoundException("Camion not found"));
    }

    private Transporteur getTransporteur(Long id) {
        return transporteurRepository.findById(id)
                .orElseThrow(() -> new TransporteurNotFoundException("Transporteur with id " + id + " not found"));
    }
}
