package com.acl.formaliteagricultureapi.serviceImpl.phytosanitaire.importation.cargaison;

import com.acl.formaliteagricultureapi.dto.imports.cargaison.PhytosanitaireCargaisonClientListDto;
import com.acl.formaliteagricultureapi.dto.imports.cargaison.PhytosanitaireCargaisonUpClientDto;
import com.acl.formaliteagricultureapi.dto.produit.DetPhytosanitaireProduitDto;
import com.acl.formaliteagricultureapi.models.DetPhytosanitaireProduit;
import com.acl.formaliteagricultureapi.models.Formalite;
import com.acl.formaliteagricultureapi.models.PhytoSanitaire;
import com.acl.formaliteagricultureapi.models.Produit;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.DetPhytosanitaireProduitRepository;
import com.acl.formaliteagricultureapi.repository.FormaliteRepository;
import com.acl.formaliteagricultureapi.repository.PhytosanitaireRepository;
import com.acl.formaliteagricultureapi.repository.ProduitRepository;
import com.acl.formaliteagricultureapi.services.phytosanitaire.importation.cargaison.InspectionPhytoCargaisonUpService;
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
 * @author kol on 20/09/2024
 * @project formalite-agriculture-api
 */
@Service
public class InspectionPhytoCargaisonUpServiceImpl implements InspectionPhytoCargaisonUpService {

    private final Logger logger = LoggerFactory.getLogger(InspectionPhytoCargaisonClientCRUDServiceImpl.class);

    private final PhytosanitaireRepository phytosanitaireRepository;

    private final FormaliteRepository formaliteRepository;

    private final DetPhytosanitaireProduitRepository detPhytosanitaireProduitRepository;

    private final ProduitRepository produitRepository;

    @Autowired
    private Environment env;

    public InspectionPhytoCargaisonUpServiceImpl(PhytosanitaireRepository phytosanitaireRepository, FormaliteRepository formaliteRepository, DetPhytosanitaireProduitRepository detPhytosanitaireProduitRepository, ProduitRepository produitRepository) {
        this.phytosanitaireRepository = phytosanitaireRepository;
        this.formaliteRepository = formaliteRepository;
        this.detPhytosanitaireProduitRepository = detPhytosanitaireProduitRepository;
        this.produitRepository = produitRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<?> updateDemande(PhytosanitaireCargaisonUpClientDto request) throws Exception {
        updatePhytosanitaire(request.getIdPhytosanitaire(),request.getNomDemandeur(),
                request.getProfessionDemandeur(), request.getAdresseDemandeur(),
                request.getLieuInspection(), request.getDatePrevueInspection());

        updateListDetAutorisationProduit(request.getDetPhytosanitaireProduitDtos(),
                request.getIdPhytosanitaire());

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
            optionalFormalite.get().setEtat(Etat.NON_SOUMIS);
            optionalFormalite.get().setUpdateAt(new Timestamp(System.currentTimeMillis()));
            formaliteRepository.save(optionalFormalite.get());
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"), optionalFormalite.get()),
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

            logger.info("id Phytosanitaire , {}", idphytosanitaire);
            //Suppression des produits liés à l'autorisation dans l'entité detAutorisationProduit
            detPhytosanitaireProduitRepository.deleteDetAutorisationProduitByIdPhytosanitaire(idphytosanitaire);

            //Nouvelle creation des produits lié à l'autorisation dans l'entité detAutorisationProduit
            Optional<PhytoSanitaire> optionalAutorisation= phytosanitaireRepository.findById(idphytosanitaire);
            saveDetailPhytoProduit(detAutorisationProduitDtos,optionalAutorisation.get());

        }

    }


    /**
     * Création du détail de produit
     * @param
     * @param phytoSanitaire
     */
    public void saveDetailPhytoProduit(List<DetPhytosanitaireProduitDto> detPhytosanitaireProduits,
                                       PhytoSanitaire phytoSanitaire) {
        detPhytosanitaireProduits.forEach(data -> {

            Optional<Produit> produit = produitRepository.findByCode(data.getProduit().getCode());
            DetPhytosanitaireProduit detProduit = new DetPhytosanitaireProduit(produit.get(),
                    phytoSanitaire, data.getQuantite(), data.getDescriptionEnvoi(),data.getOrigine(),
                    data.getConteneur(), data.getFournisseur(), data.getNombreColis());

            detPhytosanitaireProduitRepository.save(detProduit);
        });

    }




}
