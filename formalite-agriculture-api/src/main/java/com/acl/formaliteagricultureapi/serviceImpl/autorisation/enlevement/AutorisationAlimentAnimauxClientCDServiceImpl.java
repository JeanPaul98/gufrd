package com.acl.formaliteagricultureapi.serviceImpl.autorisation.enlevement;

import com.acl.formaliteagricultureapi.dto.autorisation.demande.AutorisationImportationDto;
import com.acl.formaliteagricultureapi.dto.piecejointe.PieceJointeFormaliteDto;
import com.acl.formaliteagricultureapi.dto.produit.DetAutorisationProduitDto;
import com.acl.formaliteagricultureapi.models.*;
import com.acl.formaliteagricultureapi.models.enumeration.Chaine;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.models.enumeration.StatutPaiement;
import com.acl.formaliteagricultureapi.models.enumeration.TypeRegime;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.*;
import com.acl.formaliteagricultureapi.services.autorisation.demande.alimentAnimaux.AutorisationAlimentAnimauxClientCDService;
import com.acl.formaliteagricultureapi.services.utils.UtilServices;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Zansouyé on 19/08/2024
 * @project formalite-agriculture-api
 */
@Service
public class AutorisationAlimentAnimauxClientCDServiceImpl implements AutorisationAlimentAnimauxClientCDService {

    private final Logger logger = LoggerFactory.getLogger(AutorisationAlimentAnimauxClientCDServiceImpl.class);

    private final AutorisationRepository autorisationRepository;
    private final FormaliteRepository formaliteRepository;
    private final DetAutorisationProduitRepository detAutorisationProduitRepository;
    private final ProduitRepository produitRepository;
    private final TypeAutorisationRepository typeAutorisationRepository;
    private final StructureRepository structureRepository;
    private final PieceJointeRepository pieceJointeRepository;
    private final TypePieceJointeRepository typePieceJointeRepository;
    private final AutorisationAgrementRepository autorisationAgrementRepository;

    @Autowired
    private UtilServices utilServices;

    @Autowired
    private Environment env;

    public AutorisationAlimentAnimauxClientCDServiceImpl(AutorisationRepository autorisationRepository,
                                                         FormaliteRepository formaliteRepository,
                                                         DetAutorisationProduitRepository
                                                                 detAutorisationProduitRepository,
                                                         ProduitRepository produitRepository,
                                                         TypeAutorisationRepository
                                                                 typeAutorisationRepository,
                                                         StructureRepository structureRepository,
                                                         AutorisationAgrementRepository
                                                                 autorisationAgrementRepository,
                                                         PieceJointeRepository pieceJointeRepository,
                                                         TypePieceJointeRepository typePieceJointeRepository
    ) {

        this.autorisationRepository = autorisationRepository;
        this.formaliteRepository = formaliteRepository;
        this.detAutorisationProduitRepository = detAutorisationProduitRepository;
        this.produitRepository = produitRepository;
        this.typeAutorisationRepository = typeAutorisationRepository;
        this.structureRepository = structureRepository;
        this.autorisationAgrementRepository = autorisationAgrementRepository;
        this.pieceJointeRepository = pieceJointeRepository;
        this.typePieceJointeRepository = typePieceJointeRepository;
    }

