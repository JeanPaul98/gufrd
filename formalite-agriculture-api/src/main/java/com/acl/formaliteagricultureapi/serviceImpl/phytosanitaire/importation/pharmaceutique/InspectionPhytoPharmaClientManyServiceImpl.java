package com.acl.formaliteagricultureapi.serviceImpl.phytosanitaire.importation.pharmaceutique;

import com.acl.formaliteagricultureapi.converter.PiecejointeConverter;
import com.acl.formaliteagricultureapi.converter.ProduitConverter;
import com.acl.formaliteagricultureapi.dto.imports.navire.PhytosanitaireNavireClientListDto;
import com.acl.formaliteagricultureapi.dto.imports.phytopharmaceutique.PhytosanitaireProduitPhytopharmaClientListDto;
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
import com.acl.formaliteagricultureapi.services.phytosanitaire.importation.pharmaceutique.InspectionPhtyoPharmaManyService;
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
public class InspectionPhytoPharmaClientManyServiceImpl implements InspectionPhtyoPharmaManyService {

    private final Logger logger= LoggerFactory.getLogger(InspectionPhytoPharmaClientManyServiceImpl.class);

    private final FormaliteRepository formaliteRepository;

    private final DetPhytosanitaireProduitRepository detPhytosanitaireProduitRepository;

     private final ProduitConverter produitConverter;

    private final PieceJointeRepository pieceJointeRepository;

    @Autowired
    PiecejointeConverter piecejointeConverter;

    @Autowired
    private Environment env;

    public InspectionPhytoPharmaClientManyServiceImpl(FormaliteRepository formaliteRepository,
                                                      DetPhytosanitaireProduitRepository detPhytosanitaireProduitRepository, ProduitConverter produitConverter, PieceJointeRepository pieceJointeRepository) {
        this.formaliteRepository = formaliteRepository;
        this.detPhytosanitaireProduitRepository = detPhytosanitaireProduitRepository;
        this.produitConverter = produitConverter;
        this.pieceJointeRepository = pieceJointeRepository;
    }

    @Override
    public ResponseEntity<?> listAutorisationEnlevement(Etat etat, String ref) {
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                getListDemande(etat, ref)), HttpStatus.OK);
    }

    private List<PhytosanitaireProduitPhytopharmaClientListDto> getListDemande(Etat etat, String ref) {
        //Liste enlevement Non soumise
        List<PhytosanitaireClientInterface> enelevementClientListInterfaces =
                formaliteRepository.findPhytosanitaireClientList(etat.getLabel(), ref);



        List<PhytosanitaireProduitPhytopharmaClientListDto> phytosanitaireNavireClientListDtos = new ArrayList<>();

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
            PhytosanitaireProduitPhytopharmaClientListDto clientListDto = new PhytosanitaireProduitPhytopharmaClientListDto();
            clientListDto.setIdFormalite(data.getIdFormalite());
            clientListDto.setAtp(data.getAtp());
            clientListDto.setImo(data.getImo());
            clientListDto.setChaine(data.getChaine());
            clientListDto.setNif(data.getNif());
            clientListDto.setNomNavire(data.getNomNavire());
            clientListDto.setIdPhytosanitaire(data.getIdPhytosanitaire());
            clientListDto.setNomDemandeur(data.getNomDemandeur());
            clientListDto.setEtat(data.getEtat());
            clientListDto.setStructureDemandeur(data.getStructureDemandeur());
            clientListDto.setNumGenerer(data.getNumGenerer());
            clientListDto.setNumeroDossier(data.getNumeroDossier());
            clientListDto.setTypeInspectionPhyto(data.getTypePhytosanitaire());
            clientListDto.setDateSoumission(data.getDateSoumission());
            clientListDto.setDateDemande(data.getDateDemande());
            clientListDto.setAffreteur(data.getAffreteur());
            clientListDto.setDateAccepte(data.getDateAccepte());
            clientListDto.setDateTraitement(data.getDateTraitement());
            clientListDto.setDetPhytosanitaireProduitDtos(detPhytosanitaireProduitDtos);
            clientListDto.setMontantRedevance(data.getMontantRedevance());
            clientListDto.setCompteClient(data.getCompteClient());
            clientListDto.setProfessionDemandeur(data.getProfessionDemandeur());

            List<PieceJointe> pieceJointeList = pieceJointeRepository.findByFormalite(data.getIdFormalite());
            List<PieceJointeDto> pieceJointeDtoList = new ArrayList<>();
            pieceJointeList.forEach(pj-> {
                PieceJointeDto pieceJointeDto = piecejointeConverter.convertDtoToEntity(pj);
                pieceJointeDtoList.add(pieceJointeDto);
            });
            clientListDto.setPieceJointeList(pieceJointeDtoList);

//            clientListDto.setDatePrevueInspection(data.getDatePrevueInspection());
            clientListDto.setLieuInspection(data.getLieuInspection());
            clientListDto.setAdresseDemandeur(data.getAdresseDemandeur());
            clientListDto.setRefTypeInspectionPhyto(ref);
            phytosanitaireNavireClientListDtos.add(clientListDto);
        });
        return phytosanitaireNavireClientListDtos;
    }

    @Override
    public ResponseEntity<?> listStatDemande() {
        List<PhytosanitaireProduitPhytopharmaClientListDto> nonsoumis = getListDemande(Etat.NON_SOUMIS, env.getProperty("message.type.phytosanitaire.ref.pharma"));
        List<PhytosanitaireProduitPhytopharmaClientListDto> soumis = getListDemande(Etat.SOUMIS, env.getProperty("message.type.phytosanitaire.ref.pharma"));
        List<PhytosanitaireProduitPhytopharmaClientListDto> traiter = getListDemande(Etat.TRAITER, env.getProperty("message.type.phytosanitaire.ref.pharma"));
        List<PhytosanitaireProduitPhytopharmaClientListDto> accepter = getListDemande(Etat.ACCEPTER, env.getProperty("message.type.phytosanitaire.ref.pharma"));
        List<PhytosanitaireProduitPhytopharmaClientListDto> rejeter = getListDemande(Etat.REJETER, env.getProperty("message.type.phytosanitaire.ref.pharma"));
        StatistiqueDemandeDto statistiqueDemandeDto = new StatistiqueDemandeDto(nonsoumis.size(),soumis.size(), traiter.size(),
                accepter.size(),rejeter.size());
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                statistiqueDemandeDto), HttpStatus.OK);
    }
}
