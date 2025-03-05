package com.acl.formaliteagricultureapi.serviceImpl.phytosanitaire.importation.cargaison;

import com.acl.formaliteagricultureapi.converter.PiecejointeConverter;
import com.acl.formaliteagricultureapi.converter.ProduitConverter;
import com.acl.formaliteagricultureapi.dto.imports.cargaison.PhytosanitaireCargaisonClientListDto;
import com.acl.formaliteagricultureapi.dto.imports.navire.PhytosanitaireNavireClientListDto;
import com.acl.formaliteagricultureapi.dto.piecejointe.PieceJointeDto;
import com.acl.formaliteagricultureapi.dto.produit.DetPhytosanitaireProduitDto;
import com.acl.formaliteagricultureapi.dto.produit.ProduitDto;
import com.acl.formaliteagricultureapi.dto.statistique.StatistiqueDemandeDto;
import com.acl.formaliteagricultureapi.models.DetPhytosanitaireProduit;
import com.acl.formaliteagricultureapi.models.PieceJointe;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.DetPhytosanitaireProduitRepository;
import com.acl.formaliteagricultureapi.repository.FormaliteRepository;
import com.acl.formaliteagricultureapi.repository.PieceJointeRepository;
import com.acl.formaliteagricultureapi.requete.PhytosanitaireClientInterface;
import com.acl.formaliteagricultureapi.services.phytosanitaire.importation.cargaison.InspectionPhtyoCargaisonManyService;
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
public class InspectionPhytoCargaisonClientManyServiceImpl implements InspectionPhtyoCargaisonManyService {

    private final Logger logger= LoggerFactory.getLogger(InspectionPhytoCargaisonClientManyServiceImpl.class);

    private final FormaliteRepository formaliteRepository;

    private final DetPhytosanitaireProduitRepository detPhytosanitaireProduitRepository;

     private final ProduitConverter produitConverter;

    private final PieceJointeRepository pieceJointeRepository;

    @Autowired
    PiecejointeConverter piecejointeConverter;

    @Autowired
    private Environment env;

    public InspectionPhytoCargaisonClientManyServiceImpl(FormaliteRepository formaliteRepository,
                                                         DetPhytosanitaireProduitRepository detPhytosanitaireProduitRepository, ProduitConverter produitConverter, PieceJointeRepository pieceJointeRepository) {
        this.formaliteRepository = formaliteRepository;
        this.detPhytosanitaireProduitRepository = detPhytosanitaireProduitRepository;
        this.produitConverter = produitConverter;
        this.pieceJointeRepository = pieceJointeRepository;
    }

