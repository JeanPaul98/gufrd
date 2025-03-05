package com.acl.formaliteagricultureapi.serviceImpl.phytosanitaire.exportation.obtentionCertif;


import com.acl.formaliteagricultureapi.dto.exports.vegetaux.inspection.InspectionPhytoObtentionCertifDto;
import com.acl.formaliteagricultureapi.dto.formalite.UpdateFormaliteDto;
import com.acl.formaliteagricultureapi.dto.imports.navire.PhytosanitaireNavireClientListDto;
import com.acl.formaliteagricultureapi.dto.produit.DetPhytosanitaireProduitDto;
import com.acl.formaliteagricultureapi.dto.produit.DetTraitementProduitDto;
import com.acl.formaliteagricultureapi.models.*;
import com.acl.formaliteagricultureapi.models.enumeration.Chaine;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.*;
import com.acl.formaliteagricultureapi.services.phytosanitaire.exportation.obtentionCertif.InspectionPhytoObtentionCertCrudService;
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
public class InspectionPhytoObtentionCertifClientCRUDServiceImpl implements InspectionPhytoObtentionCertCrudService {

    private final Logger logger = LoggerFactory.getLogger(InspectionPhytoObtentionCertifClientCRUDServiceImpl.class);

    private final PhytosanitaireRepository phytosanitaireRepository;

    private final FormaliteRepository formaliteRepository;

    private final DetPhytosanitaireProduitRepository detPhytosanitaireProduitRepository;

    private final ProduitRepository produitRepository;

    private final TypePhytosanitaireRepository typePhytosanitaireRepository;

    private final StructureRepository structureRepository;

    private final DetTraitementProduitRepository detTraitementProduitRepository;

    private final  SocieteRepository societeRepository;

    @Autowired
    private Environment env;


    public InspectionPhytoObtentionCertifClientCRUDServiceImpl(PhytosanitaireRepository phytosanitaireRepository,
                                                               FormaliteRepository formaliteRepository,
                                                               DetPhytosanitaireProduitRepository detPhytosanitaireProduitRepository,
                                                               ProduitRepository produitRepository,
                                                               TypePhytosanitaireRepository typePhytosanitaireRepository,
                                                               StructureRepository structureRepository, DetTraitementProduitRepository detTraitementProduitRepository, SocieteRepository societeRepository) {
        this.phytosanitaireRepository = phytosanitaireRepository;
        this.formaliteRepository = formaliteRepository;
        this.detPhytosanitaireProduitRepository = detPhytosanitaireProduitRepository;
        this.produitRepository = produitRepository;
        this.typePhytosanitaireRepository = typePhytosanitaireRepository;
        this.structureRepository = structureRepository;
        this.detTraitementProduitRepository = detTraitementProduitRepository;
        this.societeRepository = societeRepository;
    }

