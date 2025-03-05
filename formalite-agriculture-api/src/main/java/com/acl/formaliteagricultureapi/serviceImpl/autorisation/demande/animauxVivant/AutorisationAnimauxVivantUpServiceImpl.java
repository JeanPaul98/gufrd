package com.acl.formaliteagricultureapi.serviceImpl.autorisation.demande.animauxVivant;

import com.acl.formaliteagricultureapi.dto.autorisation.demande.AutorisationImportationUpdateDto;
import com.acl.formaliteagricultureapi.dto.produit.DetAutorisationProduitDto;
import com.acl.formaliteagricultureapi.models.Autorisation;
import com.acl.formaliteagricultureapi.models.DetAutorisationProduit;
import com.acl.formaliteagricultureapi.models.Formalite;
import com.acl.formaliteagricultureapi.models.Produit;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.*;
import com.acl.formaliteagricultureapi.serviceImpl.autorisation.enlevement.AutorisationAlimentAnimauxClientCDServiceImpl;
import com.acl.formaliteagricultureapi.services.autorisation.demande.animauxVivant.AutorisationAnimauxVivantUpService;
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
 * @author kol on 29/09/2024
 * @project formalite-agriculture-api
 */
@Service
public class AutorisationAnimauxVivantUpServiceImpl implements AutorisationAnimauxVivantUpService {


    private final Logger logger = LoggerFactory.getLogger(AutorisationAlimentAnimauxClientCDServiceImpl.class);

    private final AutorisationRepository autorisationRepository;
    private final FormaliteRepository formaliteRepository;
    private final DetAutorisationProduitRepository detAutorisationProduitRepository;
    private final ProduitRepository produitRepository;
    private final TypeAutorisationRepository typeAutorisationRepository;
    private final StructureRepository structureRepository;
    private final AgrementRepository agrementRepository;

    @Autowired
    private Environment env;

    public AutorisationAnimauxVivantUpServiceImpl(AutorisationRepository autorisationRepository, FormaliteRepository formaliteRepository, DetAutorisationProduitRepository detAutorisationProduitRepository, ProduitRepository produitRepository, TypeAutorisationRepository typeAutorisationRepository, StructureRepository structureRepository, AgrementRepository agrementRepository) {
        this.autorisationRepository = autorisationRepository;
        this.formaliteRepository = formaliteRepository;
        this.detAutorisationProduitRepository = detAutorisationProduitRepository;
        this.produitRepository = produitRepository;
        this.typeAutorisationRepository = typeAutorisationRepository;
        this.structureRepository = structureRepository;
        this.agrementRepository = agrementRepository;
    }

    @Override
    public ResponseEntity<?> updateDemande(AutorisationImportationUpdateDto request) throws Exception {

        updateAutorisation(request.getIdAutorisation(), request.getProvenance(), request.getDateEmbarquement());

        updateListDetAutorisationProduit(request.getDetAutorisationProduitDtos(),
                request.getIdAutorisation());

        return  updateFormalite(request.getIdFormalite());

    }


    /**
     * Mise a jour des autorisations
     * @param idAutorisation
     * @throws Exception
     */
    public void updateAutorisation(Long idAutorisation, String provenance, Date dateEmbarquement) throws Exception {
        Optional<Autorisation> optionalAutorisation = autorisationRepository.findById(idAutorisation);
        if (optionalAutorisation.isPresent()) {
            optionalAutorisation.get().setProvenance(provenance);
            optionalAutorisation.get().setDateembarquement(dateEmbarquement);
            autorisationRepository.save(optionalAutorisation.get());
        } else {
            throw  new Exception(env.getProperty("message.not.found.entity"));

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

    /**
     * Mise a jour de la formalite
     * @param idFormalite
     * @return
     */
    public ResponseEntity<?> updateFormalite(Long idFormalite) {
        Optional<Formalite> optionalFormalite = formaliteRepository.findById(idFormalite);
        if (optionalFormalite.isPresent()) {
            optionalFormalite.get().setEtat(Etat.NON_SOUMIS);
            optionalFormalite.get().setUpdateAt(new Timestamp(System.currentTimeMillis()));
            optionalFormalite.get().setDateSoumise(new Timestamp(System.currentTimeMillis()));
            Formalite formalite = formaliteRepository.save(optionalFormalite.get());
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"), optionalFormalite.get().getId()),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.not.found.entity")), HttpStatus.OK);
        }
    }
}
