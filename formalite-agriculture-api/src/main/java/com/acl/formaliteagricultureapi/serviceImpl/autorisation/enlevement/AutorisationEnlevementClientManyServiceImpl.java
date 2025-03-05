package com.acl.formaliteagricultureapi.serviceImpl.autorisation.enlevement;

import com.acl.formaliteagricultureapi.converter.PiecejointeConverter;
import com.acl.formaliteagricultureapi.converter.ProduitConverter;
import com.acl.formaliteagricultureapi.dto.imports.demande.alimentAnimaux.AutorisationEnlevementClientListDto;
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
import com.acl.formaliteagricultureapi.services.autorisation.demande.alimentAnimaux.AutorisationEnlevementClientManyService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
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
public class AutorisationEnlevementClientManyServiceImpl implements AutorisationEnlevementClientManyService {

    private final Logger logger= LoggerFactory.getLogger(AutorisationEnlevementClientManyServiceImpl.class);

    private final FormaliteRepository formaliteRepository;

    private final DemandeAutorisationAgrementRepository demandeAutorisationAgrementRepository;

    private final DetAutorisationProduitRepository detAutorisationProduitRepository;

   private final ProduitConverter produitConverter;

    private final PieceJointeRepository pieceJointeRepository;

    @Autowired
    PiecejointeConverter piecejointeConverter;

    @Autowired
    private Environment env;

    public AutorisationEnlevementClientManyServiceImpl(FormaliteRepository formaliteRepository, DemandeAutorisationAgrementRepository demandeAutorisationAgrementRepository, DetAutorisationProduitRepository detAutorisationProduitRepository, ProduitConverter produitConverter, PieceJointeRepository pieceJointeRepository){

        this.formaliteRepository = formaliteRepository;
        this.demandeAutorisationAgrementRepository = demandeAutorisationAgrementRepository;
        this.detAutorisationProduitRepository = detAutorisationProduitRepository;
        this.produitConverter = produitConverter;
        this.pieceJointeRepository = pieceJointeRepository;
    }

    @Override
    public ResponseEntity<?> listAutorisationEnlevement(Etat etat, String ref) {

        if(!getListDemande(etat,ref).isEmpty()) {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"),
                    getListDemande(etat,ref)), HttpStatus.OK);
        }else  {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.list.vide"),
                    getListDemande(etat,ref)), HttpStatus.OK);
        }

    }

