package com.acl.formaliteagricultureapi.serviceImpl.phytosanitaire.importation.navire;


import com.acl.formaliteagricultureapi.dto.imports.navire.PhytosanitaireNavireClientDto;
import com.acl.formaliteagricultureapi.dto.imports.navire.PhytosanitaireNavireClientListDto;
import com.acl.formaliteagricultureapi.dto.piecejointe.PieceJointeDto;
import com.acl.formaliteagricultureapi.dto.piecejointe.PieceJointeFormaliteDto;
import com.acl.formaliteagricultureapi.dto.produit.DetPhytosanitaireProduitDto;
import com.acl.formaliteagricultureapi.exception.DocumentNotFoundException;
import com.acl.formaliteagricultureapi.models.*;
import com.acl.formaliteagricultureapi.models.enumeration.Chaine;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.models.enumeration.StatutPaiement;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.*;
import com.acl.formaliteagricultureapi.services.phytosanitaire.importation.navire.InspPhytoNavireClientCrudService;
import com.acl.formaliteagricultureapi.services.piecejointe.PieceJointeService;
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
public class InspectionPhytoNavireClientCRUDServiceImpl implements InspPhytoNavireClientCrudService {

    private final Logger logger = LoggerFactory.getLogger(InspectionPhytoNavireClientCRUDServiceImpl.class);

    private final PhytosanitaireRepository phytosanitaireRepository;

    private final FormaliteRepository formaliteRepository;

    private final DetPhytosanitaireProduitRepository detPhytosanitaireProduitRepository;

    private final ProduitRepository produitRepository;

    private final TypePhytosanitaireRepository typePhytosanitaireRepository;

    private final StructureRepository structureRepository;

    private final TypePieceJointeRepository typePieceJointeRepository;

    private final PieceJointeRepository pieceJointeRepository;


    @Autowired
    private UtilServices utilServices;

    @Autowired
    private Environment env;


    public InspectionPhytoNavireClientCRUDServiceImpl(PhytosanitaireRepository phytosanitaireRepository,
                                                      FormaliteRepository formaliteRepository,
                                                      DetPhytosanitaireProduitRepository detPhytosanitaireProduitRepository,
                                                      ProduitRepository produitRepository,
                                                      TypePhytosanitaireRepository typePhytosanitaireRepository,
                                                      StructureRepository structureRepository, TypePieceJointeRepository typePieceJointeRepository, PieceJointeRepository pieceJointeRepository) {
        this.phytosanitaireRepository = phytosanitaireRepository;
        this.formaliteRepository = formaliteRepository;
        this.detPhytosanitaireProduitRepository = detPhytosanitaireProduitRepository;
        this.produitRepository = produitRepository;
        this.typePhytosanitaireRepository = typePhytosanitaireRepository;
        this.structureRepository = structureRepository;
        this.typePieceJointeRepository = typePieceJointeRepository;
        this.pieceJointeRepository = pieceJointeRepository;
    }

    /**
     * Création de demande d'autorisation formulaire
     * @param request
     * @return
     */
    @Override
    @Transactional
    public ResponseEntity<?> create(PhytosanitaireNavireClientDto request) {

        //Enregistrement Autorisation
        PhytoSanitaire phytoSanitaire = savePhytosanitaire(request.getNomDemandeur(), request.getProfessionDemandeur(),
                request.getAdresseDemandeur(), request.getLieuInspection(), request.getDatePrevueInspection(),request.getStructureDemandeur(),
                request.getTypeCargaison()
                );


        //Enregistrement de la formalite
        return saveFormalite(request.getAtp(), request.getNomNavire(),
                request.getImo(), request.getAffreteur(),request.getCompteClient(),
                phytoSanitaire, request.getPieceJointeDtoList(),request.getNumeroDossier(),request.getNif());
    }

