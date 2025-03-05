package com.acl.formaliteenvironnementapi.serviceImpl.enlevement;

import com.acl.formaliteenvironnementapi.dto.autorisation.AutorisationEnvListDechetDto;
import com.acl.formaliteenvironnementapi.dto.enlevement.EnlevementListDechetDto;
import com.acl.formaliteenvironnementapi.dto.statistique.StatistiqueDemandeDto;
import com.acl.formaliteenvironnementapi.models.enumeration.Etat;
import com.acl.formaliteenvironnementapi.playload.ApiResponseModel;
import com.acl.formaliteenvironnementapi.repository.FormaliteRepository;
import com.acl.formaliteenvironnementapi.requete.AutorisationInterface;
import com.acl.formaliteenvironnementapi.services.enlevement.EnlevementDechetsManyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kol on 12/09/2024
 * @project formalite-environnement-api
 */
@Service
public class EnlevementDechetsManyServiceImpl implements EnlevementDechetsManyService {

    private final FormaliteRepository formaliteRepository;

    @Autowired
    private Environment env;

    public EnlevementDechetsManyServiceImpl(FormaliteRepository formaliteRepository) {
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

    private List<EnlevementListDechetDto> getListInspection(String etat , String ref) {

        List<EnlevementListDechetDto> listInspection = new ArrayList<>();

        List<AutorisationInterface> inspectionInterfaces = formaliteRepository.findInspection(etat,ref);

        inspectionInterfaces.forEach(cert -> {
            EnlevementListDechetDto data = new EnlevementListDechetDto();
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

    @Override
    public ResponseEntity<?> listStatDemande() {
        List<EnlevementListDechetDto> nonsoumis = getListInspection(Etat.NON_SOUMIS.getLabel(), env.getProperty("message.type.autorisation.ref.enlevement"));
        List<EnlevementListDechetDto> soumis = getListInspection(Etat.SOUMIS.getLabel(), env.getProperty("message.type.autorisation.ref.enlevement"));
        List<EnlevementListDechetDto> traiter = getListInspection(Etat.TRAITER.getLabel(), env.getProperty("message.type.autorisation.ref.enlevement"));
        List<EnlevementListDechetDto> accepter = getListInspection(Etat.ACCEPTER.getLabel(), env.getProperty("message.type.autorisation.ref.enlevement"));
        List<EnlevementListDechetDto> rejeter = getListInspection(Etat.REJETER.getLabel(), env.getProperty("message.type.autorisation.ref.enlevement"));
        StatistiqueDemandeDto statistiqueDemandeDto = new StatistiqueDemandeDto(nonsoumis.size(),soumis.size(), traiter.size(),
                accepter.size(),rejeter.size());
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                statistiqueDemandeDto), HttpStatus.OK);
    }
}
