package com.acl.formaliteagricultureapi.serviceImpl.autorisation.demande.animauxVivant;

import com.acl.formaliteagricultureapi.converter.PiecejointeConverter;
import com.acl.formaliteagricultureapi.converter.ProduitConverter;
import com.acl.formaliteagricultureapi.dto.imports.demande.animauxVivant.AutorisationAnimauxVivantClientListDto;
import com.acl.formaliteagricultureapi.dto.piecejointe.PieceJointeDto;
import com.acl.formaliteagricultureapi.dto.produit.DetAutorisationProduitDto;
import com.acl.formaliteagricultureapi.dto.produit.ProduitDto;
import com.acl.formaliteagricultureapi.dto.statistique.StatistiqueDemandeDto;
import com.acl.formaliteagricultureapi.models.DetAutorisationProduit;
import com.acl.formaliteagricultureapi.models.DmdAutorisationAgrement;
import com.acl.formaliteagricultureapi.models.PieceJointe;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.DemandeAutorisationAgrementRepository;
import com.acl.formaliteagricultureapi.repository.DetAutorisationProduitRepository;
import com.acl.formaliteagricultureapi.repository.FormaliteRepository;
import com.acl.formaliteagricultureapi.repository.PieceJointeRepository;
import com.acl.formaliteagricultureapi.requete.AutorisationClientInterface;
import com.acl.formaliteagricultureapi.services.autorisation.demande.animauxVivant.AutorisationAnimauxVivantClientManyService;
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
 * @author Zansouyé on 20/08/2024
 * @project formalite-agriculture-api
 */
@Service
public class AutorisationAnimauxVivantClientManyServiceImpl implements AutorisationAnimauxVivantClientManyService {

    private final Logger logger= LoggerFactory.getLogger(AutorisationAnimauxVivantClientManyServiceImpl.class);

    private final FormaliteRepository formaliteRepository;

    private final DetAutorisationProduitRepository detAutorisationProduitRepository;

    private final PieceJointeRepository pieceJointeRepository;

    private final ProduitConverter produitConverter;

    private final DemandeAutorisationAgrementRepository demandeAutorisationAgrementRepository;

    @Autowired
    private Environment env;

    @Autowired
    PiecejointeConverter piecejointeConverter;


    public AutorisationAnimauxVivantClientManyServiceImpl(FormaliteRepository formaliteRepository, DetAutorisationProduitRepository detAutorisationProduitRepository, PieceJointeRepository pieceJointeRepository, ProduitConverter produitConverter, DemandeAutorisationAgrementRepository demandeAutorisationAgrementRepository){

        this.formaliteRepository = formaliteRepository;
        this.detAutorisationProduitRepository = detAutorisationProduitRepository;
        this.pieceJointeRepository = pieceJointeRepository;
        this.produitConverter = produitConverter;
        this.demandeAutorisationAgrementRepository = demandeAutorisationAgrementRepository;
    }

