package com.acl.formaliteenvironnementapi.serviceImpl.formalite;



import com.acl.formaliteenvironnementapi.dto.formalite.RejetFormaliteDto;
import com.acl.formaliteenvironnementapi.dto.formalite.UpdateFormaliteDto;
import com.acl.formaliteenvironnementapi.models.Formalite;
import com.acl.formaliteenvironnementapi.models.enumeration.Etat;
import com.acl.formaliteenvironnementapi.playload.ApiResponseModel;
import com.acl.formaliteenvironnementapi.repository.FormaliteRepository;
import com.acl.formaliteenvironnementapi.services.formalite.FormaliteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * @author kol on 20/08/2024
 * @project formalite-agriculture-api
 */
@Service
public class FormaliteServiceImpl implements FormaliteService {

    private final Logger logger= LoggerFactory.getLogger(FormaliteServiceImpl.class);

    private final FormaliteRepository formaliteRepository;

    @Autowired
    private Environment env;

    public FormaliteServiceImpl(FormaliteRepository formaliteRepository) {
        this.formaliteRepository = formaliteRepository;
    }

    @Override
    public ResponseEntity<?> soumettreDemande(UpdateFormaliteDto updateFormaliteDto) {
        Optional<Formalite> optionalFormalite = formaliteRepository.findById(updateFormaliteDto.getIdFormalite());
        if (optionalFormalite.isPresent()) {
            optionalFormalite.get().setEtat(Etat.SOUMIS);
            optionalFormalite.get().setDateSoumise(new Date());
            optionalFormalite.get().setUpdateAt(new Date());
            Formalite savedFormalite = formaliteRepository.save(optionalFormalite.get());
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes")),HttpStatus.OK );

        } else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.echec")),HttpStatus.OK );
        }
    }

    @Override
    public ResponseEntity<?> accepterDemande(UpdateFormaliteDto updateFormaliteDto) {
        Optional<Formalite> optionalFormalite = formaliteRepository.findById(updateFormaliteDto.getIdFormalite());
        if (optionalFormalite.isPresent()) {
            optionalFormalite.get().setEtat(Etat.ACCEPTER);
            optionalFormalite.get().setDateAccepte(new Date());
            optionalFormalite.get().setUpdateAt(new Date());
            Formalite savedFormalite = formaliteRepository.save(optionalFormalite.get());

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"), savedFormalite.getId()),HttpStatus.OK );

        }else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.echec")),HttpStatus.OK );
        }

    }

    @Override
    public ResponseEntity<?> annulerDemande(UpdateFormaliteDto updateFormaliteDto) {
        Optional<Formalite> optionalFormalite = formaliteRepository.findById(updateFormaliteDto.getIdFormalite());
        if (optionalFormalite.isPresent()) {
            optionalFormalite.get().setEtat(Etat.ANNULER);
            optionalFormalite.get().setDateAccepte(new Date());
            optionalFormalite.get().setUpdateAt(new Date());
            Formalite savedFormalite = formaliteRepository.save(optionalFormalite.get());

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"), savedFormalite.getId()),HttpStatus.OK );

        }else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.echec")),HttpStatus.OK );
        }    }

    @Override
    public ResponseEntity<?> rejeterDemande(RejetFormaliteDto request) {
        Optional<Formalite> optionalFormalite = formaliteRepository.findById(request.getIdFormalite());
        if (optionalFormalite.isPresent()) {
            optionalFormalite.get().setEtat(Etat.REJETER);
            optionalFormalite.get().setDateAccepte(new Date());
            optionalFormalite.get().setMotifAnnulation(request.getMotifRejet());
            optionalFormalite.get().setUpdateAt(new Date());
            Formalite savedFormalite = formaliteRepository.save(optionalFormalite.get());

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"), savedFormalite.getId()),HttpStatus.OK );

        }else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.echec")),HttpStatus.OK );
        }
    }
}
