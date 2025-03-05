package com.acl.formaliteagricultureapi.serviceImpl.autorisation.depotage;

import com.acl.formaliteagricultureapi.dto.imports.depotage.AutorisationDepotageTransitClientListDto;
import com.acl.formaliteagricultureapi.dto.produit.DetAutorisationProduitDto;
import com.acl.formaliteagricultureapi.models.Autorisation;
import com.acl.formaliteagricultureapi.models.DetAutorisationProduit;
import com.acl.formaliteagricultureapi.models.Formalite;
import com.acl.formaliteagricultureapi.models.Produit;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.AutorisationRepository;
import com.acl.formaliteagricultureapi.repository.DetAutorisationProduitRepository;
import com.acl.formaliteagricultureapi.repository.FormaliteRepository;
import com.acl.formaliteagricultureapi.repository.ProduitRepository;
import com.acl.formaliteagricultureapi.serviceImpl.autorisation.enlevement.AutorisationEnlevementClientUpServiceImpl;
import com.acl.formaliteagricultureapi.services.autorisation.depotage.AutorisationDepotageTransitClientUpService;
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
 * @author kol on 30/08/2024
 * @project formalite-agriculture-api
 */
@Service
public class AutorisationDepotageTransitClientUpServiceImpl implements AutorisationDepotageTransitClientUpService {

    Logger logger= LoggerFactory.getLogger(AutorisationEnlevementClientUpServiceImpl.class);

    private final FormaliteRepository formaliteRepository;

    private final AutorisationRepository autorisationRepository;

    private final DetAutorisationProduitRepository detAutorisationProduitRepository;

    private final ProduitRepository produitRepository;

    @Autowired
    private Environment env;

    public AutorisationDepotageTransitClientUpServiceImpl(FormaliteRepository formaliteRepository, AutorisationRepository autorisationRepository, DetAutorisationProduitRepository detAutorisationProduitRepository, ProduitRepository produitRepository) {
        this.formaliteRepository = formaliteRepository;
        this.autorisationRepository = autorisationRepository;
        this.detAutorisationProduitRepository = detAutorisationProduitRepository;
        this.produitRepository = produitRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<?> updateDemande(AutorisationDepotageTransitClientListDto request) throws Exception {

        updateAutorisation(request.getIdAutorisation(),request.getDatearrivee());

        updateListDetAutorisationProduit(request.getDetAutorisationProduitDtos(),
                request.getIdAutorisation());

        return  updateFormalite(request.getIdFormalite(),request.getAtp(),request.getNomNavire(),
                request.getImo(), request.getAffreteur(), request.getNomImportateur());
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
        }
        else{

            logger.info("Id autorisation {} ," , idAutorisation);

            //Suppression des produits liés à l'autorisation dans l'entité detAutorisationProduit
            detAutorisationProduitRepository.deleteDetAutorisationProduitByIdAutorisation(idAutorisation);

            //Nouvelle creation des produits lié à l'autorisation dans l'entité detAutorisationProduit
            Optional<Autorisation> optionalAutorisation= autorisationRepository.findById(idAutorisation);
            saveDetAutorisationProduit(detAutorisationProduitDtos,optionalAutorisation.get());

        }

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


    /**
     * Mise a jour des autorisations
     * @param idAutorisation
     * @param
     * @param datearrivee
     * @throws Exception
     */
    public void updateAutorisation(Long idAutorisation,  Date datearrivee) throws Exception {
        Optional<Autorisation> optionalAutorisation = autorisationRepository.findById(idAutorisation);
        if (optionalAutorisation.isPresent()) {
            optionalAutorisation.get().setDatearrivee(datearrivee);
            autorisationRepository.save(optionalAutorisation.get());
        } else {
            throw  new Exception(env.getProperty("message.not.found.entity"));

        }
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
                                             String affreteur, String nomImportateur) {
        Optional<Formalite> optionalFormalite = formaliteRepository.findById(idFormalite);
        if (optionalFormalite.isPresent()) {
            optionalFormalite.get().setAtp(atp);
            optionalFormalite.get().setNomNavire(nomNavire);
            optionalFormalite.get().setImo(imo);
            optionalFormalite.get().setNomImportateur(nomImportateur);
            optionalFormalite.get().setAffreteur(affreteur);
            optionalFormalite.get().setEtat(Etat.NON_SOUMIS);
            optionalFormalite.get().setUpdateAt(new Timestamp(System.currentTimeMillis()));
            Formalite formalite = formaliteRepository.save(optionalFormalite.get());
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"), formalite.getId()),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.not.found.entity")), HttpStatus.OK);
        }
    }


}