    /**
     * Création de demande d'inspection formulaire
     * @param request
     * @return
     */
    @Override
    @Transactional
    public ResponseEntity<?> create(InspectionPhytoObtentionCertifDto request) {
        for (DetPhytosanitaireProduitDto detDto : request.getDetPhytosanitaireProduitDtos()) {
            Optional<Produit> optionalProduit = produitRepository.findByCode(
                    detDto.getProduit().getCode());
            if (!optionalProduit.isPresent()) {
                return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                        env.getProperty("message.notfound")), HttpStatus.OK);
            }
        }

        //Enregistrement demande obtention certificat
        PhytoSanitaire phytoSanitaire = savePhytosanitaire(request.getLieuDestination(),
                request.getNomExpediteur(), request.getAdresseExpediteur(),
                request.getNomDestinataire(), request.getAdresseDestinataire(),
                request.getMoyenTransport(), request.getRenseignementComplementaire(),
                request.isTraitementProduit(), request.getDateHeureEmbarquement()
                );

        //Enregistrement Detail Phytosanitaire Produit
        saveDetailPhytoProduit(request.getDetPhytosanitaireProduitDtos(),
                phytoSanitaire);

        //Request TraitementDetail produit
        if(request.isTraitementProduit()) {
            saveDetailTraitementProduit(request.getDetTraitementProduitDtos(), phytoSanitaire);
        }

        //Enregistrement de la formalite
        return saveFormalite(request.getAtp(), request.getNomNavire(),
                request.getImo(), request.getAffreteur(), phytoSanitaire);
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
                                           PhytoSanitaire phytoSanitaire) {
        Formalite formalite = new Formalite(atp, nomNavire, immo, affreteur);
        formalite.setChaine(Chaine.Export);
        formalite.setNumGenere("PHYOBTEN" + System.currentTimeMillis());
        formalite.setPhytoSanitaire(phytoSanitaire);
        Optional<Structure> optionalStructure = structureRepository.findByCode(env.getProperty("message.structure.vegetaux"));
        if (!optionalStructure.isPresent()) {
            throw new IllegalArgumentException(env.getProperty("message.exception.reference"));
        }
        formalite.setStructure(optionalStructure.get());
        formalite.setEtat(Etat.NON_SOUMIS);
        formalite.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        formalite.setDateDmd(new Timestamp(System.currentTimeMillis()));
        Formalite saveFormalite = formaliteRepository.save(formalite);
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                env.getProperty("message.succes"), saveFormalite.getId()), HttpStatus.OK);
    }

    /**
     * Enregistrer la demande d'obtention phytosanitaire
     * @param lieuDestination
     * @param nomExpediteur
     * @param adresseExpediteur
     * @param nomDestinataire
     * @param adresseDestinataire
     * @param moyenTransport
     * @param renseignementComplementaire
     * @param traitementProduit
     * @param dateHeureEmbarquement
     * @return
     */
    public PhytoSanitaire savePhytosanitaire(String lieuDestination,
                                             String nomExpediteur, String adresseExpediteur,
                                             String nomDestinataire, String adresseDestinataire,
                                             String moyenTransport, String renseignementComplementaire,
                                             boolean traitementProduit,
                                             Date dateHeureEmbarquement) {
        PhytoSanitaire phytoSanitaire = new PhytoSanitaire();
        phytoSanitaire.setLieuDestination(lieuDestination);
        phytoSanitaire.setNomExpediteur(nomExpediteur);
        phytoSanitaire.setAdresseExpediteur(adresseExpediteur);
        phytoSanitaire.setNomDestinataire(nomDestinataire);
        phytoSanitaire.setAdresseDestinataire(adresseDestinataire);
        phytoSanitaire.setMoyenTransport(moyenTransport);
        phytoSanitaire.setRenseignementCompl(renseignementComplementaire);
        phytoSanitaire.setDateHeureEmbarquement(dateHeureEmbarquement);
        phytoSanitaire.setTraitementProduit(traitementProduit);

        Optional<TypeInspPhyto> optionalTypeAutorisation = typePhytosanitaireRepository.
                findByRef(env.getProperty("message.type.phytosanitaire.ref.obtencertif"));
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
            Optional<Produit> optionalProduitproduit = produitRepository.findByCode(data.getProduit().getCode());
            detProduit.setProduit(optionalProduitproduit.get());
            detProduit.setPhytoSanitaire(phytoSanitaire);
            detProduit.setQuantite(data.getQuantite());
            detProduit.setDescriptionEnvoi(data.getDescriptionEnvoi());
            detProduit.setNombreColis(data.getNombreColis());
            detProduit.setPaysEtLieuOrigin(data.getOrigine());
            detPhytosanitaireProduitRepository.save(detProduit);
        });

    }

    /**
     * Création du détail de produit
     * @param
     * @param phytoSanitaire
     */
    public void saveDetailTraitementProduit(List<DetTraitementProduitDto> detTraitementProduitDtos,
                                       PhytoSanitaire phytoSanitaire) {
        for (DetTraitementProduitDto data : detTraitementProduitDtos) {
            DetTraitementProduit detProduit = new DetTraitementProduit();
            Optional<Produit> opTraitementProduit = produitRepository.findByCode(data.getProduit().getCode());
            Optional<Societe> societe = societeRepository.findById(data.getIdSociete());
            if(opTraitementProduit.isPresent()) {
                detProduit.setProduit(opTraitementProduit.get());
            } else {
                throw new IllegalArgumentException(env.getProperty("message.exception.reference"));
            }
            if(societe.isPresent()) {
                detProduit.setSocieteTraitement(societe.get());
            } else {
                throw new IllegalArgumentException(env.getProperty("message.exception.reference"));
            }

            detProduit.setPhytoSanitaire(phytoSanitaire);
            detProduit.setConcentration(data.getConcentration());
            detProduit.setTemperature(data.getTemperature());
            detProduit.setNatureTraitement(data.getNatureTraitement());
            detProduit.setDuree(data.getDuree());
            detTraitementProduitRepository.save(detProduit);
        }

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


    public void updateListDetAutorisationProduit(List<DetPhytosanitaireProduitDto>
                                                                      detPhytosanitaireProduits,
                                                              Long idPhytosanitaire) throws Exception {
        if(detPhytosanitaireProduits.isEmpty()){
            throw  new Exception(env.getProperty("message.not.found.entity"));
        }else{
            for(DetPhytosanitaireProduitDto detAutorisationProduitDto:detPhytosanitaireProduits){
               Optional<Produit>  produit = produitRepository.findByCode(detAutorisationProduitDto.getProduit().getCode());

                updateDetPhytosanitaireProduit(idPhytosanitaire, produit.get().getId(),
                        detAutorisationProduitDto.getQuantite(),
                        detAutorisationProduitDto.getDescriptionEnvoi(),
                        detAutorisationProduitDto.getConteneur(),
                        detAutorisationProduitDto.getFournisseur(),
                        produit.get(),
                        detAutorisationProduitDto.getOrigine()
                       );
            }

        }


    }

    public void updateDetPhytosanitaireProduit(Long idPhytosanitaire, Long idProduit,
                                                          int quantite, String descriptionEnvoie,
                                                            String conteneur, String fournisseur,
                                                            Produit produit,
                                                          String origine
                                                         ) throws Exception {
        DetPhytosanitaireProduit detPhytosanitaireProduit = detPhytosanitaireProduitRepository.
                findByPhytosanitaireIdAndProduitId(idPhytosanitaire, idProduit);
        if (detPhytosanitaireProduit == null) {
            throw  new Exception(env.getProperty("message.not.found.entity"));
        } else {
            detPhytosanitaireProduit.setQuantite(quantite);
            detPhytosanitaireProduit.setPaysEtLieuOrigin(origine);
            detPhytosanitaireProduit.setProduit(produit);
            detPhytosanitaireProduit.setConteneur(conteneur);
            detPhytosanitaireProduit.setDescriptionEnvoi(descriptionEnvoie);
            detPhytosanitaireProduit.setFournisseur(fournisseur);
            detPhytosanitaireProduit.setPaysEtLieuOrigin(origine);
            detPhytosanitaireProduitRepository.save(detPhytosanitaireProduit);
        }


    }


    public ResponseEntity<?> cancelFormalite(Long idFormalite) {
        Optional<Formalite> optionalFormalite = formaliteRepository.findById(idFormalite);
        if (optionalFormalite.isPresent()) {
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
    public ResponseEntity<?> cancel(UpdateFormaliteDto request) {
        return  cancelFormalite(request.getIdFormalite());
    }
}