    @Override
    public ResponseEntity<?> listAutorisationEnlevement(Etat etat, String ref) {
        //Liste enlevement Non soumise
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                getListDemande(etat,ref)), HttpStatus.OK);
    }

    private List<PhytosanitaireCargaisonClientListDto> getListDemande(Etat etat, String ref) {
        List<PhytosanitaireClientInterface> enelevementClientListInterfaces =
                formaliteRepository.findPhytosanitaireClientList(etat.getLabel(), ref);

        List<PhytosanitaireCargaisonClientListDto> phytosanitaireNavireClientListDtos = new ArrayList<>();

        //Parcours la liste De détail Produit
        //Crée le constructeur de la liste
        enelevementClientListInterfaces.forEach(data -> {
            List<DetPhytosanitaireProduitDto> detPhytosanitaireProduitDtos = new ArrayList<>();

            List<DetPhytosanitaireProduit> detail = detPhytosanitaireProduitRepository.findByPhytosanitaire(data.getIdPhytosanitaire());
            detail.forEach(produitDetail -> {

                ProduitDto produitDto = produitConverter.convertDtoToEntity(produitDetail.getProduit());
                DetPhytosanitaireProduitDto dtoProduitDetail = new DetPhytosanitaireProduitDto(produitDto, produitDetail.getQuantite()
                ,produitDetail.getConteneur(), produitDetail.getFournisseur(), produitDetail.getDescriptionEnvoi(),
                        produitDetail.getPaysEtLieuOrigin(), produitDetail.getNombreColis());

                detPhytosanitaireProduitDtos.add(dtoProduitDetail);
            });
            PhytosanitaireCargaisonClientListDto clientListDto = new PhytosanitaireCargaisonClientListDto();
            clientListDto.setIdFormalite(data.getIdFormalite());
            clientListDto.setAtp(data.getAtp());
            clientListDto.setImo(data.getImo());
            clientListDto.setNomDemandeur(data.getNomDemandeur());
            clientListDto.setEtat(data.getEtat());
            clientListDto.setNomNavire(data.getNomNavire());
            clientListDto.setChaine(data.getChaine());
            clientListDto.setNumGenerer(data.getNumGenerer());
            clientListDto.setTypeInspectionPhyto(data.getTypePhytosanitaire());
            clientListDto.setDateSoumission(data.getDateSoumission());
            clientListDto.setDateDemande(data.getDateDemande());
            clientListDto.setStructureDemandeur(data.getStructureDemandeur());
            clientListDto.setAffreteur(data.getAffreteur());
            clientListDto.setNif(data.getNif());
            clientListDto.setNumeroDossier(data.getNumeroDossier());
            clientListDto.setLieuInspection(data.getLieuInspection());
            clientListDto.setIdFormalite(data.getIdFormalite());
            clientListDto.setIdPhytosanitaire(data.getIdPhytosanitaire());
            clientListDto.setProfessionDemandeur(data.getProfessionDemandeur());
            clientListDto.setAdresseDemandeur(data.getAdresseDemandeur());
            clientListDto.setDateAccepte(data.getDateAccepte());
            clientListDto.setDetPhytosanitaireProduitDtos(detPhytosanitaireProduitDtos);
            clientListDto.setMontantRedevance(data.getMontantRedevance());
            clientListDto.setCompteClient(data.getCompteClient());
            clientListDto.setStatuPaiement(data.getStatutPaiement());
            clientListDto.setDateTraitement(data.getDateTraitement());
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

    private List<PhytosanitaireCargaisonClientListDto> getListDemandeByCompte(Etat etat, String ref, String compteClient) {
        List<PhytosanitaireClientInterface> enelevementClientListInterfaces =
                formaliteRepository.findPhytosanitaireClientByCompte(etat.getLabel(), ref, compteClient);

        List<PhytosanitaireCargaisonClientListDto> phytosanitaireNavireClientListDtos = new ArrayList<>();

        //Parcours la liste De détail Produit
        //Crée le constructeur de la liste
        enelevementClientListInterfaces.forEach(data -> {
            List<DetPhytosanitaireProduitDto> detPhytosanitaireProduitDtos = new ArrayList<>();

            List<DetPhytosanitaireProduit> detail = detPhytosanitaireProduitRepository.findByPhytosanitaire(data.getIdPhytosanitaire());
            detail.forEach(produitDetail -> {

                ProduitDto produitDto = produitConverter.convertDtoToEntity(produitDetail.getProduit());
                DetPhytosanitaireProduitDto dtoProduitDetail = new DetPhytosanitaireProduitDto(produitDto, produitDetail.getQuantite()
                        ,produitDetail.getConteneur(), produitDetail.getFournisseur(), produitDetail.getDescriptionEnvoi(),
                        produitDetail.getPaysEtLieuOrigin(), produitDetail.getNombreColis());

                detPhytosanitaireProduitDtos.add(dtoProduitDetail);
            });
           /* PhytosanitaireCargaisonClientListDto clientListDto = new PhytosanitaireCargaisonClientListDto(data.getIdFormalite(),
                    data.getAtp(), data.getNomNavire(),data.getImo(),data.getAffreteur(), data.getProfessionDemandeur(),data.getChaine(),data.getAdresseDemandeur(),
                    data.getNomDemandeur(), data.getEtat(), data.getNumGenerer(),data.getTypePhytosanitaire(), data.getDateSoumission(),
                    data.getDateDemande(), data.getDateAccepte(), data.getDateTraitement(), data.getDatePrevueInspection(),
                    data.getCompteClient(), data.getLieuInspection(), data.getDatePrevueInspection());*/

            PhytosanitaireCargaisonClientListDto clientListDto = new PhytosanitaireCargaisonClientListDto();
            clientListDto.setIdFormalite(data.getIdFormalite());
            clientListDto.setAtp(data.getAtp());
            clientListDto.setImo(data.getImo());
            clientListDto.setChaine(data.getChaine());
            clientListDto.setNomNavire(data.getNomNavire());
            clientListDto.setIdPhytosanitaire(data.getIdPhytosanitaire());
            clientListDto.setNomDemandeur(data.getNomDemandeur());
            clientListDto.setEtat(data.getEtat());
            clientListDto.setNumGenerer(data.getNumGenerer());
            clientListDto.setTypeInspectionPhyto(data.getTypePhytosanitaire());
            clientListDto.setDateSoumission(data.getDateSoumission());
            clientListDto.setDateDemande(data.getDateDemande());
            clientListDto.setNif(data.getNif());
            clientListDto.setNumeroDossier(data.getNumeroDossier());
            clientListDto.setAffreteur(data.getAffreteur());
            clientListDto.setStructureDemandeur(data.getStructureDemandeur());
            clientListDto.setDateAccepte(data.getDateAccepte());
            clientListDto.setDateTraitement(data.getDateTraitement());
            clientListDto.setDetPhytosanitaireProduitDtos(detPhytosanitaireProduitDtos);
            clientListDto.setMontantRedevance(data.getMontantRedevance());
            clientListDto.setCompteClient(data.getCompteClient());
            clientListDto.setProfessionDemandeur(data.getProfessionDemandeur());
//            clientListDto.setDatePrevueInspection(data.getDatePrevueInspection());
            clientListDto.setLieuInspection(data.getLieuInspection());
            clientListDto.setAdresseDemandeur(data.getAdresseDemandeur());
            clientListDto.setRefTypeInspectionPhyto(ref);
            clientListDto.setCompteClient(data.getCompteClient());
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
        List<PhytosanitaireCargaisonClientListDto> nonsoumis = getListDemande(Etat.NON_SOUMIS, env.getProperty("message.type.phytosanitaire.ref.cargaison"));
        List<PhytosanitaireCargaisonClientListDto> soumis = getListDemande(Etat.SOUMIS, env.getProperty("message.type.phytosanitaire.ref.cargaison"));
        List<PhytosanitaireCargaisonClientListDto> traiter = getListDemande(Etat.TRAITER, env.getProperty("message.type.phytosanitaire.ref.cargaison"));
        List<PhytosanitaireCargaisonClientListDto> accepter = getListDemande(Etat.ACCEPTER, env.getProperty("message.type.phytosanitaire.ref.cargaison"));
        List<PhytosanitaireCargaisonClientListDto> rejeter = getListDemande(Etat.REJETER, env.getProperty("message.type.phytosanitaire.ref.cargaison"));
        StatistiqueDemandeDto statistiqueDemandeDto = new StatistiqueDemandeDto(nonsoumis.size(),soumis.size(), traiter.size(),
                accepter.size(),rejeter.size());
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                statistiqueDemandeDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> listPhytocargaisonByCompte(Etat etat, String ref, String compteClient) {
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                getListDemandeByCompte(etat,ref, compteClient)), HttpStatus.OK);
    }
}