    /**
     * Update by kol olivier
     * Création de demande d'autorisation formulaire
     * @param request
     * @return
     */
    @Override
    @Transactional
    public ResponseEntity<?> create(AutorisationImportationDto request) {

        for (DetAutorisationProduitDto detAutorisationProduitDto : request.getDetAutorisationProduitDtos()) {
            Optional<Produit> optionalProduit = produitRepository.findByCode(
                    detAutorisationProduitDto.getProduit().getCode());
            if (!optionalProduit.isPresent()) {
                return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                        env.getProperty("message.produit.notfound")), HttpStatus.OK);
            }
        }
        //Enregistrement Autorisation
        Autorisation autorisation = saveAutorisation(request.getProvenance(),
                request.getDateEmbarquement());

        //Enregistrement Detail Autorisation Produit
        saveDetAutorisationProduit(request.getDetAutorisationProduitDtos(),
                autorisation);

        //Enregistrement de la formalite
        return saveFormalite(request.getNumeroAgrement(), autorisation,
               request.getCompteClient(), request.getPieceJointeDtoList(),request.getTypeRegime(),
                request.getNif(),request.getNumeroDossier());
    }


    public ResponseEntity<?> saveFormalite(String numeroAgrement, Autorisation autorisation, String compteClient,
                                           List<PieceJointeFormaliteDto> pieceJointeFormaliteDtos, TypeRegime typeRegime, String nif,String numeroDossier) {

        return switch (typeRegime.name()) {
            case "CONSOMMATION" -> {
                yield saveConsommationFormalite(numeroAgrement,autorisation, compteClient,
                        pieceJointeFormaliteDtos, typeRegime, nif,numeroDossier);
            }
            case "TRANSIT" -> {
                yield saveTransitFormalite(numeroAgrement,autorisation, compteClient,
                        pieceJointeFormaliteDtos, typeRegime, nif,numeroDossier);
            }
            default -> new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.not.found.typeregime")), HttpStatus.OK);
        };
    }

    /**
     * Méthode qui crée la formalitée et enregistrer la piece jointe avec ces documents
     * @param autorisation
     * @param compteClient
     * @return
     */
    public ResponseEntity<?> saveConsommationFormalite(String numeroAgrement, Autorisation autorisation, String compteClient,
                                           List<PieceJointeFormaliteDto> pieceJointeFormaliteDtos, TypeRegime typeRegime, String nif,String numeroDossier) {

        Optional<AutorisationAgrement> agrement  = autorisationAgrementRepository.findByNumero(numeroAgrement);

        if(agrement.isPresent()) {
            Formalite formalite = new Formalite(compteClient,
                    autorisation,agrement.get(),Etat.NON_SOUMIS,StatutPaiement.IMPAYER,
                    Chaine.Import);

            formalite.setNumGenere(utilServices.generateNumDemande(env.getProperty("message.code.genere.numero.autorisation")));

            Optional<Structure> optionalStructure = structureRepository.findByCode(env.getProperty("message.structure.code"));

            if (!optionalStructure.isPresent()) {
                throw new IllegalArgumentException(env.getProperty("message.exception.reference"));
            }
            formalite.setTypeRegime(typeRegime);
            formalite.setStructure(optionalStructure.get());
            formalite.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            formalite.setDateDmd(new Timestamp(System.currentTimeMillis()));
            formalite.setNif(nif);
            formalite.setNumeroDossier(numeroDossier);
            Formalite saveFormalite = formaliteRepository.save(formalite);

            savePiecejointe(pieceJointeFormaliteDtos, saveFormalite);

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"),saveFormalite.getId()), HttpStatus.OK);
        }
        else  {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.not.found.agrement"),numeroAgrement), HttpStatus.OK);
        }
    }


    /**
     * Méthode qui crée la formalitée et enregistrer la piece jointe avec ces documents
     * @param autorisation
     * @param compteClient
     * @return
     */
    public ResponseEntity<?> saveTransitFormalite(String numeroAgrement, Autorisation autorisation, String compteClient,
                                                       List<PieceJointeFormaliteDto> pieceJointeFormaliteDtos,
                                                  TypeRegime typeRegime, String nif,String numeroDossier) {


             Formalite formalite = new Formalite(compteClient,
                    autorisation,Etat.NON_SOUMIS,StatutPaiement.IMPAYER,
                    Chaine.Import, numeroAgrement);

            formalite.setNumGenere(utilServices.generateNumDemande(env.getProperty("message.code.genere.numero.autorisation")));

            Optional<Structure> optionalStructure = structureRepository.findByCode(env.getProperty("message.structure.code"));

            if (!optionalStructure.isPresent()) {
                throw new IllegalArgumentException(env.getProperty("message.exception.reference"));
            }
            formalite.setTypeRegime(typeRegime);
            formalite.setStructure(optionalStructure.get());
            formalite.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            formalite.setDateDmd(new Timestamp(System.currentTimeMillis()));
            formalite.setNif(nif);
            formalite.setNumeroDossier(numeroDossier);
            Formalite saveFormalite = formaliteRepository.save(formalite);

            savePiecejointe(pieceJointeFormaliteDtos, saveFormalite);

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"),saveFormalite.getId()), HttpStatus.OK);
        }



    /**
     * Création de l'autorisation
     * @return
     */
    private Autorisation saveAutorisation(String provenance, Date dateEmbarquement) {
        Autorisation autorisation = new Autorisation(provenance, dateEmbarquement);
        logger.info("Type autorisation ", env.getProperty("message.type.autorisation.ref.alimentAnimaux"));
        Optional<TypeAutorisation> optionalTypeAutorisation = typeAutorisationRepository.
                findByRef(env.getProperty("message.type.autorisation.ref.alimentAnimaux"));
        if (!optionalTypeAutorisation.isPresent()) {
            throw new IllegalArgumentException(env.getProperty("message.exception.reference"));
        }
        autorisation.setTypeAutorisation(optionalTypeAutorisation.get());
        return autorisationRepository.save(autorisation);
    }


    /**
     * Création du détail de produit
     * @param detAutorisationProduitDtos
     * @param autorisation
     */
    public void saveDetAutorisationProduit(List<DetAutorisationProduitDto> detAutorisationProduitDtos,
                                           Autorisation autorisation) {
        detAutorisationProduitDtos.forEach(data -> {
            Optional<Produit> produit = produitRepository.findByCode(data.getProduit().getCode());

            DetAutorisationProduit detProduit = new DetAutorisationProduit(produit.get(), data.getQuantite(),
                    data.getProvenance(), data.getNombreCarton(),
                    data.getUnite(), data.getOrigine(), autorisation, data.getPoidNets(), data.getConteneur());

            detAutorisationProduitRepository.save(detProduit);
        });

    }


    public void savePiecejointe(List<PieceJointeFormaliteDto> detPiecesJoinets, Formalite formalite) {
        detPiecesJoinets.forEach(data -> {
            PieceJointe piecejointe = new PieceJointe();
            Optional<TypePieceJointe> typePieceJointe = typePieceJointeRepository.findById(data.getIdTypePieceJointe());
            if(typePieceJointe.isPresent()) {
                piecejointe.setTypePieceJointe(typePieceJointe.get());
                piecejointe.setFormalite(formalite);
                piecejointe.setUrlPj(data.getUrlPj());
                piecejointe.setNomOriginePieceJointe(typePieceJointe.get().getLibelle());
                piecejointe.setNomGenerePieceJointe(data.getNomOriginePieceJointe());
                pieceJointeRepository.save(piecejointe);
            } else  {
                throw new IllegalArgumentException(env.getProperty("message.exception.reference"));
            }

        });
    }



}
