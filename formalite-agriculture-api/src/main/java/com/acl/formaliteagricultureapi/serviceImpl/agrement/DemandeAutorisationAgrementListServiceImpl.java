package com.acl.formaliteagricultureapi.serviceImpl.agrement;

import com.acl.formaliteagricultureapi.converter.DemandeAutorisationAgrementConverter;
import com.acl.formaliteagricultureapi.dto.agrement.DemandeAgrementListDto;
import com.acl.formaliteagricultureapi.models.Agrement;
import com.acl.formaliteagricultureapi.models.DmdAutorisationAgrement;
import com.acl.formaliteagricultureapi.models.Societe;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.AgrementRepository;
import com.acl.formaliteagricultureapi.repository.DemandeAutorisationAgrementRepository;
import com.acl.formaliteagricultureapi.repository.SocieteRepository;
import com.acl.formaliteagricultureapi.services.agrement.DemandeAutorisationAgrementListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Zansouyé on 04/10/2024
 * @project formalite-agriculture-api
 */
@Service
public class DemandeAutorisationAgrementListServiceImpl implements DemandeAutorisationAgrementListService {

    Logger logger= LoggerFactory.getLogger(DemandeAutorisationAgrementListServiceImpl.class);

    private final DemandeAutorisationAgrementRepository demandeAutorisationAgrementRepository;

    private final SocieteRepository societeRepository;

    private final AgrementRepository agrementRepository;

    @Autowired
    private DemandeAutorisationAgrementConverter autorisationAgrementConverter;

    public DemandeAutorisationAgrementListServiceImpl(DemandeAutorisationAgrementRepository
                                                              demandeAutorisationAgrementRepository, SocieteRepository societeRepository, AgrementRepository agrementRepository) {
        this.demandeAutorisationAgrementRepository = demandeAutorisationAgrementRepository;
        this.societeRepository = societeRepository;
        this.agrementRepository = agrementRepository;
    }

    @Autowired
    private Environment env;

    @Override
    public ResponseEntity<?> listDemandeAutorisationAgrement(Etat etat, String compteDemandeur) {

        if(compteDemandeur.isEmpty()){
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.notfound")), HttpStatus.OK);
        }else{
            List<DmdAutorisationAgrement> dmdAutorisationAgrementList=demandeAutorisationAgrementRepository.
                    listDmdAutorisationAgrementByEtatAndUser(etat.name(), compteDemandeur);

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"),dmdAutorisationAgrementList), HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<?> listAutorisationAgrements(Etat etat) {
        List<DmdAutorisationAgrement> dmdAutorisationAgrementList=demandeAutorisationAgrementRepository.
                listDmdAutorisationAgrementByEtat(etat);

        if (dmdAutorisationAgrementList.isEmpty()){

            List<DemandeAgrementListDto> demandeAgrementListDtos = new ArrayList<>();

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.list.vide"),dmdAutorisationAgrementList), HttpStatus.OK);
        } else {

            List<DemandeAgrementListDto> demandeAgrementListDtos = new ArrayList<>();
            dmdAutorisationAgrementList.forEach(d-> {
                DemandeAgrementListDto demandeAgrementListDto= autorisationAgrementConverter.convertEntityDto(d);
                        demandeAgrementListDto.setIdAgrement(d.getCode());
                        demandeAgrementListDto.setStatutPaiement(d.getStatutPaiement());
                        Optional<Societe> societe = societeRepository.findById(d.getSociete().getId());
                        if(societe.isPresent()){
                            demandeAgrementListDto.setSociete(societe.get().getRaisonSociale());
                            demandeAgrementListDto.setNif(societe.get().getNif());
                        }

                        Optional<Agrement> agrement = agrementRepository.findById(d.getAgrement().getId());
                        if(agrement.isPresent()){
                            demandeAgrementListDto.setTypeAgrement(agrement.get().getLibelle());
                            demandeAgrementListDto.setMontant(agrement.get().getMontant());
                        }

                    demandeAgrementListDtos.add(demandeAgrementListDto);
            }
            );

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"),demandeAgrementListDtos), HttpStatus.OK);
        }
    }
}
