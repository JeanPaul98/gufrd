package com.acl.formaliteagricultureapi.serviceImpl.agrement;

import com.acl.formaliteagricultureapi.converter.DemandeAutorisationAgrementConverter;
import com.acl.formaliteagricultureapi.dto.agrement.AgrementBySocieteReturnDto;
import com.acl.formaliteagricultureapi.models.AutorisationAgrement;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.AutorisationAgrementRepository;
import com.acl.formaliteagricultureapi.repository.DemandeAutorisationAgrementRepository;
import com.acl.formaliteagricultureapi.repository.SocieteRepository;
import com.acl.formaliteagricultureapi.repository.AgrementRepository;
import com.acl.formaliteagricultureapi.services.agrement.AgrementListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kol on 22/09/2024
 * @Update Zansouyé on 26/09/2024
 * @project formalite-agriculture-api
 */
@Service
public class AgrementListServiceImpl implements AgrementListService {

    private final DemandeAutorisationAgrementRepository demandeAutorisationAgrementRepository;

    private final SocieteRepository societeRepository;

    private final AutorisationAgrementRepository autorisationAgrementRepository;

    @Autowired
    private Environment env;

    @Autowired
    private DemandeAutorisationAgrementConverter demandeAutorisationAgrementConverter;

    public AgrementListServiceImpl(DemandeAutorisationAgrementRepository demandeAutorisationAgrementRepository,
                                   SocieteRepository societeRepository, AutorisationAgrementRepository autorisationAgrementRepository) {
        this.demandeAutorisationAgrementRepository = demandeAutorisationAgrementRepository;
        this.societeRepository = societeRepository;
        this.autorisationAgrementRepository = autorisationAgrementRepository;
    }

    @Override
    public ResponseEntity<?> listAgrement() {

        List<AutorisationAgrement> agrements = autorisationAgrementRepository.findAll();

        if (agrements.isEmpty()) {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.list.vide"), agrements), HttpStatus.OK);

        } else {

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"), agrements), HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<?> getAgrementBySociete(String nif) {

        /*Optional<Societe> optionalSociete= societeRepository.findByNif(nif);
        if(optionalSociete.isPresent()){
            Set<AutorisationAgrement>agrements= optionalSociete.get().getAgrements();

            List<AgrementBySocieteReturnDto> agrementListDtoBySocieteReturn = new ArrayList<>();
            agrements.forEach(data->{
                AgrementBySocieteReturnDto agr= new AgrementBySocieteReturnDto(data.getNumero(),
                        data.getActivite(), data.getDateAgrement(), data.getSociete().getRaisonSociale(),
                        data.getSociete().getNif());
                agrementListDtoBySocieteReturn.add(agr);

                agr= new AgrementBySocieteReturnDto();
            });

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"), agrementListDtoBySocieteReturn), HttpStatus.OK);

        }else{
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.not.found.entity")), HttpStatus.OK);
        }*/
        return null;
    }




}
