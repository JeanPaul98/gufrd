package com.acl.formaliteagricultureapi.serviceImpl.processVerbal.cargo;

import com.acl.formaliteagricultureapi.dto.procesVerbal.DetPVInspecteurDto;
import com.acl.formaliteagricultureapi.dto.procesVerbal.DetPvProduitDto;
import com.acl.formaliteagricultureapi.dto.procesVerbal.PvPhytoSanitaireCargConteneurDto;
import com.acl.formaliteagricultureapi.dto.produit.DetTraitementProduitDto;
import com.acl.formaliteagricultureapi.models.*;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.*;
import com.acl.formaliteagricultureapi.services.processVerval.cargaison.PvPhytoCargaisonService;
import jakarta.transaction.Transactional;
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
 * @author kol on 07/09/2024
 * @project formalite-agriculture-api
 */
@Service
public class PvPhytoCargoServiceImpl implements PvPhytoCargaisonService {


    private final FormaliteRepository formaliteRepository;

    private final ProcesVerbalRepository procesVerbalRepository;

    private final DetPvProduitRepository detPvProduitRepository;

    private  final DetPvInspecteurRepository detPvInspecteurRepository;

    private final ProduitRepository produitRepository;

    private final TypePvRepository typePvRepository;

    private final InspecteurRepository inspecteurRepository;

    private final SocieteRepository societeRepository;

   private final DetTraitementProduitRepository detTraitementProduitRepository;

    @Autowired
    private Environment env;

    public PvPhytoCargoServiceImpl(FormaliteRepository formaliteRepository, ProcesVerbalRepository procesVerbalRepository,
                                   DetPvProduitRepository detPvProduitRepository, DetPvInspecteurRepository detPvInspecteurRepository,
                                   ProduitRepository produitRepository, TypePvRepository typePvRepository, InspecteurRepository inspecteurRepository, SocieteRepository societeRepository, DetTraitementProduitRepository detTraitementProduitRepository) {
        this.formaliteRepository = formaliteRepository;
        this.procesVerbalRepository = procesVerbalRepository;
        this.detPvProduitRepository = detPvProduitRepository;
        this.detPvInspecteurRepository = detPvInspecteurRepository;
        this.produitRepository = produitRepository;
        this.typePvRepository = typePvRepository;
        this.inspecteurRepository = inspecteurRepository;
        this.societeRepository = societeRepository;
        this.detTraitementProduitRepository = detTraitementProduitRepository;
    }


