package com.acl.formaliteagricultureapi.serviceImpl.agrement;

import com.acl.formaliteagricultureapi.dto.agrement.AgrementDto;
import com.acl.formaliteagricultureapi.models.Agrement;
import com.acl.formaliteagricultureapi.models.AutorisationAgrement;
import com.acl.formaliteagricultureapi.models.Structure;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.AgrementRepository;
import com.acl.formaliteagricultureapi.repository.AutorisationAgrementRepository;
import com.acl.formaliteagricultureapi.repository.StructureRepository;
import com.acl.formaliteagricultureapi.services.agrement.AgrementService;
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
 * @author Zansouyé on 26/09/2024
 * @project formalite-agriculture-api
 */
@Service
public class AgrementServiceImpl implements AgrementService {

    private Logger logger= LoggerFactory.getLogger(AgrementServiceImpl.class);

    private final AgrementRepository agrementRepository;

    private final StructureRepository structureRepository;

    private final AutorisationAgrementRepository autorisationAgrementRepository;

    public AgrementServiceImpl(AgrementRepository agrementRepository,
                               StructureRepository structureRepository,
                               AutorisationAgrementRepository autorisationAgrementRepository) {
        this.agrementRepository = agrementRepository;
        this.structureRepository=structureRepository;
        this.autorisationAgrementRepository=autorisationAgrementRepository;
    }

    @Autowired
    private Environment env;


    @Override
    public ResponseEntity<?> createAgrement(AgrementDto agrementDto) {

        Optional<Structure>optionalStructure= structureRepository.findById(agrementDto.getIdStructure());
        if(optionalStructure.isPresent()){
            Agrement agrement= new Agrement(agrementDto.getEtatTypeAgrement(), agrementDto.getLibelle(),
                    agrementDto.getCompteClient(), agrementDto.getMontant(), agrementDto.getDuree(),
                    new Date(), optionalStructure.get());

            Agrement saveAgrement= agrementRepository.save(agrement);

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"), saveAgrement), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.notfound")), HttpStatus.OK);
        }


    }

    @Override
    public ResponseEntity<?> findByNumeroAgrement(String numero) {
        Optional<AutorisationAgrement>optionalAutorisationAgrement=autorisationAgrementRepository.findByNumero(numero);
        return optionalAutorisationAgrement.map(autorisationAgrement ->
                new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                env.getProperty("message.succes"), autorisationAgrement),
                HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                env.getProperty("message.notfound")), HttpStatus.OK));


    }
}
