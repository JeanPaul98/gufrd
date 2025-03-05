package com.acl.formaliteagricultureapi.serviceImpl.autorisation.demande.animauxVivant;


import com.acl.formaliteagricultureapi.dto.autorisation.demande.AutorisationImportationDto;
import com.acl.formaliteagricultureapi.dto.imports.demande.animauxVivant.AutorisationAnimauxVivantClientListDto;
import com.acl.formaliteagricultureapi.dto.imports.demande.animauxVivant.AutorisationAnimauxVivantsClientDto;
import com.acl.formaliteagricultureapi.dto.piecejointe.PieceJointeFormaliteDto;
import com.acl.formaliteagricultureapi.dto.produit.DetAutorisationProduitDto;
import com.acl.formaliteagricultureapi.models.*;
import com.acl.formaliteagricultureapi.models.enumeration.*;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.*;
import com.acl.formaliteagricultureapi.services.autorisation.demande.animauxVivant.AutorisationAnimauxVivantClientCRUDService;
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
public class AutorisationAnimauxVivantClientCRUDServiceImpl implements AutorisationAnimauxVivantClientCRUDService {

    private final Logger logger= LoggerFactory.getLogger(AutorisationAnimauxVivantClientCRUDServiceImpl.class);

    private final AutorisationRepository autorisationRepository;

    private final FormaliteRepository formaliteRepository;

    private final DetAutorisationProduitRepository detAutorisationProduitRepository;

    private final ProduitRepository produitRepository;

    private final TypeAutorisationRepository typeAutorisationRepository;

    private final StructureRepository structureRepository;

    private final AutorisationAgrementRepository autorisationAgrementRepository;

    private final PieceJointeRepository pieceJointeRepository;

    private final TypePieceJointeRepository typePieceJointeRepository;

    private final UtilServices utilServices ;

    @Autowired
    private Environment env;