    @Override
    @Transactional
    public ResponseEntity<?> create(PvPhytoSanitaireCargConteneurDto request) {

        Optional<Formalite> formalite = formaliteRepository.findById(request.getIdFormalite());

        if(formalite.isPresent()){

            //Controle du type Phytosanitaire
            if(formalite.get().getPhytoSanitaire().getTypeInspPhyto().getRef().
                    equalsIgnoreCase(env.getProperty("message.type.phytosanitaire.ref.cargaison"))) {

                //Création du prcess verbale
                ProcesVerbal procesVerbal = saveProcesVerval(request.getPointEntree(),request.getLieuInspection(),
                        request.getDateInspection(), request.getDateApplication(),request.getExpediteur(),
                        request.getDestinataire(), request.getNumeroEnregistrement(), request.getControleTechnique(),
                        request.getResultatInspection(),request.getDatePv(), request.getRemarque(), request.getIdFormalite(),
                        request.getAgentPv());
                procesVerbal.setDateArrivee(request.getDatearrivee());

                //Création des inspecteurs  du process verbal
                saveDetailPvInspecteur(request.getDetPVInspecteurDtos(), procesVerbal);

                // Création des produits du process verbale
                saveDetailPvProduit(request.getDetPvProduitDtos(), procesVerbal);

            //    saveDetailTraitementProduit(request.getDetTraitementProduitDtos(), formalite.get().getPhytoSanitaire());

                // update Formalite
                return updateFormalite(request.getIdFormalite());

            } else  {
                return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                        env.getProperty("message.formalite.conformite")), HttpStatus.OK);
            }


        }else{
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.notfound")), HttpStatus.OK);
        }

    }



    public ProcesVerbal saveProcesVerval(String pointEntree, String lieuInspection,  Date dateInspection, Date dateApplication,
                                         String expediteur, String destinataire, String numeroEnregistrement, String controleTechnique,
                                         String resultatInspection, Date datePv,
                                         String remarque, Long idFormalite, String agentPV) {

        ProcesVerbal procesVerbal = new ProcesVerbal(pointEntree, lieuInspection,dateInspection,
                dateApplication,expediteur,destinataire,numeroEnregistrement,controleTechnique,resultatInspection,
                datePv,remarque,agentPV
        );

        procesVerbal.setNumero(env.getProperty("message.code.genere.numero.pv")
                + System.currentTimeMillis());
        Optional<Formalite> formalite = formaliteRepository.findById(idFormalite);
        procesVerbal.setFormalite(formalite.get());
        Optional<TypePv> optionalTypePv = typePvRepository.findByRef(env.
                getProperty("message.type.pv.ref.cargaison"));

        if(optionalTypePv.isPresent()){
            procesVerbal.setTypePv(optionalTypePv.get());
            procesVerbal.setCreatedAt(new Timestamp(System.currentTimeMillis()));

            return procesVerbalRepository.save(procesVerbal);
        }else{
            throw new IllegalArgumentException(env.getProperty("message.exception.reference"));
        }
    }

    private void saveDetailPvProduit(List<DetPvProduitDto> detPvProduitDtos, ProcesVerbal procesVerbal)  {

        detPvProduitDtos.forEach(data -> {
            DetProcesVerbalProduit detProcesVerbalProduit = new DetProcesVerbalProduit();
         //   Optional<Produit> produit = produitRepository.findByCode(data.getCodProduit());
                detProcesVerbalProduit.setDescriptionEnvoi(data.getDescriptionEnvoie());
                detProcesVerbalProduit.setProcesVerbal(procesVerbal);
                detProcesVerbalProduit.setQuantite(data.getQuantite());
                detProcesVerbalProduit.setMesure(data.getMesure());
                detProcesVerbalProduit.setOrigine(data.getOrigine());
                detProcesVerbalProduit.setEmplacement(data.getEmplacement());
                detPvProduitRepository.save(detProcesVerbalProduit);

        });
    }

    public ResponseEntity<?>updateFormalite(Long idFormalite){
        Optional<Formalite> optionalFormalite = formaliteRepository.findById(idFormalite);
        optionalFormalite.get().setEtat(Etat.TRAITER);
        optionalFormalite.get().setDateTraitement(new Date());
        optionalFormalite.get().setUpdateAt(new Timestamp(System.currentTimeMillis()));
        Formalite formalite= formaliteRepository.save(optionalFormalite.get());
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                env.getProperty("message.succes"),idFormalite), HttpStatus.OK);
    }

    private void saveDetailPvInspecteur(List<DetPVInspecteurDto> detPVInspecteurDtos, ProcesVerbal procesVerbal) {
        detPVInspecteurDtos.forEach(data -> {
            DetProcesVerbalInspecteur detailinspecteur = new DetProcesVerbalInspecteur();
            Optional<Inspecteur> inspecteur = inspecteurRepository.findById(data.getIdInspecteur());
            if(inspecteur.isPresent()){
                detailinspecteur.setInspecteur(inspecteur.get());
                detailinspecteur.setProcesverbal(procesVerbal);
                detailinspecteur.setFonction(data.getFonction());
                detPvInspecteurRepository.save(detailinspecteur);
            }else{
                throw new IllegalArgumentException(env.getProperty("message.not.found.entity"));
            }

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



}


