package com.acl.formaliteagricultureapi.serviceImpl.autorisation.demande.medicament;

import com.acl.formaliteagricultureapi.converter.PiecejointeConverter;
import com.acl.formaliteagricultureapi.converter.ProduitConverter;
import com.acl.formaliteagricultureapi.dto.imports.demande.medicament.AutorisationEnlevementMedicamentClientListDto;
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
import com.acl.formaliteagricultureapi.services.autorisation.demande.medicament.AutorisationEnvMedicamentClientManyService;
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
 * @author Zansouyé on 27/08/2024
 * @project formalite-agriculture-api
 */
@Service
public class AutorisationEnvMedicamentClientManyServiceImplem implements AutorisationEnvMedicamentClientManyService {


    private final Logger logger= LoggerFactory.getLogger(AutorisationEnvMedicamentClientManyServiceImplem.class);

    private final FormaliteRepository formaliteRepository;

    private final DetAutorisationProduitRepository detAutorisationProduitRepository;

    private final DemandeAutorisationAgrementRepository demandeAutorisationAgrementRepository;

    private final ProduitConverter produitConverter;

    private final PieceJointeRepository pieceJointeRepository;

    @Autowired
    PiecejointeConverter piecejointeConverter;

    @Autowired
    private Environment env;


    public AutorisationEnvMedicamentClientManyServiceImplem(FormaliteRepository formaliteRepository,
                                                            DetAutorisationProduitRepository detAutorisationProduitRepository, DemandeAutorisationAgrementRepository demandeAutorisationAgrementRepository, ProduitConverter produitConverter, PieceJointeRepository pieceJointeRepository){

        this.formaliteRepository = formaliteRepository;
        this.detAutorisationProduitRepository = detAutorisationProduitRepository;
        this.demandeAutorisationAgrementRepository = demandeAutorisationAgrementRepository;
        this.produitConverter = produitConverter;
        this.pieceJointeRepository = pieceJointeRepository;
    }