private List<AutorisationEnlevementClientListDto>   getListDemande(Etat etat, String ref) {
    //Liste enlevement Non soumise
    List<AutorisationClientInterface> enelevementClientListInterfaces =
            formaliteRepository.findAutorisationClientList(etat.getLabel(), ref);


    List<AutorisationEnlevementClientListDto> autorisationEnlevementClientListDtoList = new ArrayList<>();

    //Parcours la liste De détail Produit
    //Crée le constructeur de la liste
    enelevementClientListInterfaces.forEach(data -> {
        List<DetAutorisationProduitDto> detAutorisationProduitDtoList = new ArrayList<>();

        List<DetAutorisationProduit> detAutorisationProduits = detAutorisationProduitRepository.findByAutorisationId(data.getIdAutorisation());

        detAutorisationProduits.forEach(produitDetail -> {
            ProduitDto produitDto = produitConverter.convertDtoToEntity(produitDetail.getProduit());

            DetAutorisationProduitDto dtoProduitDetail = new DetAutorisationProduitDto(produitDto, produitDetail.getQuantite()
                    ,produitDetail.getProvenance(),produitDetail.getNombreCarton(),
                    produitDetail.getUnite(), produitDetail.getOrigine(), produitDetail.getPoidNet());

            detAutorisationProduitDtoList.add(dtoProduitDetail);
        });

        AutorisationEnlevementClientListDto clientListDto = new AutorisationEnlevementClientListDto(data.getIdFormalite(),
                data.getProvenance(), data.getDatearrivee(), data.getChaine(), data.getDateDemande()
                , data.getEtat(), data.getTypeAutorisation(), data.getTypeReference(), data.getNumGenerer(), data.getDateRejet(),
                data.getIdAutorisation(),
                data.getDateAccepte(),data.getDateTraitement(),data.getDateSoumission(), data.getCompteClient(),
                data.getMotifRejet(), data.getNomImportateur(), data.getMontantRedevance(), detAutorisationProduitDtoList
                );
         clientListDto.setDateEmbarquement(data.getDateEmbarquement());
        if(data.getIdAgrement()!=null) {
            Optional<DmdAutorisationAgrement> agrement = demandeAutorisationAgrementRepository.findById(data.getIdAgrement());
            clientListDto.setNumeroAgrement(agrement.get().getNumero());
            clientListDto.setNomSociete(agrement.get().getSociete().getRaisonSociale());
            clientListDto.setActivite(agrement.get().getActivite());
        }
        clientListDto.setDateRejet(data.getDateRejet());
        clientListDto.setMotifRejet(data.getMotifRejet());
        clientListDto.setTypeRegime(data.getTypeRegime());
        clientListDto.setNumeroAgrementTransit(data.getNumeroAgrementTransit());
        clientListDto.setStatutPaiement(data.getStatutPaiement());
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

        autorisationEnlevementClientListDtoList.add(clientListDto);

    });

    return autorisationEnlevementClientListDtoList;
}

    @Override
    public ResponseEntity<?> listStatDemande() {

        List<AutorisationEnlevementClientListDto> nonsoumis = getListDemande(Etat.NON_SOUMIS, env.getProperty("message.type.autorisation.ref.alimentAnimaux"));
        List<AutorisationEnlevementClientListDto> soumis = getListDemande(Etat.SOUMIS, env.getProperty("message.type.autorisation.ref.alimentAnimaux"));
        List<AutorisationEnlevementClientListDto> traiter = getListDemande(Etat.TRAITER, env.getProperty("message.type.autorisation.ref.alimentAnimaux"));
        List<AutorisationEnlevementClientListDto> accepter = getListDemande(Etat.ACCEPTER, env.getProperty("message.type.autorisation.ref.alimentAnimaux"));
        List<AutorisationEnlevementClientListDto> rejeter = getListDemande(Etat.REJETER, env.getProperty("message.type.autorisation.ref.alimentAnimaux"));
        StatistiqueDemandeDto statistiqueDemandeDto = new StatistiqueDemandeDto(nonsoumis.size(),soumis.size(), traiter.size(),
                accepter.size(),rejeter.size());
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                statistiqueDemandeDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> listAutorisationEnlevementByCompte(Etat etat, String ref, String compte) {
        if(!getListDemande(etat,ref).isEmpty()) {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                    getListDemandeByCompte(etat,ref, compte)), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.list.vide"),
                    getListDemandeByCompte(etat,ref,compte)), HttpStatus.OK);
        }
    }


    private List<AutorisationEnlevementClientListDto> getListDemandeByCompte(Etat etat, String ref,
                                                                                String compteClient) {
        List<AutorisationClientInterface> autorisationClientInterfaces =
                formaliteRepository.findAutorisationClientListByCompte(etat.name(), ref,compteClient);

        List<AutorisationEnlevementClientListDto> autorisationAnimauxVivantClientListDtos1 = new ArrayList<>();

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

            AutorisationEnlevementClientListDto clientListDto = new AutorisationEnlevementClientListDto(data.getIdAutorisation(), data.getIdFormalite(), data.getAtp(), data.getNomNavire(),
                    data.getImmo(), data.getAffreteur(), data.getConteneur(), data.getDatearrivee(), data.getChaine(), data.getDateDemande()
                    , data.getEtat(), data.getTypeAutorisation(), data.getTypeReference(), data.getNumGenerer(), data.getDateRejet(),
                    data.getDateAccepte(),data.getDateTraitement(),data.getDateSoumission(),
                    detAutorisationProduitDtoList, data.getCompteClient(), data.getNomImportateur(), data.getMontantRedevance(),
                    data.getMotifRejet());
            clientListDto.setStatutPaiement(data.getStatutPaiement());
            clientListDto.setProvenance(data.getProvenance());
            clientListDto.setDateEmbarquement(data.getDateEmbarquement());
//            clientListDto.setDatearrivee(data.getDatearrivee());
            if(data.getIdAgrement()!=null) {
                Optional<DmdAutorisationAgrement> agrement = demandeAutorisationAgrementRepository.findById(data.getIdAgrement());
                clientListDto.setNumeroAgrement(agrement.get().getNumero());
                clientListDto.setNomSociete(agrement.get().getSociete().getRaisonSociale());
            }

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

}
