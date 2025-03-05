package com.acl.formaliteagricultureapi.serviceImpl.processVerbal.navire;

import com.acl.formaliteagricultureapi.converter.ProcessVerbalConverter;
import com.acl.formaliteagricultureapi.converter.ProduitConverter;
import com.acl.formaliteagricultureapi.dto.procesVerbal.DetPVInspecteurDto;
import com.acl.formaliteagricultureapi.dto.procesVerbal.DetPvProduitDto;
import com.acl.formaliteagricultureapi.dto.procesVerbal.navire.PvPhytoSanitaireNavireDto;

import com.acl.formaliteagricultureapi.models.*;

import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.*;
import com.acl.formaliteagricultureapi.services.processVerval.navire.PvPhytoNavireService;

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
 * @author kol on 26/08/2024
 * @project formalite-agriculture-api
 */
@Service
public class PvPhytoNavireServiceImpl implements PvPhytoNavireService {

    private final FormaliteRepository formaliteRepository;

    private final ProcesVerbalRepository procesVerbalRepository;

    private final DetPvProduitRepository detPvProduitRepository;

    private  final DetPvInspecteurRepository detPvInspecteurRepository;

    private final ProduitRepository produitRepository;

    private final TypePvRepository typePvRepository;

    private final InspecteurRepository inspecteurRepository;

    @Autowired
    private ProcessVerbalConverter processVerbalConverter;

    @Autowired
    private ProduitConverter produitConverter;

    @Autowired
    private Environment env;
    @Autowired
    private DetCertificatProduitRepository detCertificatProduitRepository;

    public PvPhytoNavireServiceImpl(FormaliteRepository formaliteRepository,
                                    ProcesVerbalRepository procesVerbalRepository,
                                    DetPvProduitRepository detPvProduitRepository,
                                    DetPvInspecteurRepository detPvInspecteurRepository,
                                    ProduitRepository produitRepository,
                                    TypePvRepository typePvRepository,
                                    InspecteurRepository inspecteurRepository) {

        this.formaliteRepository = formaliteRepository;
        this.procesVerbalRepository = procesVerbalRepository;
        this.detPvProduitRepository = detPvProduitRepository;
        this.detPvInspecteurRepository = detPvInspecteurRepository;
        this.produitRepository = produitRepository;
        this.typePvRepository = typePvRepository;
        this.inspecteurRepository = inspecteurRepository;
    }


    @Override
    @Transactional
    public ResponseEntity<?> create(PvPhytoSanitaireNavireDto request) {

        Optional<Formalite> formalite = formaliteRepository.findById(request.getIdFormalite());

        if(formalite.isPresent()){

            if(formalite.get().getPhytoSanitaire()!=null) {

                List<ProcesVerbal> procesVerbals = procesVerbalRepository.findByFormaliteId(formalite.get().getId());

                if(procesVerbals.isEmpty()) {
                    ProcesVerbal procesVerbal = saveProcesVerval(request.getVia(),request.getDateInspection(),request.getDateDepartPrevue()
                            ,request.getPartieNavireVisitee(), request.getOfficierNavire(), request.getLieuInspection(), request.getCommandant(),
                            request.getDatePv(), request.getRemarque(),
                            request.getIdFormalite(), request.getAgentPV());
                    procesVerbal.setDateArrivee(request.getDatearrivee());
                    procesVerbal.setProvenance(request.getProvenance());
                    procesVerbal.setDatePv(request.getDatePv());

                    //Création des inspecteurs  du process verbal
                    saveDetailPvInspecteur(request.getDetPVInspecteurDtos(), procesVerbal);

                    // Création des produits du process verbale
                    saveDetailPvProduit(request.getDetPvProduitDtoList(), procesVerbal);

                    // update Formalite
                    return updateFormalite(request.getIdFormalite());
                } else {
                    return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CONFLICT.name(),
                            env.getProperty("message.existe")), HttpStatus.OK);
                }
            }
                else  {
                    return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                            env.getProperty("message.notfound")), HttpStatus.OK);
                }
            //Controle du type Phytosanitaire

        }
        else
        {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.notfound")), HttpStatus.OK);
        }

    }


    public ResponseEntity<?>updateFormalite(Long idFormalite){

        Optional<Formalite> optionalFormalite = formaliteRepository.findById(idFormalite);
        optionalFormalite.get().setEtat(Etat.TRAITER);
        optionalFormalite.get().setDateTraitement(new Date());
        optionalFormalite.get().setUpdateAt(new Timestamp(System.currentTimeMillis()));
        Formalite formalite= formaliteRepository.save(optionalFormalite.get());
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                env.getProperty("message.succes"), idFormalite), HttpStatus.OK);
    }

    public ProcesVerbal saveProcesVerval(String via, Date dateInspection, Date dateDepartPrevue,
                                              String partieNavireVisitee, String officierNavire,
                                           String lieuInspection, String commandant, Date datePv,
                                         String remarque, Long idFormalite, String agentPV) {

        ProcesVerbal procesVerbal = new ProcesVerbal(via, dateInspection,dateDepartPrevue,
                partieNavireVisitee, commandant,officierNavire,lieuInspection,datePv,
                remarque, agentPV);

        procesVerbal.setNumero(env.getProperty("message.code.genere.numero.pv")
                + System.currentTimeMillis());
        Optional<Formalite> formalite = formaliteRepository.findById(idFormalite);
        procesVerbal.setFormalite(formalite.get());
        Optional<TypePv> optionalTypePv = typePvRepository.findByRef(env.
                getProperty("message.type.pv.ref.navire"));

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

            DetProcesVerbalProduit detProcesVerbalProduit = new DetProcesVerbalProduit(data.getQuantite(),
                   data.getEmplacement(), data.getMesure(), data.getOrigine(),
                    data.getMoyenTransport(), data.getMatiereActive(), data.getDescriptionEnvoie());
            detProcesVerbalProduit.setProcesVerbal(procesVerbal);
                detPvProduitRepository.save(detProcesVerbalProduit);
        });
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




}
