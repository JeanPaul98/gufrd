package com.acl.escalenavire.services.Impl;

import com.acl.escalenavire.dto.AnnonceEscaleDto;
import com.acl.escalenavire.models.AnnonceEscale;
import com.acl.escalenavire.playload.ApiResponseModel;
import com.acl.escalenavire.repositories.AnnonceEscaleRepository;
import com.acl.escalenavire.services.AnnonceEscaleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnnonceEscaleServiceImpl implements AnnonceEscaleService {

    private final AnnonceEscaleRepository annonceEscaleRepository;

    public AnnonceEscaleServiceImpl(AnnonceEscaleRepository annonceEscaleRepository) {
        this.annonceEscaleRepository = annonceEscaleRepository;
    }

    @Override
    public ResponseEntity<?> list(int page,int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AnnonceEscale> annonce = annonceEscaleRepository.AllAnnonce(pageable);
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    "AnnonceEscale liste", annonce), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> listAnnByNumeroAan(String numeroAan) {
        Optional<AnnonceEscale> numAa = annonceEscaleRepository.findByNumeroAan(numeroAan);
        if (numAa.isPresent()){
            AnnonceEscale escale = numAa.get();
            AnnonceEscaleDto dto = new AnnonceEscaleDto(
            escale.getPortProvenance().getNomPort(),
            escale.getPortDestination().getNomPort(),
            escale.getIdNavire().getNomNavire(),
            escale.getIdNavire().getImo(),
            escale.getNomAffreteurDepart(),
            escale.getNomAffreteurArrivee()
            );
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    "AnnonceEscale trouvée avec succès", dto), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                "AnnonceEscale non trouvé", null), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> listAnnByAffect(String affreteurArrivee) {
        Optional<AnnonceEscale> aft = annonceEscaleRepository.findByNomAffreteurArrivee(affreteurArrivee);
        if (aft.isPresent()){
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    "AnnonceEscale trouvée avec succès", aft), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                "AnnonceEscale non trouvé", null), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> findPortByAnnonceEscale(Long id) {
        Optional<AnnonceEscale> annonceEscaleOptional = annonceEscaleRepository.findById(id);
        if (annonceEscaleOptional.isPresent()){
            AnnonceEscale annonceEscale = annonceEscaleOptional.get();
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    "Port trouvé avec succès", annonceEscale.getPortProvenance()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                "Port non trouvé", null), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> findNavireByAnnonceEscale(Long id) {
        Optional<AnnonceEscale> annonceEscaleOptional = annonceEscaleRepository.findById(id);
        if (annonceEscaleOptional.isPresent()){
            AnnonceEscale annonceEscale = annonceEscaleOptional.get();
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    "Navire trouvé avec succès", annonceEscale.getIdNavire()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                "Navire non trouvé", null), HttpStatus.NOT_FOUND);
    }
}