    @Override
    public ResponseEntity<?> listAutorisationAnimaux(Etat etat, String ref) {

        if(!getListDemande(etat,ref).isEmpty()) {

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                    getListDemande(etat,ref)), HttpStatus.OK);
        }else {

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.list.vide"),
                    getListDemande(etat,ref)), HttpStatus.OK);
        }

    }

    private List<AutorisationAnimauxVivantClientListDto> getListDemande(Etat etat, String ref) {
        List<AutorisationClientInterface> autorisationClientInterfaces =
                formaliteRepository.findAutorisationClientList(etat.name(), ref);

        List<AutorisationAnimauxVivantClientListDto> autorisationAnimauxVivantClientListDtos1 = new ArrayList<>();

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

            //Liste de piece jointe

        //fin piecejointe
            AutorisationAnimauxVivantClientListDto clientListDto = new AutorisationAnimauxVivantClientListDto(data.getIdAutorisation(), data.getIdFormalite(), data.getAtp(), data.getNomNavire(),
                    data.getImmo(), data.getAffreteur(), data.getConteneur(), data.getDatearrivee(), data.getChaine(), data.getDateDemande()
                    , data.getEtat(), data.getTypeAutorisation(), data.getTypeReference(), data.getNumGenerer(), data.getDateRejet(),
                    data.getDateAccepte(),data.getDateTraitement(),data.getDateSoumission(),
                    detAutorisationProduitDtoList, data.getCompteClient(), data.getNomImportateur(), data.getMontantRedevance(),
                    data.getMotifRejet());
            clientListDto.setStatutPaiement(data.getStatutPaiement());
            clientListDto.setProvenance(data.getProvenance());
            clientListDto.setDateEmbarquement(data.getDateEmbarquement());
            clientListDto.setDatearrivee(data.getDatearrivee());
            if(data.getIdAgrement()!=null) {
                Optional<DmdAutorisationAgrement> agrement = demandeAutorisationAgrementRepository.findById(data.getIdAgrement());
                clientListDto.setNumeroAgrement(agrement.get().getNumero());
                clientListDto.setNomSociete(agrement.get().getSociete().getRaisonSociale());
            }
//            clientListDto.setDateRejet(data.getDateRejet());
            clientListDto.setMotifRejet(data.getMotifRejet());
            clientListDto.setTypeRegime(data.getTypeRegime());
            clientListDto.setNumeroAgrementTransit(data.getNumeroAgrementTransit());
            clientListDto.setStatutDemande(data.getStatutDemande());

            // piece jointe
            List<PieceJointe> pieceJointeList = pieceJointeRepository.findByFormalite(data.getIdFormalite());
            List<PieceJointeDto> pieceJointeDtoList = new ArrayList<>();
            pieceJointeList.forEach(pj-> {
                PieceJointeDto pieceJointeDto = piecejointeConverter.convertDtoToEntity(pj);
                pieceJointeDto.setTypePieceJointe(pj.getTypePieceJointe().getLibelle());
                pieceJointeDto.setNomOriginePieceJointe(pj.getNomOriginePieceJointe());
                pieceJointeDtoList.add(pieceJointeDto);
            });
            clientListDto.setNif(data.getNif());
            clientListDto.setNumeroDossier(data.getNumeroDossier());
            clientListDto.setPieceJointeList(pieceJointeDtoList);

            autorisationAnimauxVivantClientListDtos1.add(clientListDto);
        });
        return autorisationAnimauxVivantClientListDtos1;
    }

    @Override
    public ResponseEntity<?> listStatDemande() {
        List<AutorisationAnimauxVivantClientListDto> nonsoumis = getListDemande(Etat.NON_SOUMIS, env.getProperty("message.type.autorisation.ref.animauxVivant"));
        List<AutorisationAnimauxVivantClientListDto> soumis = getListDemande(Etat.SOUMIS, env.getProperty("message.type.autorisation.ref.animauxVivant"));
        List<AutorisationAnimauxVivantClientListDto> traiter = getListDemande(Etat.TRAITER, env.getProperty("message.type.autorisation.ref.animauxVivant"));
        List<AutorisationAnimauxVivantClientListDto> accepter = getListDemande(Etat.ACCEPTER, env.getProperty("message.type.autorisation.ref.animauxVivant"));
        List<AutorisationAnimauxVivantClientListDto> rejeter = getListDemande(Etat.REJETER, env.getProperty("message.type.autorisation.ref.animauxVivant"));
        StatistiqueDemandeDto statistiqueDemandeDto = new StatistiqueDemandeDto(nonsoumis.size(),soumis.size(), traiter.size(),
                accepter.size(),rejeter.size());
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                statistiqueDemandeDto), HttpStatus.OK);
    }

    private List<AutorisationAnimauxVivantClientListDto> getListDemandeByCompte(Etat etat, String ref,String compteClient) {
        List<AutorisationClientInterface> autorisationClientInterfaces =
                formaliteRepository.findAutorisationClientListByCompte(etat.name(), ref,compteClient);

        List<AutorisationAnimauxVivantClientListDto> autorisationAnimauxVivantClientListDtos1 = new ArrayList<>();

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

            AutorisationAnimauxVivantClientListDto clientListDto = new AutorisationAnimauxVivantClientListDto(
                    data.getIdAutorisation(), data.getIdFormalite(), data.getAtp(), data.getNomNavire(),
                    data.getImmo(), data.getAffreteur(), data.getConteneur(), data.getDatearrivee(), data.getChaine(),
                    data.getDateDemande(),data.getEtat(), data.getTypeAutorisation(), data.getTypeReference(),
                    data.getNumGenerer(), data.getDateRejet(),data.getDateAccepte(),data.getDateTraitement(),
                    data.getDateSoumission(),detAutorisationProduitDtoList, data.getCompteClient(),data.getNomImportateur(),
                    data.getMontantRedevance(),data.getMotifRejet()
            );
            clientListDto.setStatutPaiement(data.getStatutPaiement());
            clientListDto.setProvenance(data.getProvenance());
            clientListDto.setDateEmbarquement(data.getDateEmbarquement());
            clientListDto.setDatearrivee(data.getDatearrivee());

            if(data.getIdAgrement()!=null) {
                Optional<DmdAutorisationAgrement> agrement = demandeAutorisationAgrementRepository.findById(data.getIdAgrement());
                clientListDto.setNumeroAgrement(agrement.get().getNumero());
                clientListDto.setNomSociete(agrement.get().getSociete().getRaisonSociale());
            }

            clientListDto.setTypeRegime(data.getTypeRegime());
            clientListDto.setNumeroAgrementTransit(data.getNumeroAgrementTransit());
            clientListDto.setStatutDemande(data.getStatutDemande());

            List<PieceJointe> pieceJointeList = pieceJointeRepository.findByFormalite(data.getIdFormalite());
            List<PieceJointeDto> pieceJointeDtoList = new ArrayList<>();
            pieceJointeList.forEach(pj-> {
                PieceJointeDto pieceJointeDto = piecejointeConverter.convertDtoToEntity(pj);
                pieceJointeDtoList.add(pieceJointeDto);
            });
            clientListDto.setNif(data.getNif());
            clientListDto.setNumeroDossier(data.getNumeroDossier());
            clientListDto.setPieceJointeList(pieceJointeDtoList);


            autorisationAnimauxVivantClientListDtos1.add(clientListDto);
        });
        return autorisationAnimauxVivantClientListDtos1;
    }


    @Override
    public ResponseEntity<?> listAutorisationAnimauxByCompte(Etat etat, String ref, String compte) {
        if(!getListDemande(etat,ref).isEmpty()) {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                    getListDemandeByCompte(etat,ref, compte)), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.list.vide"),
                    getListDemandeByCompte(etat,ref,compte)), HttpStatus.OK);
        }
    }
}
