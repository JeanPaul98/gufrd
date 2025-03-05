package com.acl.formaliteagricultureapi.serviceImpl.phytosanitaire.importation.navire;

import com.acl.formaliteagricultureapi.converter.PiecejointeConverter;
import com.acl.formaliteagricultureapi.dto.imports.navire.PhytosanitaireNavireClientListDto;
import com.acl.formaliteagricultureapi.dto.piecejointe.PieceJointeDto;
import com.acl.formaliteagricultureapi.dto.statistique.StatistiqueDemandeDto;
import com.acl.formaliteagricultureapi.models.PieceJointe;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.FormaliteRepository;
import com.acl.formaliteagricultureapi.repository.PieceJointeRepository;
import com.acl.formaliteagricultureapi.requete.PhytosanitaireClientInterface;
import com.acl.formaliteagricultureapi.services.phytosanitaire.importation.navire.InspectionPhtyoNavireManyService;
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
 * @author Zansouyé on 20/08/2024
 * @project formalite-agriculture-api
 */
@Service
public class InspectionPhytoNavireClientManyServiceImpl implements InspectionPhtyoNavireManyService {

    private final Logger logger= LoggerFactory.getLogger(InspectionPhytoNavireClientManyServiceImpl.class);

    private final FormaliteRepository formaliteRepository;

    private final PieceJointeRepository pieceJointeRepository;

    @Autowired
    PiecejointeConverter piecejointeConverter;

    @Autowired
    private Environment env;

    public InspectionPhytoNavireClientManyServiceImpl(FormaliteRepository formaliteRepository, PieceJointeRepository pieceJointeRepository) {
        this.formaliteRepository = formaliteRepository;
        this.pieceJointeRepository = pieceJointeRepository;
    }


    @Override
    public ResponseEntity<?> listAutorisationEnlevement(Etat etat, String ref) {

        //Liste enlevement Non soumise interface
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                getListDemande(etat,ref)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> listPhytosanitaireByCompte(Etat etat, String ref, String compte) {

        //Liste enlevement Non soumise interface
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                getListDemandeByCompteClient(etat,ref,compte)), HttpStatus.OK);
    }

private List<PhytosanitaireNavireClientListDto>  getListDemande(Etat etat, String ref) {

    List<PhytosanitaireClientInterface> enelevementClientListInterfaces =
            formaliteRepository.findPhytosanitaireClientList(etat.getLabel(), ref);


    List<PhytosanitaireNavireClientListDto> phytosanitaireNavireClientListDtos = new ArrayList<>();

    //Parcours la liste De détail Produit
    //Crée le constructeur de la liste
    enelevementClientListInterfaces.forEach(data -> {
        PhytosanitaireNavireClientListDto clientListDto = new PhytosanitaireNavireClientListDto(data.getIdFormalite(),
                data.getIdPhytosanitaire(),data.getChaine(), data.getNomNavire(), data.getProfessionDemandeur(),
                data.getAdresseDemandeur(), data.getLieuInspection(), data.getAtp(), data.getDatePrevueInspection(),
                data.getImo(), data.getNomDemandeur(), data.getEtat(), data.getNumGenerer(), data.getTypePhytosanitaire(),
                data.getDateSoumission(), data.getDateDemande(), data.getAffreteur(),data.getDateAccepte(),
                data.getCompteClient(),data.getDateRejet(), data.getMontantRedevance(), data.getMotifRejet(), data.getDateTraitement());
        clientListDto.setStructureDemandeur(data.getStructureDemandeur());
        clientListDto.setTypeCargaison(data.getTypeCargaison());
        phytosanitaireNavireClientListDtos.add(clientListDto);
    });

    return phytosanitaireNavireClientListDtos;
}

    private List<PhytosanitaireNavireClientListDto>  getListDemandeByCompteClient(Etat etat, String ref, String compteClient) {

        List<PhytosanitaireClientInterface> enelevementClientListInterfaces =
                formaliteRepository.findPhytosanitaireClientByCompte(etat.getLabel(), ref, compteClient);


        List<PhytosanitaireNavireClientListDto> phytosanitaireNavireClientListDtos = new ArrayList<>();

        //Parcours la liste De détail Produit
        //Crée le constructeur de la liste
        enelevementClientListInterfaces.forEach(data -> {
            PhytosanitaireNavireClientListDto clientListDto = new PhytosanitaireNavireClientListDto(data.getIdFormalite(),
                    data.getIdPhytosanitaire(),data.getChaine(), data.getNomNavire(), data.getProfessionDemandeur(),
                    data.getAdresseDemandeur(), data.getLieuInspection(), data.getAtp(), data.getDatePrevueInspection(),
                    data.getImo(), data.getNomDemandeur(), data.getEtat(), data.getNumGenerer(), data.getTypePhytosanitaire(),
                    data.getDateSoumission(), data.getDateDemande(), data.getAffreteur(),data.getDateAccepte(),
                    data.getCompteClient(),data.getDateRejet(), data.getMontantRedevance(), data.getMotifRejet(), data.getDateTraitement());
            clientListDto.setStructureDemandeur(data.getStructureDemandeur());
            clientListDto.setTypeCargaison(data.getTypeCargaison());
            clientListDto.setNumeroDossier(data.getNumeroDossier());
            clientListDto.setNif(data.getNif());
            List<PieceJointe> pieceJointeList = pieceJointeRepository.findByFormalite(data.getIdFormalite());
            List<PieceJointeDto> pieceJointeDtoList = new ArrayList<>();
            pieceJointeList.forEach(pj-> {
                PieceJointeDto pieceJointeDto = piecejointeConverter.convertDtoToEntity(pj);
                pieceJointeDtoList.add(pieceJointeDto);
            });
            clientListDto.setPieceJointeList(pieceJointeDtoList);

            phytosanitaireNavireClientListDtos.add(clientListDto);
        });

        return phytosanitaireNavireClientListDtos;
    }


    @Override
    public ResponseEntity<?> listStatDemande() {
        List<PhytosanitaireNavireClientListDto> nonsoumis = getListDemande(Etat.NON_SOUMIS, env.getProperty("message.type.phytosanitaire.ref.navire"));
        List<PhytosanitaireNavireClientListDto> soumis = getListDemande(Etat.SOUMIS, env.getProperty("message.type.phytosanitaire.ref.navire"));
        List<PhytosanitaireNavireClientListDto> traiter = getListDemande(Etat.TRAITER, env.getProperty("message.type.phytosanitaire.ref.navire"));
        List<PhytosanitaireNavireClientListDto> accepter = getListDemande(Etat.ACCEPTER, env.getProperty("message.type.phytosanitaire.ref.navire"));
        List<PhytosanitaireNavireClientListDto> rejeter = getListDemande(Etat.REJETER, env.getProperty("message.type.phytosanitaire.ref.navire"));
        StatistiqueDemandeDto statistiqueDemandeDto = new StatistiqueDemandeDto(nonsoumis.size(),soumis.size(), traiter.size(),
                accepter.size(),rejeter.size());
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                statistiqueDemandeDto), HttpStatus.OK);
    }
}
