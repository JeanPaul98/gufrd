package com.acl.formaliteagricultureapi.serviceImpl.autorisation.depotage;

import com.acl.formaliteagricultureapi.converter.PiecejointeConverter;
import com.acl.formaliteagricultureapi.converter.ProduitConverter;
import com.acl.formaliteagricultureapi.dto.imports.demande.medicament.AutorisationEnlevementMedicamentClientListDto;
import com.acl.formaliteagricultureapi.dto.imports.depotage.AutorisationDepotageTransitClientListDto;
import com.acl.formaliteagricultureapi.dto.piecejointe.PieceJointeDto;
import com.acl.formaliteagricultureapi.dto.produit.DetAutorisationProduitDto;
import com.acl.formaliteagricultureapi.dto.produit.ProduitDto;
import com.acl.formaliteagricultureapi.dto.statistique.StatistiqueDemandeDto;
import com.acl.formaliteagricultureapi.models.DetAutorisationProduit;
import com.acl.formaliteagricultureapi.models.PieceJointe;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.DetAutorisationProduitRepository;
import com.acl.formaliteagricultureapi.repository.FormaliteRepository;
import com.acl.formaliteagricultureapi.repository.PieceJointeRepository;
import com.acl.formaliteagricultureapi.requete.AutorisationClientInterface;
import com.acl.formaliteagricultureapi.serviceImpl.autorisation.enlevement.AutorisationEnlevementClientManyServiceImpl;
import com.acl.formaliteagricultureapi.services.autorisation.depotage.AutorisationDepotageTransitManyClientService;
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
 * @author kol on 20/08/2024
 * @project formalite-agriculture-api
 */
@Service
public class AutorisationDepotageTransitManyServiceImpl implements AutorisationDepotageTransitManyClientService {


    private final Logger logger= LoggerFactory.getLogger(AutorisationEnlevementClientManyServiceImpl.class);

    private final FormaliteRepository formaliteRepository;

    private final DetAutorisationProduitRepository detAutorisationProduitRepository;

    private final ProduitConverter produitConverter;

    private final PieceJointeRepository pieceJointeRepository;

    @Autowired
    private Environment env;

    @Autowired
    private PiecejointeConverter piecejointeConverter;

    public AutorisationDepotageTransitManyServiceImpl(FormaliteRepository formaliteRepository, DetAutorisationProduitRepository detAutorisationProduitRepository, ProduitConverter produitConverter, PieceJointeRepository pieceJointeRepository) {
        this.formaliteRepository = formaliteRepository;
        this.detAutorisationProduitRepository = detAutorisationProduitRepository;
        this.produitConverter = produitConverter;
        this.pieceJointeRepository = pieceJointeRepository;
    }


