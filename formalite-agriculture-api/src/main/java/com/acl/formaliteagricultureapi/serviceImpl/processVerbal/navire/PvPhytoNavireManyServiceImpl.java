package com.acl.formaliteagricultureapi.serviceImpl.processVerbal.navire;

import com.acl.formaliteagricultureapi.converter.ProcessVerbalConverter;
import com.acl.formaliteagricultureapi.converter.ProduitConverter;
import com.acl.formaliteagricultureapi.dto.procesVerbal.DetPVInspecteurDto;
import com.acl.formaliteagricultureapi.dto.procesVerbal.DetPvProduitDto;
import com.acl.formaliteagricultureapi.dto.procesVerbal.navire.PvPhytoSanitaireNavireDto;
import com.acl.formaliteagricultureapi.dto.procesVerbal.navire.PvPhytoSanitaireNavireListDto;
import com.acl.formaliteagricultureapi.dto.produit.ProduitDto;
import com.acl.formaliteagricultureapi.models.DetProcesVerbalProduit;
import com.acl.formaliteagricultureapi.models.DetProcesVerbalInspecteur;
import com.acl.formaliteagricultureapi.models.ProcesVerbal;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.*;
import com.acl.formaliteagricultureapi.services.processVerval.navire.PvPhytoNavireManyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kol on 10/09/2024
 * @project formalite-agriculture-api
 */
@Service
public class PvPhytoNavireManyServiceImpl implements PvPhytoNavireManyService {

    private final FormaliteRepository formaliteRepository;

    private final ProcesVerbalRepository procesVerbalRepository;

    private final DetPvProduitRepository detPvProduitRepository;

    private  final DetPvInspecteurRepository detPvInspecteurRepository;

    private final ProduitRepository produitRepository;

    private final TypePvRepository typePvRepository;

    private final InspecteurRepository inspecteurRepository;

    private final DetCertificatProduitRepository detCertificatProduitRepository;

    @Autowired
    private ProcessVerbalConverter processVerbalConverter;

    @Autowired
    private ProduitConverter produitConverter;

    @Autowired
    private Environment env;

    public PvPhytoNavireManyServiceImpl(FormaliteRepository formaliteRepository, ProcesVerbalRepository procesVerbalRepository, DetPvProduitRepository detPvProduitRepository, DetPvInspecteurRepository detPvInspecteurRepository, ProduitRepository produitRepository, TypePvRepository typePvRepository, InspecteurRepository inspecteurRepository, DetCertificatProduitRepository detCertificatProduitRepository) {
        this.formaliteRepository = formaliteRepository;
        this.procesVerbalRepository = procesVerbalRepository;
        this.detPvProduitRepository = detPvProduitRepository;
        this.detPvInspecteurRepository = detPvInspecteurRepository;
        this.produitRepository = produitRepository;
        this.typePvRepository = typePvRepository;
        this.inspecteurRepository = inspecteurRepository;
        this.detCertificatProduitRepository = detCertificatProduitRepository;
    }


    @Override
    public ResponseEntity<?> listProcessVervale(String ref) {
        List<ProcesVerbal> procesVerbals = procesVerbalRepository.findByTypeRef(ref);
        if(!procesVerbals.isEmpty()) {
            List<PvPhytoSanitaireNavireListDto> pvPhytoSanitaireNavireDtos = new ArrayList<>();

            procesVerbals.forEach(data -> {
                //Information du Pv

                List<DetPvProduitDto> detPvProduitDtoList = new ArrayList<>();

                List<DetProcesVerbalProduit> detProcesVerbalProduits = detPvProduitRepository.findByProcesVerbal(data.getId());

                detProcesVerbalProduits.forEach(produitDetail -> {
                   // ProduitDto produitDto = produitConverter.convertDtoToEntity(produitDetail.getProduit());
                    DetPvProduitDto dtoprod = new DetPvProduitDto(produitDetail.getQuantite(),
                            produitDetail.getEmplacement(), produitDetail.getMesure(), produitDetail.getOrigine(),
                            produitDetail.getMoyenTransport(), produitDetail.getMatiereActive(), produitDetail.getDescriptionEnvoi());
                    detPvProduitDtoList.add(dtoprod);
                });

                //Liste Inspecteur

                List<DetProcesVerbalInspecteur> detPVInspecteurs = detPvInspecteurRepository.findByProcesVerbal(data.getId());
             List<DetPVInspecteurDto> detPVInspecteurDtoList = new ArrayList<>();
                detPVInspecteurs.forEach(insp -> {
                    DetPVInspecteurDto detPVInspecteurDto = new DetPVInspecteurDto(insp.getInspecteur().getId(),
                            insp.getInspecteur().getNomInspecteur()+" "+insp.getInspecteur().getPrenomsInspecteur(),
                            insp.getFonction());
                    detPVInspecteurDtoList.add(detPVInspecteurDto);
                });

                PvPhytoSanitaireNavireListDto pv = processVerbalConverter.convertDtoNavireListToEntity(data);
                pv.setDatearrivee(pv.getDatearrivee());
                pv.setDetPvProduitDtoList(detPvProduitDtoList);
                pv.setDetPVInspecteurDtos(detPVInspecteurDtoList);
                pv.setIdProcessVerbal(data.getId());

                //Information des produits

                pvPhytoSanitaireNavireDtos.add(pv);


            });

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"),pvPhytoSanitaireNavireDtos), HttpStatus.OK);
        }else  {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.list.vide")), HttpStatus.OK);
        }


    }
}