    public AutorisationAnimauxVivantClientCRUDServiceImpl(AutorisationRepository autorisationRepository,
                                                          FormaliteRepository formaliteRepository,
                                                          DetAutorisationProduitRepository detAutorisationProduitRepository,
                                                          ProduitRepository produitRepository,
                                                          TypeAutorisationRepository typeAutorisationRepository,
                                                          StructureRepository structureRepository,
                                                          AutorisationAgrementRepository autorisationAgrementRepository, PieceJointeRepository pieceJointeRepository, TypePieceJointeRepository typePieceJointeRepository, UtilServices utilServices){

        this.autorisationRepository=autorisationRepository;
        this.formaliteRepository=formaliteRepository;
        this.detAutorisationProduitRepository = detAutorisationProduitRepository;
        this.produitRepository=produitRepository;
        this.typeAutorisationRepository=typeAutorisationRepository;
        this.structureRepository=structureRepository;
        this.autorisationAgrementRepository = autorisationAgrementRepository;
        this.pieceJointeRepository = pieceJointeRepository;
        this.typePieceJointeRepository = typePieceJointeRepository;
        this.utilServices = utilServices;
    }
    @Override
    @Transactional
    public ResponseEntity<?> create(AutorisationImportationDto request) {

        for(DetAutorisationProduitDto detAutorisationProduitDto: request.getDetAutorisationProduitDtos()){
            Optional<Produit>optionalProduit=produitRepository.findByCode(
                    detAutorisationProduitDto.getProduit().getCode());
            if(!optionalProduit. isPresent()){
                return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),"Produit "+
                        env.getProperty("message.notfound")), HttpStatus.OK);
            }

        }
        //Enregistrement Autorisation
        Autorisation autorisation= saveAutorisation(request.getProvenance());


        //Enregistrement Detail Autorisation Produit
        saveDetAutorisationProduit(request.getDetAutorisationProduitDtos(),
                autorisation);

        //Enregistrement de la formalite
        return saveFormalite(request.getNumeroAgrement(),autorisation,request.getCompteClient(), request.getPieceJointeDtoList(),
                request.getTypeRegime(), request.getNif(),request.getNumeroDossier());

    }

    public ResponseEntity<?> saveFormalite(String numeroAgrement, Autorisation autorisation, String compteClient,
                                           List<PieceJointeFormaliteDto> pieceJointeFormaliteDtos, TypeRegime typeRegime,
                                           String nif,String numeroDossier) {

        return switch (typeRegime.name()) {
            case "CONSOMMATION" -> {
                yield saveConsommationFormalite(numeroAgrement,autorisation, compteClient,numeroDossier,
                        pieceJointeFormaliteDtos, typeRegime, nif);
            }
            case "TRANSIT" -> {
                yield saveTransitFormalite(numeroAgrement,autorisation, compteClient,
                        pieceJointeFormaliteDtos, typeRegime, nif,numeroDossier);
            }
            default -> new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),"Type Regime "+
                    env.getProperty("message.not.found.entity")), HttpStatus.OK);
        };
    }

    /**
     * Méthode qui crée la formalitée et enregistrer la piece jointe avec ces documents
     * @param autorisation
     * @param compteClient
     * @return
     */
    public ResponseEntity<?> saveConsommationFormalite(String numeroAgrement, Autorisation autorisation, String compteClient,String numeroDossier,
                                                       List<PieceJointeFormaliteDto> pieceJointeFormaliteDtos, TypeRegime typeRegime, String nif) {

        Optional<AutorisationAgrement> agrement  = autorisationAgrementRepository.findByNumero(numeroAgrement);
        logger.info("numero {}", numeroAgrement);
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

            //ajout du Nif
            formalite.setNif(nif);
            formalite.setNumeroDossier(numeroDossier);
            Formalite saveFormalite = formaliteRepository.save(formalite);

            savePiecejointe(pieceJointeFormaliteDtos, saveFormalite);

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"),saveFormalite.getId()), HttpStatus.OK);
        }
        else  {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),"Agrement "+
                    env.getProperty("message.not.found.entity"),numeroAgrement), HttpStatus.OK);
        }
    }


    /**
     * Méthode qui crée la formalitée et enregistrer la piece jointe avec ces documents
     * @param autorisation
     * @param compteClient
     * @return
     */
    public ResponseEntity<?> saveTransitFormalite(String numeroAgrement, Autorisation autorisation, String compteClient,
                                                  List<PieceJointeFormaliteDto> pieceJointeFormaliteDtos, TypeRegime typeRegime,
                                                  String nif,String numeroDossier) {


        Formalite formalite = new Formalite(compteClient,
                autorisation,Etat.NON_SOUMIS,StatutPaiement.IMPAYER,
                Chaine.Import, numeroAgrement);

//,


        Optional<Structure> optionalStructure = structureRepository.findByCode(env.getProperty("message.structure.code"));
        formalite.setNumGenere(utilServices.generateNumDemande(env.getProperty("message.code.genere.numero.autorisation")));
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

    public Autorisation saveAutorisation( String provenance){
        Autorisation autorisation=new Autorisation(provenance);
        Optional<TypeAutorisation>optionalTypeAutorisation= typeAutorisationRepository.
                findByRef(env.getProperty("message.type.autorisation.ref.animauxVivant"));
        if(!optionalTypeAutorisation.isPresent()){
            throw new IllegalArgumentException(env.getProperty("message.exception.reference"));
        }
        autorisation.setTypeAutorisation(optionalTypeAutorisation.get());
        return autorisationRepository.save(autorisation);
    }

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

    /*
     *
     Debut mise a jour
     */


    /**
     * Mise a jour de la formalite
     * @param idFormalite
     * @param atp
     * @param nomNavire
     * @param imo
     * @param affreteur
     * @return
     */
    public ResponseEntity<?> updateFormalite(Long idFormalite, String atp, String nomNavire, String imo,
                                             String affreteur) {
        Optional<Formalite> optionalFormalite = formaliteRepository.findById(idFormalite);
        if (optionalFormalite.isPresent()) {
            optionalFormalite.get().setAtp(atp);
            optionalFormalite.get().setNomNavire(nomNavire);
            optionalFormalite.get().setImo(imo);
            optionalFormalite.get().setAffreteur(affreteur);
            optionalFormalite.get().setUpdateAt(new Timestamp(System.currentTimeMillis()));
            optionalFormalite.get().setDateSoumise(new Timestamp(System.currentTimeMillis()));
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"), formaliteRepository.save(optionalFormalite.get())),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.not.found.entity")), HttpStatus.OK);
        }
    }


    /**
     * Mise a jour du détail des autorisationss
     * @param detAutorisationProduitDtos
     * @param idAutorisation
     * @throws Exception
     */
    public void updateListDetAutorisationProduit(List<DetAutorisationProduitDto>
                                                         detAutorisationProduitDtos,
                                                 Long idAutorisation) throws Exception {
        if(detAutorisationProduitDtos.isEmpty()){
            throw  new Exception(env.getProperty("message.not.found.entity"));
        }else{

            detAutorisationProduitRepository.deleteDetAutorisationProduitByIdAutorisation(idAutorisation);
            Optional<Autorisation> optionalAutorisation= autorisationRepository.findById(idAutorisation);
            saveDetAutorisationProduit(detAutorisationProduitDtos,optionalAutorisation.get());

        }
    }

    public void updateDetAutorisationProduit(Long idAutorisation, Long idProduit,
                                             int quantite, String unite, Produit produit,
                                             int nombreCarton, String origine,
                                             Date dateProduction) throws Exception {
        DetAutorisationProduit detAutorisationProduit = detAutorisationProduitRepository.
                findByAutorisationIdAndProduitId(idAutorisation, idProduit);
        if (detAutorisationProduit == null) {
            //throw  new Exception(env.getProperty("message.not.found.entity"));
            Optional<Autorisation> optionalAutorisation= autorisationRepository.findById(idAutorisation);
            DetAutorisationProduit det= new DetAutorisationProduit(quantite,unite,nombreCarton,dateProduction,
                    origine,produit,optionalAutorisation.get());
        } else {
            detAutorisationProduit.setQuantite(quantite);
            detAutorisationProduit.setUnite(unite);
            detAutorisationProduit.setProduit(produit);
            detAutorisationProduit.setNombreCarton(nombreCarton);
            detAutorisationProduit.setOrigine(origine);
            detAutorisationProduit.setDateProduction(dateProduction);
            detAutorisationProduitRepository.save(detAutorisationProduit);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> validerDemande(AutorisationAnimauxVivantClientListDto request) throws Exception {

      //  updateAutorisation(request.getIdAutorisation(),request.getConteneur(),request.getDatearrivee());

        updateListDetAutorisationProduit(request.getDetAutorisationProduitDtos(),
                request.getIdAutorisation());

        return  null;
    }


    @Override
    public ResponseEntity<?> cancel(AutorisationAnimauxVivantClientListDto request) {
        return  null;
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
