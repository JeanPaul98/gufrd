package com.acl.vbs.services.impls;

import com.acl.vbs.entities.Camion;
import com.acl.vbs.entities.Pays;
import com.acl.vbs.entities.Transporteur;
import com.acl.vbs.exceptions.CamionAlreadyExistsException;
import com.acl.vbs.exceptions.CamionNotFoundException;
import com.acl.vbs.exceptions.PaysNotFoundException;
import com.acl.vbs.exceptions.TransporteurNotFoundException;
import com.acl.vbs.repositories.CamionRepository;
import com.acl.vbs.repositories.PaysRepository;
import com.acl.vbs.repositories.TransporteurRepository;
import com.acl.vbs.requests.CamionRequest;
import com.acl.vbs.responses.CamionResponse;
import com.acl.vbs.services.CamionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CamionServiceImpl implements CamionService {

    private CamionRepository repository;
    private PaysRepository countryRepository;
    private TransporteurRepository transporterRepository;

    @Override
    public List<CamionResponse> listeCamion() {
        return repository.findAll().stream().map(this::toCamionResponse).toList();
    }

    @Override
    public CamionResponse creationCamion(CamionRequest request) {
        Camion camion = new Camion();
        Pays pays = getPays(request.getCodePays());
        Transporteur transporteur = getTransporteur(request.getCodeTransporteur());
        if (repository.findByImmatriculation(request.getImmatriculation()).isPresent()) {
            throw new CamionAlreadyExistsException("Camion with immatriculation " + request.getImmatriculation() + " already exists");
        }
        BeanUtils.copyProperties(request, camion);
        camion.setCodeTransporteur(transporteur);
        camion.setCodePays(pays);
        Camion saved = repository.save(camion);
        return toCamionResponse(saved);
    }

    @Override
    public CamionResponse prendreCamion(String registration) {
        return repository.findByImmatriculation(registration).map(this::toCamionResponse)
                .orElseThrow(() -> new CamionNotFoundException("Camion not found"));
    }

    private Transporteur getTransporteur(String codeTransporter) {
        return transporterRepository.findByCodeTransporteur(codeTransporter)
                .orElseThrow(() -> new TransporteurNotFoundException("Transporteur not found"));
    }

    private Pays getPays(String codePays) {
        return countryRepository.findByCodePays(codePays)
                .orElseThrow(() -> new PaysNotFoundException("Pays not found"));
    }

    private CamionResponse toCamionResponse(Camion camion) {
        CamionResponse response = new CamionResponse();
        BeanUtils.copyProperties(camion, response);
        return response;
    }
}
