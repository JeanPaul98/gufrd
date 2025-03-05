package com.acl.formaliteagricultureapi.serviceImpl.autorisation.depotage;

import com.acl.formaliteagricultureapi.dto.piecejointe.PieceJointeFormaliteDto;
import com.acl.formaliteagricultureapi.dto.produit.DetAutorisationProduitDto;
import com.acl.formaliteagricultureapi.exception.DocumentNotFoundException;
import com.acl.formaliteagricultureapi.models.*;
import com.acl.formaliteagricultureapi.models.enumeration.StatutPaiement;
import com.acl.formaliteagricultureapi.repository.*;
import com.acl.formaliteagricultureapi.serviceImpl.autorisation.enlevement.AutorisationAlimentAnimauxClientCDServiceImpl;
import com.acl.formaliteagricultureapi.dto.imports.depotage.AutorisationDepotageTransitClientDto;
import com.acl.formaliteagricultureapi.models.enumeration.Chaine;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.services.autorisation.depotage.AutorisationDepotageTransitCRUDService;
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
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author kol on 20/08/2024
 * @project formalite-agriculture-api
 */
@Service
public class AutorisationDepotageTransitCRUDServiceImpl implements AutorisationDepotageTransitCRUDService {

    private final Logger logger= LoggerFactory.getLogger(AutorisationAlimentAnimauxClientCDServiceImpl.class);

    private final AutorisationRepository autorisationRepository;

    private final FormaliteRepository formaliteRepository;

    private final TypeAutorisationRepository typeAutorisationRepository;

    private final StructureRepository structureRepository;

    private final DetAutorisationProduitRepository detAutorisationProduitRepository;

    private final ProduitRepository produitRepository;

    private final TypePieceJointeRepository typePieceJointeRepository;

    private final  PieceJointeRepository pieceJointeRepository;

    @Autowired
    private UtilServices utilServices;

    @Autowired
    private Environment env;

    public AutorisationDepotageTransitCRUDServiceImpl(AutorisationRepository autorisationRepository,
                                                      FormaliteRepository formaliteRepository,
                                                      TypeAutorisationRepository typeAutorisationRepository,
                                                      StructureRepository structureRepository,
                                                      DetAutorisationProduitRepository detAutorisationProduitRepository,
                                                      ProduitRepository produitRepository, TypePieceJointeRepository typePieceJointeRepository,
                                                      PieceJointeRepository pieceJointeRepository) {
        this.autorisationRepository = autorisationRepository;
        this.formaliteRepository = formaliteRepository;
        this.typeAutorisationRepository = typeAutorisationRepository;
        this.structureRepository = structureRepository;
        this.detAutorisationProduitRepository = detAutorisationProduitRepository;
        this.produitRepository = produitRepository;
        this.typePieceJointeRepository = typePieceJointeRepository;
        this.pieceJointeRepository = pieceJointeRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<?> create(AutorisationDepotageTransitClientDto request) {
        //Enregistrement Autorisation

        for (DetAutorisationProduitDto detAutorisationProduitDto : request.getDetAutorisationProduitDtos()) {
            Optional<Produit> optionalProduit = produitRepository.findByCode(
                    detAutorisationProduitDto.getProduit().getCode());
            if (!optionalProduit.isPresent()) {
                return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                        env.getProperty("message.produit.notfound")), HttpStatus.OK);
            }

        }

        Autorisation autorisation= saveAutorisation(request.getDatearrivee(), request.getNumeroBL(), request.getNumeroAutorisation());

        //Creation de détail produit
      double redevance = saveDetAutorisationProduit(request.getDetAutorisationProduitDtos(),
                autorisation);

        //Enregistrement de la formalite
        return saveFormalite(request.getAtp(), request.getNomNavire(),
                request.getImo(),request.getAffreteur(),request.getCompteClient(), autorisation,
                request.getNomImportateur(), redevance, request.getPieceJointeFormaliteDtos(),request.getNif(),request.getNumeroDossier());
    }


    public double saveDetAutorisationProduit(List<DetAutorisationProduitDto> detAutorisationProduitDtos,
                                           Autorisation autorisation) {
        // Montant redevance
        AtomicReference<Double> montantTotal = new AtomicReference<>((double) 0);

        detAutorisationProduitDtos.forEach(data -> {

            Optional<Produit> produit = produitRepository.findByCode(data.getProduit().getCode());

            DetAutorisationProduit detProduit = new DetAutorisationProduit(produit.get(), data.getQuantite(),
                    data.getProvenance(), data.getNombreCarton(),
                    data.getUnite(), data.getOrigine(), autorisation,data.getPoidNets(),data.getConteneur());

            montantTotal.set(montantTotal.get() + detProduit.getProduit().getTypeProduit().getPrixUnitaire() *
                    data.getPoidNets());

            detAutorisationProduitRepository.save(detProduit);
        });
        return montantTotal.get();
    }


    public ResponseEntity<?>saveFormalite(String atp,String nomNavire,String immo,String affreteur,
                                          String compteClient,Autorisation autorisation,
                                          String nomImportateur, double redevance,
                                          List<PieceJointeFormaliteDto> pieceJointeFormaliteDtos, String nif,String numeroDossier){

        Formalite formalite= new Formalite(atp, nomNavire, immo, affreteur,compteClient, nomImportateur);
        formalite.setAutorisation(autorisation);
        formalite.setStatutPaiement(StatutPaiement.IMPAYER);
        formalite.setChaine(Chaine.Import);
        formalite.setMontantRedevance(redevance);
        formalite.setNumGenere(utilServices.generateNumDemande(env.getProperty("message.code.genere.numero.autorisation")));
        formalite.setAutorisation(autorisation);
        Optional<Structure> optionalStructure= structureRepository.findByCode(env.getProperty("message.structure.code"));
        if(!optionalStructure.isPresent()){
            throw new DocumentNotFoundException(env.getProperty("message.exception.reference"));
        }

        formalite.setStructure(optionalStructure.get());
        formalite.setEtat(Etat.NON_SOUMIS);
        formalite.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        formalite.setDateDmd(new Timestamp(System.currentTimeMillis()));
        formalite.setNif(nif);
        formalite.setNumeroDossier(numeroDossier);
        Formalite saveFormalite= formaliteRepository.save(formalite);

        //savePiecejointe(pieceJointeFormaliteDtos, saveFormalite);

        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                env.getProperty("message.succes"), saveFormalite.getId()), HttpStatus.OK);
    }

    public Autorisation saveAutorisation(Date datearrivee, String numeroBL, String numeroAutorisation){
        Autorisation autorisation=new Autorisation();
        autorisation.setDatearrivee(datearrivee);
        autorisation.setNumeroBL(numeroBL);
        autorisation.setNumroAutorisation(numeroAutorisation);
        Optional<TypeAutorisation>optionalTypeAutorisation= typeAutorisationRepository.
                findByRef(env.getProperty("message.type.autorisation.ref.depotage"));
        if(!optionalTypeAutorisation.isPresent()){
            throw new DocumentNotFoundException(env.getProperty("message.exception.reference"));
        }
        autorisation.setTypeAutorisation(optionalTypeAutorisation.get());
        return autorisationRepository.save(autorisation);
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



}