    @Override
    public ResponseEntity<?> listAutorisationDepotage(Etat etat, String ref) {

        if(!getListDemande(etat,ref).isEmpty()) {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                    getListDemande(etat,ref)), HttpStatus.OK);
        } else  {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.list.vide"),
                    getListDemande(etat,ref)), HttpStatus.OK);
        }


    }

    private List<AutorisationDepotageTransitClientListDto> getListDemande(Etat etat, String ref) {

        List<AutorisationClientInterface> depotageTransitListInterfaces =
                formaliteRepository.findAutorisationClientList(etat.name(), ref);


        List<AutorisationDepotageTransitClientListDto> depotageTransitClientListDtos = new ArrayList<>();

        //Parcours la liste De détail Produit
        //Crée le constructeur de la liste
        depotageTransitListInterfaces.forEach(data -> {

            List<DetAutorisationProduitDto> detAutorisationProduitDtoList = new ArrayList<>();

            List<DetAutorisationProduit> detAutorisationProduits = detAutorisationProduitRepository.findByAutorisationId(data.getIdAutorisation());
            detAutorisationProduits.forEach(produitDetail -> {

                ProduitDto produitDto = produitConverter.convertDtoToEntity(produitDetail.getProduit());
                DetAutorisationProduitDto dtoProduitDetail = new DetAutorisationProduitDto(produitDto, produitDetail.getQuantite()
                ,produitDetail.getProvenance(),produitDetail.getNombreCarton(),
                        produitDetail.getUnite(), produitDetail.getOrigine(), produitDetail.getPoidNet() );

                detAutorisationProduitDtoList.add(dtoProduitDetail);
            });

            AutorisationDepotageTransitClientListDto clientListDto = new AutorisationDepotageTransitClientListDto(data.getIdFormalite(), data.getAtp(), data.getNomNavire(),
                    data.getImmo(), data.getAffreteur(), data.getDatearrivee(), data.getChaine(), data.getDateDemande()
                    , data.getEtat(), data.getTypeAutorisation(), data.getTypeReference(), data.getNumGenerer(), data.getDateRejet(),
                    data.getDateAccepte(),data.getDateTraitement(),data.getDateSoumission(),
                    detAutorisationProduitDtoList);
            clientListDto.setMontantRedevance(data.getMontantRedevance());
            clientListDto.setIdAutorisation(data.getIdAutorisation());
            clientListDto.setMotifRejet(data.getMotifRejet());
            clientListDto.setStatutPaiement(data.getStatutPaiement());
            clientListDto.setNomImportateur(data.getNomImportateur());
            clientListDto.setCompteClient(data.getCompteClient());
            clientListDto.setNumeroBL(data.getNumeroBL());
            clientListDto.setNif(data.getNif());

            List<PieceJointe> pieceJointeList = pieceJointeRepository.findByFormalite(data.getIdFormalite());
            List<PieceJointeDto> pieceJointeDtoList = new ArrayList<>();
            pieceJointeList.forEach(pj-> {
                PieceJointeDto pieceJointeDto = piecejointeConverter.convertDtoToEntity(pj);
                pieceJointeDto.setTypePieceJointe(pj.getTypePieceJointe().getLibelle());
                pieceJointeDto.setNomOriginePieceJointe(pj.getNomOriginePieceJointe());
                pieceJointeDtoList.add(pieceJointeDto);
            });

            clientListDto.setNumeroDossier(data.getNumeroDossier());
            clientListDto.setNumeroAutorisation(data.getNumeroAutorisation());
            clientListDto.setPieceJointeList(pieceJointeDtoList);
            depotageTransitClientListDtos.add(clientListDto);
        });
        return depotageTransitClientListDtos;
    }

    @Override
    public ResponseEntity<?> listStatDemande() {
        List<AutorisationDepotageTransitClientListDto> nonsoumis = getListDemande(Etat.NON_SOUMIS, env.getProperty("message.type.autorisation.ref.depotage"));
        List<AutorisationDepotageTransitClientListDto> soumis = getListDemande(Etat.SOUMIS, env.getProperty("message.type.autorisation.ref.depotage"));
        List<AutorisationDepotageTransitClientListDto> traiter = getListDemande(Etat.TRAITER, env.getProperty("message.type.autorisation.ref.depotage"));
        List<AutorisationDepotageTransitClientListDto> accepter = getListDemande(Etat.ACCEPTER, env.getProperty("message.type.autorisation.ref.depotage"));
        List<AutorisationDepotageTransitClientListDto> rejeter = getListDemande(Etat.REJETER, env.getProperty("message.type.autorisation.ref.depotage"));
        StatistiqueDemandeDto statistiqueDemandeDto = new StatistiqueDemandeDto(nonsoumis.size(),soumis.size(), traiter.size(),
                accepter.size(),rejeter.size());
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                statistiqueDemandeDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> listAutorisatioByCompte(Etat etat, String ref, String compte) {
        if(!getListDemande(etat,ref).isEmpty()) {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                    getListDemandeByCompte(etat,ref, compte)), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.list.vide"),
                    getListDemandeByCompte(etat,ref,compte)), HttpStatus.OK);
        }
    }


    private List<AutorisationDepotageTransitClientListDto> getListDemandeByCompte(Etat etat, String ref,
                                                                                       String compteClient) {
        List<AutorisationClientInterface> autorisationClientInterfaces =
                formaliteRepository.findAutorisationClientListByCompte(etat.name(), ref,compteClient);

        List<AutorisationDepotageTransitClientListDto> autorisationAnimauxVivantClientListDtos1 = new ArrayList<>();

        //Parcours la liste De détail Produit
        //Crée le constructeur de la liste
        autorisationClientInterfaces.forEach(data -> {

            List<DetAutorisationProduitDto> detAutorisationProduitDtoList = new ArrayList<>();
            List<DetAutorisationProduit> detAutorisationProduits = detAutorisationProduitRepository.findByAutorisationId(data.getIdAutorisation());

            detAutorisationProduits.forEach(produitDetail -> {
                ProduitDto produitDto = produitConverter.convertDtoToEntity(produitDetail.getProduit());

                DetAutorisationProduitDto dtoProduitDetail = new DetAutorisationProduitDto(produitDto, produitDetail.getQuantite()
                        ,produitDetail.getProvenance(),produitDetail.getNombreCarton(),
                        produitDetail.getUnite(), produitDetail.getOrigine(), produitDetail.getPoidNet() );

                detAutorisationProduitDtoList.add(dtoProduitDetail);
            });

            AutorisationDepotageTransitClientListDto clientListDto = new AutorisationDepotageTransitClientListDto(data.getIdAutorisation(), data.getIdFormalite(), data.getAtp(), data.getNomNavire(),
                    data.getImmo(), data.getAffreteur(), data.getConteneur(), data.getDatearrivee(), data.getChaine(), data.getDateDemande()
                    , data.getEtat(), data.getTypeAutorisation(), data.getTypeReference(), data.getNumGenerer(), data.getDateRejet(),
                    data.getDateAccepte(),data.getDateTraitement(),data.getDateSoumission(),
                    detAutorisationProduitDtoList, data.getCompteClient(), data.getNomImportateur(), data.getMontantRedevance(),
                    data.getMotifRejet());

            clientListDto.setStatutPaiement(data.getStatutPaiement());
            clientListDto.setNumeroAutorisation(data.getNumeroAutorisation());
            clientListDto.setNumeroBL(data.getNumeroBL());
            clientListDto.setDatearrivee(data.getDatearrivee());
            // piece jointe
            List<PieceJointe> pieceJointeList = pieceJointeRepository.findByFormalite(data.getIdFormalite());
            List<PieceJointeDto> pieceJointeDtoList = new ArrayList<>();
            pieceJointeList.forEach(pj-> {
                PieceJointeDto pieceJointeDto = piecejointeConverter.convertDtoToEntity(pj);
                pieceJointeDto.setTypePieceJointe(pj.getTypePieceJointe().getLibelle());
                pieceJointeDto.setNomOriginePieceJointe(pj.getNomOriginePieceJointe());
                pieceJointeDtoList.add(pieceJointeDto);
            });
            clientListDto.setPieceJointeDtoList(pieceJointeDtoList);
            clientListDto.setNif(data.getNif());
            clientListDto.setNumeroDossier(data.getNumeroDossier());
            autorisationAnimauxVivantClientListDtos1.add(clientListDto);
        });
        return autorisationAnimauxVivantClientListDtos1;
    }


}