    /**
     * Méthode qui crée la formalitée
     * @param atp
     * @param nomNavire
     * @param immo
     * @param affreteur
     * @param phytoSanitaire
     * @return
     */
    public ResponseEntity<?> saveFormalite(String atp, String nomNavire, String immo, String affreteur,
                                           String compteClient,PhytoSanitaire phytoSanitaire,
                                           List<PieceJointeFormaliteDto> pieceJointeFormaliteDtos,String numeroDossier,String nif) {

        Formalite formalite = new Formalite(atp, nomNavire, immo, affreteur,compteClient);
        formalite.setChaine(Chaine.Import);
        formalite.setStatutPaiement(StatutPaiement.IMPAYER);
        formalite.setNumGenere(utilServices.generateNumDemande(env.getProperty("message.code.genere.numero.phytosanitaire")));
        formalite.setPhytoSanitaire(phytoSanitaire);
        Optional<Structure> optionalStructure = structureRepository.findByCode(env.getProperty("message.structure.vegetaux"));

        if (!optionalStructure.isPresent()) {
            throw new DocumentNotFoundException(env.getProperty("message.exception.reference"));
        }
        formalite.setNif(nif);
        formalite.setNumeroDossier(numeroDossier);
        formalite.setStructure(optionalStructure.get());
        formalite.setEtat(Etat.NON_SOUMIS);
        formalite.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        formalite.setDateDmd(new Timestamp(System.currentTimeMillis()));
        Formalite saveFormalite = formaliteRepository.save(formalite);

        //Enregistrer la pièce jointe

        savePiecejointe(pieceJointeFormaliteDtos , formalite);

        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                env.getProperty("message.succes"), saveFormalite.getId()), HttpStatus.OK);
    }



    public PhytoSanitaire savePhytosanitaire(String nomDemandeur, String professionDemandeur,
                                             String adresseDemandeur, String lieuInspection,
                                             Date datePrevueInspection, String structureDemandeur, String typeCargaison) {
        PhytoSanitaire phytoSanitaire = new PhytoSanitaire(nomDemandeur, professionDemandeur, adresseDemandeur,
                lieuInspection, structureDemandeur, typeCargaison,datePrevueInspection);
        Optional<TypeInspPhyto> optionalTypeAutorisation = typePhytosanitaireRepository.
                findByRef(env.getProperty("message.type.phytosanitaire.ref.navire"));
        if (!optionalTypeAutorisation.isPresent()) {
            throw new IllegalArgumentException(env.getProperty("message.exception.reference"));
        }
        phytoSanitaire.setTypeInspPhyto(optionalTypeAutorisation.get());
        return phytosanitaireRepository.save(phytoSanitaire);
    }

    /**
     * Création du détail de produit
     * @param
     * @param phytoSanitaire
     */
    public void saveDetailPhytoProduit(List<DetPhytosanitaireProduitDto> detPhytosanitaireProduits,
                                           PhytoSanitaire phytoSanitaire) {
        detPhytosanitaireProduits.forEach(data -> {
            DetPhytosanitaireProduit detProduit = new DetPhytosanitaireProduit();
            Optional<Produit> produit = produitRepository.findByCode(data.getProduit().getCode());
            detProduit.setProduit(produit.get());
            detProduit.setPhytoSanitaire(phytoSanitaire);
            detProduit.setQuantite(data.getQuantite());
            detProduit.setDescriptionEnvoi(data.getDescriptionEnvoi());
            detProduit.setConteneur(data.getConteneur());
            detProduit.setFournisseur(data.getFournisseur());
            detProduit.setPaysEtLieuOrigin(data.getOrigine());
            detPhytosanitaireProduitRepository.save(detProduit);
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
               throw new DocumentNotFoundException(env.getProperty("message.exception.reference"));
           }

        });
    }

    /*
     *
     Debut valider demande
     */
    @Override
    @Transactional
    public ResponseEntity<?> validerDemande(PhytosanitaireNavireClientListDto request) throws Exception {

        updatePhytosanitaire(request.getIdPhytosanitaire(),request.getNomDemandeur(),request.getProfessionDemandeur()
        ,request.getAdresseDemandeur(), request.getLieuInspection(), request.getDatePrevueInspection());


        return  updateFormalite(request.getIdFormalite(),request.getAtp(),request.getNomNavire(),
                request.getImo(), request.getAffreteur());

    }


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
            optionalFormalite.get().setEtat(Etat.SOUMIS);
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


    public void updatePhytosanitaire(Long idPhytosanitaire, String nomDemandeur , String professionDemandeur,
            String adresseDemandeur, String lieuInspection, Date datePrevueInspection ) throws Exception {
        Optional<PhytoSanitaire> optionalPhytoSanitaire = phytosanitaireRepository.findById(idPhytosanitaire);
        if (optionalPhytoSanitaire.isPresent()) {
            optionalPhytoSanitaire.get().setLieuInspection(lieuInspection);
            optionalPhytoSanitaire.get().setNomDemandeur(nomDemandeur);
            optionalPhytoSanitaire.get().setProfessionDemandeur(professionDemandeur);
            optionalPhytoSanitaire.get().setDatePrevueInspection(datePrevueInspection);
            optionalPhytoSanitaire.get().setAdresseDemandeur(adresseDemandeur);
            phytosanitaireRepository.save(optionalPhytoSanitaire.get());
        } else {
            throw  new Exception(env.getProperty("message.not.found.entity"));

        }
    }

    /**
     * Mise a jour du détail des autorisationss
     * @param detAutorisationProduitDtos
     * @param idphytosanitaire
     * @throws Exception
     */
    public void updateListDetAutorisationProduit(List<DetPhytosanitaireProduitDto>
                                                         detAutorisationProduitDtos,
                                                 Long idphytosanitaire) throws Exception {
        if(detAutorisationProduitDtos.isEmpty()){
            throw  new Exception(env.getProperty("message.not.found.entity"));
        }else{

            //Suppression des produits liés à l'autorisation dans l'entité detAutorisationProduit
            detPhytosanitaireProduitRepository.deleteDetAutorisationProduitByIdPhytosanitaire(idphytosanitaire);

            //Nouvelle creation des produits lié à l'autorisation dans l'entité detAutorisationProduit
            Optional<PhytoSanitaire> optionalAutorisation= phytosanitaireRepository.findById(idphytosanitaire);
            saveDetailPhytoProduit(detAutorisationProduitDtos,optionalAutorisation.get());

        }

    }



    public ResponseEntity<?> cancelFormalite(Long idFormalite, String atp, String nomNavire, String imo,
                                             String affreteur) {
        Optional<Formalite> optionalFormalite = formaliteRepository.findById(idFormalite);
        if (optionalFormalite.isPresent()) {
            optionalFormalite.get().setAtp(atp);
            optionalFormalite.get().setNomNavire(nomNavire);
            optionalFormalite.get().setImo(imo);
            optionalFormalite.get().setAffreteur(affreteur);
            optionalFormalite.get().setEtat(Etat.ANNULER);
            optionalFormalite.get().setUpdateAt(new Timestamp(System.currentTimeMillis()));
            optionalFormalite.get().setDateAnnulation(new Timestamp(System.currentTimeMillis()));
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"), formaliteRepository.save(optionalFormalite.get())),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.not.found.entity")), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> cancel(PhytosanitaireNavireClientListDto request) {
        return  cancelFormalite(request.getIdFormalite(),request.getAtp(),request.getNomNavire(),
                request.getImo(), request.getAffreteur());
    }
}