    @Override
    public ResponseEntity<?> listAutorisationEnlevement(Etat etat, String ref) {

   if(!getListDemande(etat,ref).isEmpty()) {
       return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
               getListDemande(etat, ref)), HttpStatus.OK);
   }else  {
       return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
               env.getProperty("message.list.vide"),
               getListDemande(etat, ref)), HttpStatus.OK);
   }

    }

    private List<AutorisationEnlevementMedicamentClientListDto> getListDemande(Etat etat, String ref) {
        List<AutorisationClientInterface> enelevementClientListInterfaces =
                formaliteRepository.findAutorisationClientList(etat.getLabel(), ref);

        List<DetAutorisationProduitDto> detAutorisationProduitDtoList = new ArrayList<>();

        List<AutorisationEnlevementMedicamentClientListDto> autorisationEnlevementClientListDtoList = new ArrayList<>();

        //Parcours la liste De détail Produit
        //Crée le constructeur de la liste
        enelevementClientListInterfaces.forEach(data -> {
            List<DetAutorisationProduit> detAutorisationProduits = detAutorisationProduitRepository.findByAutorisationId(data.getIdAutorisation());
            detAutorisationProduits.forEach(produitDetail -> {
                ProduitDto produitDto = produitConverter.convertDtoToEntity(produitDetail.getProduit());
                DetAutorisationProduitDto dtoProduitDetail = new DetAutorisationProduitDto(produitDto, produitDetail.getQuantite()
                        ,produitDetail.getProvenance(),produitDetail.getNombreCarton(),
                        produitDetail.getUnite(), produitDetail.getOrigine(), produitDetail.getPoidNet() );
                detAutorisationProduitDtoList.add(dtoProduitDetail);
            });
            AutorisationEnlevementMedicamentClientListDto clientListDto = new AutorisationEnlevementMedicamentClientListDto(data.getIdFormalite(),
                    data.getProvenance(), data.getDateEmbarquement(), data.getChaine(), data.getDateDemande()
                    , data.getEtat(), data.getTypeAutorisation(), data.getTypeReference(), data.getNumGenerer(), data.getDateRejet(),
                    data.getIdAutorisation(),
                    data.getDateAccepte(),data.getDateTraitement(),data.getDateSoumission(), data.getCompteClient(),
                    data.getMotifRejet(), data.getMontantRedevance(), detAutorisationProduitDtoList
            );
            clientListDto.setDateEmbarquement(data.getDateEmbarquement());
            if(data.getIdAgrement()!=null) {
                Optional<DmdAutorisationAgrement> agrement = demandeAutorisationAgrementRepository.findById(data.getIdAgrement());
                clientListDto.setNumeroAgrement(agrement.get().getNumero());
                clientListDto.setNomSociete(agrement.get().getSociete().getRaisonSociale());
            }
            clientListDto.setStatutPaiement(data.getStatutPaiement());
            clientListDto.setTypeRegime(data.getTypeRegime());
            clientListDto.setNumeroAgrementTransit(data.getNumeroAgrementTransit());
            // piece jointe
            List<PieceJointe> pieceJointeList = pieceJointeRepository.findByFormalite(data.getIdFormalite());
            List<PieceJointeDto> pieceJointeDtoList = new ArrayList<>();
            pieceJointeList.forEach(pj-> {
                PieceJointeDto pieceJointeDto = piecejointeConverter.convertDtoToEntity(pj);
                pieceJointeDtoList.add(pieceJointeDto);
            });
            clientListDto.setNif(data.getNif());
            clientListDto.setNumeroDossier(data.getNumeroDossier());
            clientListDto.setPieceJointeList(pieceJointeDtoList);
            autorisationEnlevementClientListDtoList.add(clientListDto);
        });
    return  autorisationEnlevementClientListDtoList;
    }

    @Override
    public ResponseEntity<?> listStatDemande() {
        List<AutorisationEnlevementMedicamentClientListDto> nonsoumis = getListDemande(Etat.NON_SOUMIS, env.getProperty("message.type.autorisation.ref.envmedicament"));
        List<AutorisationEnlevementMedicamentClientListDto> soumis = getListDemande(Etat.SOUMIS, env.getProperty("message.type.autorisation.ref.envmedicament"));
        List<AutorisationEnlevementMedicamentClientListDto> traiter = getListDemande(Etat.TRAITER, env.getProperty("message.type.autorisation.ref.envmedicament"));
        List<AutorisationEnlevementMedicamentClientListDto> accepter = getListDemande(Etat.ACCEPTER, env.getProperty("message.type.autorisation.ref.envmedicament"));
        List<AutorisationEnlevementMedicamentClientListDto> rejeter = getListDemande(Etat.REJETER, env.getProperty("message.type.autorisation.ref.envmedicament"));
        StatistiqueDemandeDto statistiqueDemandeDto = new StatistiqueDemandeDto(nonsoumis.size(),soumis.size(), traiter.size(),
                accepter.size(),rejeter.size());
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                statistiqueDemandeDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> listAutorisationEnvMedicamentByCompte(Etat etat, String ref, String compte) {
        if(!getListDemande(etat,ref).isEmpty()) {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                    getListDemandeByCompte(etat,ref, compte)), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.list.vide"),
                    getListDemandeByCompte(etat,ref,compte)), HttpStatus.OK);
        }
    }

    private List<AutorisationEnlevementMedicamentClientListDto> getListDemandeByCompte(Etat etat, String ref,
                                                                                String compteClient) {
        List<AutorisationClientInterface> autorisationClientInterfaces =
                formaliteRepository.findAutorisationClientListByCompte(etat.name(), ref,compteClient);

        List<AutorisationEnlevementMedicamentClientListDto> autorisationAnimauxVivantClientListDtos1 = new ArrayList<>();

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

            AutorisationEnlevementMedicamentClientListDto clientListDto = new AutorisationEnlevementMedicamentClientListDto(data.getIdAutorisation(), data.getIdFormalite(), data.getAtp(), data.getNomNavire(),
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

//            clientListDto.setDateRejet(data.getDateRejet());
            clientListDto.setMotifRejet(data.getMotifRejet());
            clientListDto.setNif(data.getNif());
            clientListDto.setNumeroDossier(data.getNumeroDossier());
            clientListDto.setPieceJointeList(pieceJointeDtoList);

            autorisationAnimauxVivantClientListDtos1.add(clientListDto);
        });
        return autorisationAnimauxVivantClientListDtos1;
    }

}
