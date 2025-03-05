package com.acl.formaliteenvironnementapi.serviceImpl.autorisation;

import com.acl.formaliteenvironnementapi.dto.autorisation.AutorisationEnvDechetDto;
import com.acl.formaliteenvironnementapi.dto.autorisation.AutorisationEnvListDechetDto;
import com.acl.formaliteenvironnementapi.dto.statistique.StatistiqueDemandeDto;
import com.acl.formaliteenvironnementapi.models.enumeration.Etat;
import com.acl.formaliteenvironnementapi.playload.ApiResponseModel;
import com.acl.formaliteenvironnementapi.repository.FormaliteRepository;
import com.acl.formaliteenvironnementapi.requete.AutorisationInterface;
import com.acl.formaliteenvironnementapi.services.autorisation.AutorisationEnvDechetManyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kol on 11/09/2024
 * @project formalite-environnement-api
 */
@Service
public class AutorisationEnvDechetManyServiceImpl implements AutorisationEnvDechetManyService {

    private final FormaliteRepository formaliteRepository;

    @Autowired
    private Environment env;

    public AutorisationEnvDechetManyServiceImpl(FormaliteRepository formaliteRepository) {
        this.formaliteRepository = formaliteRepository;
    }


    @Override
    public ResponseEntity<?> listAutorisation(Etat etat, String ref) {

        if(!getListInspection(etat.getLabel(),ref).isEmpty()) {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                    getListInspection(etat.getLabel(),ref)), HttpStatus.OK) ;
        }else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.list.vide"),
                    getListInspection(etat.getLabel(),ref)), HttpStatus.OK) ;
        }
    }

    @Override
    public ResponseEntity<?> listStatDemande() {
        List<AutorisationEnvListDechetDto> nonsoumis = getListInspection(Etat.NON_SOUMIS.getLabel(), env.getProperty("message.type.autorisation.ref.dechet"));
        List<AutorisationEnvListDechetDto> soumis = getListInspection(Etat.SOUMIS.getLabel(), env.getProperty("message.type.autorisation.ref.dechet"));
        List<AutorisationEnvListDechetDto> traiter = getListInspection(Etat.TRAITER.getLabel(), env.getProperty("message.type.autorisation.ref.dechet"));
        List<AutorisationEnvListDechetDto> accepter = getListInspection(Etat.ACCEPTER.getLabel(), env.getProperty("message.type.autorisation.ref.dechet"));
        List<AutorisationEnvListDechetDto> rejeter = getListInspection(Etat.REJETER.getLabel(), env.getProperty("message.type.autorisation.ref.dechet"));
        StatistiqueDemandeDto statistiqueDemandeDto = new StatistiqueDemandeDto(nonsoumis.size(),soumis.size(), traiter.size(),
                accepter.size(),rejeter.size());
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                statistiqueDemandeDto), HttpStatus.OK);
    }

    private List<AutorisationEnvListDechetDto> getListInspection(String etat , String ref) {

        List<AutorisationEnvListDechetDto> listInspection = new ArrayList<>();

        List<AutorisationInterface> inspectionInterfaces = formaliteRepository.findInspection(etat,ref);

        inspectionInterfaces.forEach(cert -> {
            AutorisationEnvListDechetDto data = new AutorisationEnvListDechetDto();
            data.setAffreteur(cert.getAffreteur());
            data.setAtp(cert.getAtp());
            data.setIdAutorisation(cert.getIdAutorisation());
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
}
