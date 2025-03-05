package com.acl.formalitesanteapi.serviceimpl.navire;

import com.acl.formalitesanteapi.dto.certificat.RenouvelementCertificatListDto;
import com.acl.formalitesanteapi.dto.inspection.navire.InspectionNavireListDto;
import com.acl.formalitesanteapi.dto.statistique.StatistiqueDemandeDto;
import com.acl.formalitesanteapi.models.enumeration.Etat;
import com.acl.formalitesanteapi.playload.ApiResponseModel;
import com.acl.formalitesanteapi.repository.FormaliteRepository;
import com.acl.formalitesanteapi.requette.CertificatInterfaces;
import com.acl.formalitesanteapi.requette.InspectionInterfaces;
import com.acl.formalitesanteapi.services.inspection.navire.InspectionNavireListService;
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
public class InspectionNavireListServiceImpl implements InspectionNavireListService {

    private final FormaliteRepository formaliteRepository;

    @Autowired
    private Environment env;

    public InspectionNavireListServiceImpl(FormaliteRepository formaliteRepository) {
        this.formaliteRepository = formaliteRepository;
    }


    @Override
    public ResponseEntity<?> listInspection(Etat etat, String ref) {
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                getListInspection(etat.getLabel(),ref)), HttpStatus.OK) ;
    }


    private List<InspectionNavireListDto> getListInspection(String etat , String ref) {

        List<InspectionNavireListDto> listInspection = new ArrayList<>();

        List<InspectionInterfaces> inspectionInterfaces = formaliteRepository.findInspection(etat,ref);

        inspectionInterfaces.forEach(cert -> {
            InspectionNavireListDto data = new InspectionNavireListDto();
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
            data.setNationalite(cert.getNationalite());
            data.setProvenance(cert.getProvenance());
            data.setDestination(cert.getDestination());
            data.setDateAccepte(cert.getDateAcceptation());
            data.setDateDeclaration(cert.getDateDeclaration());
            data.setCommandant(cert.getCommandant());
            data.setDateSoumission(cert.getDateSoumission());
            data.setNumGenerer(cert.getNumGenerer());
            data.setMontantRedevance(cert.getMontantRedevance());

            listInspection.add(data);
        });

        return listInspection;

    }

    @Override
    public ResponseEntity<?> listStatDemande() {

        List<InspectionNavireListDto> nonsoumis = getListInspection(Etat.NON_SOUMIS.getLabel(), env.getProperty("message.type.inspection.ref.navire"));
        List<InspectionNavireListDto> soumis = getListInspection(Etat.SOUMIS.getLabel(), env.getProperty("message.type.inspection.ref.navire"));
        List<InspectionNavireListDto> traiter = getListInspection(Etat.TRAITER.getLabel(), env.getProperty("message.type.inspection.ref.navire"));
        List<InspectionNavireListDto> accepter = getListInspection(Etat.ACCEPTER.getLabel(), env.getProperty("message.type.inspection.ref.navire"));
        List<InspectionNavireListDto> rejeter = getListInspection(Etat.REJETER.getLabel(), env.getProperty("message.type.inspection.ref.navire"));
        StatistiqueDemandeDto statistiqueDemandeDto = new StatistiqueDemandeDto(nonsoumis.size(),soumis.size(), traiter.size(),
                accepter.size(),rejeter.size());
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                statistiqueDemandeDto), HttpStatus.OK);

    }
}
