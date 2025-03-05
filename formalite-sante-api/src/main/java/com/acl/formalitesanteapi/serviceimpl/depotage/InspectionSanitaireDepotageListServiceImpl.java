package com.acl.formalitesanteapi.serviceimpl.depotage;

import com.acl.formalitesanteapi.dto.inspection.depotage.InspectionSanitaireDepotageListDto;
import com.acl.formalitesanteapi.dto.inspection.navire.InspectionNavireListDto;
import com.acl.formalitesanteapi.dto.statistique.StatistiqueDemandeDto;
import com.acl.formalitesanteapi.models.enumeration.Etat;
import com.acl.formalitesanteapi.playload.ApiResponseModel;
import com.acl.formalitesanteapi.repository.FormaliteRepository;
import com.acl.formalitesanteapi.requette.InspectionInterfaces;
import com.acl.formalitesanteapi.services.inspection.depotage.InspectionSanitaireDepotageListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kol on 09/09/2024
 * @project formalite-sante-api
 */
@Service
public class InspectionSanitaireDepotageListServiceImpl implements InspectionSanitaireDepotageListService
{

    private final FormaliteRepository formaliteRepository;

    @Autowired
    private Environment env;

    public InspectionSanitaireDepotageListServiceImpl(FormaliteRepository formaliteRepository) {
        this.formaliteRepository = formaliteRepository;
    }



    @Override
    public ResponseEntity<?> listInspection(Etat etat, String ref) {
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                getListInspection(etat.getLabel(),ref)), HttpStatus.OK) ;
    }



    private List<InspectionSanitaireDepotageListDto> getListInspection(String etat , String ref) {

        List<InspectionSanitaireDepotageListDto> listInspection = new ArrayList<>();

        List<InspectionInterfaces> inspectionInterfaces = formaliteRepository.findInspection(etat,ref);

        inspectionInterfaces.forEach(cert -> {
            InspectionSanitaireDepotageListDto data = new InspectionSanitaireDepotageListDto();
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
            data.setDateAccepte(cert.getDateAcceptation());
            data.setDateSoumission(cert.getDateSoumission());
            data.setNumGenerer(cert.getNumGenerer());
            data.setMontantRedevance(cert.getMontantRedevance());

            listInspection.add(data);
        });

        return listInspection;

    }

    @Override
    public ResponseEntity<?> listStatDemande() {
        List<InspectionSanitaireDepotageListDto> nonsoumis = getListInspection(Etat.NON_SOUMIS.getLabel(), env.getProperty("message.type.inspection.ref.depotage"));
        List<InspectionSanitaireDepotageListDto> soumis = getListInspection(Etat.SOUMIS.getLabel(), env.getProperty("message.type.inspection.ref.depotage"));
        List<InspectionSanitaireDepotageListDto> traiter = getListInspection(Etat.TRAITER.getLabel(), env.getProperty("message.type.inspection.ref.depotage"));
        List<InspectionSanitaireDepotageListDto> accepter = getListInspection(Etat.ACCEPTER.getLabel(), env.getProperty("message.type.inspection.ref.depotage"));
        List<InspectionSanitaireDepotageListDto> rejeter = getListInspection(Etat.REJETER.getLabel(), env.getProperty("message.type.inspection.ref.depotage"));
        StatistiqueDemandeDto statistiqueDemandeDto = new StatistiqueDemandeDto(nonsoumis.size(),soumis.size(), traiter.size(),
                accepter.size(),rejeter.size());
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                statistiqueDemandeDto), HttpStatus.OK);
    }
}
