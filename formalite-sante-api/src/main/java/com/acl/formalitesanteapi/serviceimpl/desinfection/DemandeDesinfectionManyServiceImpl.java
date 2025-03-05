package com.acl.formalitesanteapi.serviceimpl.desinfection;

import com.acl.formalitesanteapi.controller.inspectIon.InspectionDesinfectionControlleur;
import com.acl.formalitesanteapi.dto.inspection.desinfection.DemandeDesinfectionListDto;
import com.acl.formalitesanteapi.dto.inspection.navire.InspectionNavireListDto;
import com.acl.formalitesanteapi.dto.statistique.StatistiqueDemandeDto;
import com.acl.formalitesanteapi.models.enumeration.Etat;
import com.acl.formalitesanteapi.playload.ApiResponseModel;
import com.acl.formalitesanteapi.repository.FormaliteRepository;
import com.acl.formalitesanteapi.requette.InspectionInterfaces;
import com.acl.formalitesanteapi.services.inspection.desinfection.DemandeDesinfectionManyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kol on 11/09/2024
 * @project formalite-sante-api
 */
@Service
public class DemandeDesinfectionManyServiceImpl implements DemandeDesinfectionManyService {

    private final FormaliteRepository formaliteRepository;

    Logger logger = LoggerFactory.getLogger(DemandeDesinfectionManyServiceImpl.class);


    @Autowired
    private Environment env;

    public DemandeDesinfectionManyServiceImpl(FormaliteRepository formaliteRepository) {
        this.formaliteRepository = formaliteRepository;
    }

    @Override
    public ResponseEntity<?> listInspection(Etat etat, String ref) {

        if(!getListInspection(etat.getLabel(),ref).isEmpty()) {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                    getListInspection(etat.getLabel(),ref)), HttpStatus.OK) ;
        }else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.list.vide")),
                    HttpStatus.OK) ;
        }


    }

    private List<DemandeDesinfectionListDto> getListInspection(String etat , String ref) {

        List<DemandeDesinfectionListDto> listInspection = new ArrayList<>();

        List<InspectionInterfaces> inspectionInterfaces = formaliteRepository.findInspection(etat,ref);

        inspectionInterfaces.forEach(cert -> {
            logger.info("Infor {} " ,cert.getCargaison() );
            DemandeDesinfectionListDto data = new DemandeDesinfectionListDto();
            data.setAffreteur(cert.getAffreteur());
            data.setAtp(cert.getAtp());
            data.setIdInspection(cert.getIdInspection());
            data.setChaine(cert.getChaine());
            data.setEtat(cert.getEtat());
            data.setCompteClient(cert.getCompteClient());
            data.setImo(cert.getImo());
            data.setDateDemande(cert.getDateDemande());
            data.setIdFormalite(cert.getIdFormalite());
            data.setNomNavire(cert.getNomNavire());
            data.setCargaison(cert.getCargaison());
            data.setProvenance(cert.getProvenance());
            data.setNrt(cert.getNrt());
            data.setDateAccepte(cert.getDateAcceptation());
            data.setPavillon(cert.getPavillon());
            data.setReinspection(cert.getDateReinspection());
            data.setTonnage(cert.getTonnage());
            data.setOpDesinfection(cert.getOpDesinfection());
            data.setRemarque(cert.getRemarque());
            data.setOpDeratisation(cert.getOpDeratisation());
            data.setOpDesinsectisation(cert.getOpDesinsectisation());
            data.setDateSoumission(cert.getDateSoumission());
            data.setNumGenerer(cert.getNumGenerer());
            data.setMontantRedevance(cert.getMontantRedevance());

            listInspection.add(data);
        });

        return listInspection;

    }

    @Override
    public ResponseEntity<?> listStatDemande() {


        List<DemandeDesinfectionListDto> nonsoumis = getListInspection(Etat.NON_SOUMIS.getLabel(), env.getProperty("message.type.inspection.ref.desinfection"));
        List<DemandeDesinfectionListDto> soumis = getListInspection(Etat.SOUMIS.getLabel(), env.getProperty("message.type.inspection.ref.desinfection"));
        List<DemandeDesinfectionListDto> traiter = getListInspection(Etat.TRAITER.getLabel(), env.getProperty("message.type.inspection.ref.desinfection"));
        List<DemandeDesinfectionListDto> accepter = getListInspection(Etat.ACCEPTER.getLabel(), env.getProperty("message.type.inspection.ref.desinfection"));
        List<DemandeDesinfectionListDto> rejeter = getListInspection(Etat.REJETER.getLabel(), env.getProperty("message.type.inspection.ref.desinfection"));
        StatistiqueDemandeDto statistiqueDemandeDto = new StatistiqueDemandeDto(nonsoumis.size(),soumis.size(), traiter.size(),
                accepter.size(),rejeter.size());
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                statistiqueDemandeDto), HttpStatus.OK);
    }
}
