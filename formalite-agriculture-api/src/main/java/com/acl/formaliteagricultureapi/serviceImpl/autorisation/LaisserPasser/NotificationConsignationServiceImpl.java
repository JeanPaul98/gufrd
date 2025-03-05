package com.acl.formaliteagricultureapi.serviceImpl.autorisation.LaisserPasser;

import com.acl.formaliteagricultureapi.dto.autorisation.DetNotificationConsignationProduitDto;
import com.acl.formaliteagricultureapi.dto.autorisation.NotificationConsignationDto;
import com.acl.formaliteagricultureapi.dto.formalite.UpdateFormaliteDto;
import com.acl.formaliteagricultureapi.models.*;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.*;
import com.acl.formaliteagricultureapi.services.autorisation.laisserPasser.NotificationConsignationService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


/**
 * @author Zansouyé on 18/09/2024
 * @project formalite-agriculture-api
 */
@Service
public class NotificationConsignationServiceImpl implements NotificationConsignationService {

    private Logger logger= LoggerFactory.getLogger(NotificationConsignationServiceImpl.class);

    private final ProduitRepository produitRepository;

    private final NotificationConsignationReposiroty notificationConsignationReposiroty;

    private final DetNotificationConsignationProduitRepository detNotificationConsignationProduitRepository;

    private final InspecteurRepository inspecteurRepository;

    private final FormaliteRepository formaliteRepository;

    @Autowired
    private Environment env;
    public NotificationConsignationServiceImpl(ProduitRepository produitRepository,
                                               NotificationConsignationReposiroty
                                                       notificationConsignationReposiroty,
                                               DetNotificationConsignationProduitRepository
                                                       detNotificationConsignationProduitRepository,
                                               InspecteurRepository inspecteurRepository,
                                               FormaliteRepository formaliteRepository) {
        this.produitRepository = produitRepository;
        this.notificationConsignationReposiroty = notificationConsignationReposiroty;
        this.detNotificationConsignationProduitRepository = detNotificationConsignationProduitRepository;
        this.inspecteurRepository=inspecteurRepository;
        this.formaliteRepository=formaliteRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<?> createNotificationConsignation(NotificationConsignationDto
                                                                        notificationConsignationDto) {

        NotificationConsignation newNC= createNC(notificationConsignationDto.getDateConsignation(),
                notificationConsignationDto.getMotifConsignation(),
                notificationConsignationDto.getLieuConsignation(),
                notificationConsignationDto.getAnalyseDemande(),
                notificationConsignationDto.getNumeroAutorisation(),
                notificationConsignationDto.getMoyenTransport(),
                notificationConsignationDto.getReference(),
                notificationConsignationDto.getIdInspecteur(),
                notificationConsignationDto.getIdFormalite());


        return createDetNotificationConsignationProduit(notificationConsignationDto.
                getDetNotificationConsignationProduitDtoList(), newNC);

    }


    public NotificationConsignation createNC(Date dateConsignation, String motifConsignation,
                                             String lieuConsignation, String analyseDemande,
                                             String numeroAutorisation, String moyenTransport,
                                             String reference, Long idInspecteur, Long idFormalite){

        Optional<Inspecteur>optionalInspecteur= inspecteurRepository.
                findById(idInspecteur);

        Optional<Formalite>optionalFormalite= formaliteRepository.findById(idFormalite);

        if(optionalInspecteur.isPresent()&& optionalFormalite.isPresent()){
            NotificationConsignation notificationConsignation= new NotificationConsignation(
                    moyenTransport,reference,motifConsignation,lieuConsignation,analyseDemande,
                    numeroAutorisation,dateConsignation,optionalInspecteur.get(), optionalFormalite.get());

            NotificationConsignation saveNC= notificationConsignationReposiroty.
                    save(notificationConsignation);
            return saveNC;
        }else{
            throw new IllegalArgumentException(env.getProperty("message.notfound"));
        }
    }

    public ResponseEntity<?>createDetNotificationConsignationProduit(List<DetNotificationConsignationProduitDto>
                                                                             detNotificationConsignationProduitDtoList,
                                                                     NotificationConsignation nc ){

        List<DetNotificationConsignationProduit>detNotificationConsignationProduitList=new ArrayList<>();
        for(DetNotificationConsignationProduitDto parc: detNotificationConsignationProduitDtoList){
            Optional<Produit>optionalProduit= produitRepository.findById(parc.getIdProduit());
            if(optionalProduit.isPresent()){
                DetNotificationConsignationProduit detNotificationConsignationProduit=
                        new DetNotificationConsignationProduit(parc.getProvenance(), parc.getOrigine(),
                                parc.getDestination(), parc.getQuantite(), parc.getNombre(), parc.getUnite(),
                                optionalProduit.get(),nc);

                DetNotificationConsignationProduit save= detNotificationConsignationProduitRepository.
                        save(detNotificationConsignationProduit);

                detNotificationConsignationProduitList.add(save);

            }else{
                return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                        env.getProperty("message.notfound")), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                env.getProperty("message.succes"), detNotificationConsignationProduitList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<byte[]> generateLaisserPasserNotificationConsignation(UpdateFormaliteDto updateFormaliteDto) {
        return null;
    }
}
