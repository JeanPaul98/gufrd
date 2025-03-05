package com.acl.vbs.fret.services.impls;

import com.acl.vbs.fret.converters.AppConverter;
import com.acl.vbs.fret.entities.Garant;
import com.acl.vbs.fret.entities.Transporteur;
import com.acl.vbs.fret.exceptions.GarantNotFoundException;
import com.acl.vbs.fret.exceptions.TransporteurNotFoundException;
import com.acl.vbs.fret.repositories.GarantRepository;
import com.acl.vbs.fret.repositories.TransporteurRepository;
import com.acl.vbs.fret.requests.TransporteurRequest;
import com.acl.vbs.fret.responses.MSWUserResponse;
import com.acl.vbs.fret.responses.TransporteurResponse;
import com.acl.vbs.fret.services.AuthenticationService;
import com.acl.vbs.fret.services.TransporteurService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.acl.vbs.fret.converters.AppConverter.toTransporteurResponse;

@Service
@RequiredArgsConstructor
public class TransporteurServiceImpl implements TransporteurService {

    private final GarantRepository garantRepository;
    private final TransporteurRepository repository;
    private final AuthenticationService authenticationService;

    @Override
    public List<TransporteurResponse> listeTransporteur() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"))
                .stream().map(AppConverter::toTransporteurResponse).toList();
    }

    @Override
    public TransporteurResponse creationTransporteur(TransporteurRequest request) {
        // Récupération des informations de l'utilisateur connecté
        MSWUserResponse user = authenticationService.getAuthInfo();

        Garant garant = getGarant(request.getGarant());

        Transporteur transporteur = new Transporteur();
        BeanUtils.copyProperties(request, transporteur);
        transporteur.setCreatedBy(user.getFullname());
        transporteur.setGarant(garant);

        Transporteur saved = repository.save(transporteur);
        return toTransporteurResponse(saved);
    }

    @Override
    public TransporteurResponse prendreTransporteur(String raisonSociale) {
        return repository.findByRaisonSociale(raisonSociale).map(AppConverter::toTransporteurResponse)
                .orElseThrow(() -> new TransporteurNotFoundException("Transporteur not found"));
    }

    private Garant getGarant(Long id) {
        return garantRepository.findById(id).orElseThrow(() -> new GarantNotFoundException("Garant not found"));
    }
}
