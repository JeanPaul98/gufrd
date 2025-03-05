package com.acl.formaliteagricultureapi.serviceImpl.processVerbal.cargo;

import com.acl.formaliteagricultureapi.converter.InspecteurConverter;
import com.acl.formaliteagricultureapi.converter.ProcessVerbalConverter;
import com.acl.formaliteagricultureapi.converter.ProduitConverter;
import com.acl.formaliteagricultureapi.dto.procesVerbal.DetPVInspecteurDto;
import com.acl.formaliteagricultureapi.dto.procesVerbal.DetPvProduitDto;
import com.acl.formaliteagricultureapi.dto.procesVerbal.PvPhytoSanitaireCargConteneurDto;
import com.acl.formaliteagricultureapi.dto.produit.ProduitDto;
import com.acl.formaliteagricultureapi.models.DetProcesVerbalProduit;
import com.acl.formaliteagricultureapi.models.DetProcesVerbalInspecteur;
import com.acl.formaliteagricultureapi.models.ProcesVerbal;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.*;
import com.acl.formaliteagricultureapi.services.processVerval.cargaison.PvPhytoCargaisonManyService;
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
public class PvPhytoCargaisonManyServiceImpl implements PvPhytoCargaisonManyService {

    private final ProcesVerbalRepository procesVerbalRepository;

    private final DetPvProduitRepository detPvProduitRepository;

    private  final DetPvInspecteurRepository detPvInspecteurRepository;

    @Autowired
    private ProcessVerbalConverter processVerbalConverter;

    @Autowired
    private ProduitConverter produitConverter;

    @Autowired
    private InspecteurConverter inspecteurConverter;

    @Autowired
    private Environment env;

    public PvPhytoCargaisonManyServiceImpl(ProcesVerbalRepository procesVerbalRepository, DetPvProduitRepository detPvProduitRepository, DetPvInspecteurRepository detPvInspecteurRepository) {
        this.procesVerbalRepository = procesVerbalRepository;
        this.detPvProduitRepository = detPvProduitRepository;
        this.detPvInspecteurRepository = detPvInspecteurRepository;
    }


    @Override
    public ResponseEntity<?> listProcessVervale(String ref) {
        List<ProcesVerbal> procesVerbals = procesVerbalRepository.findByTypeRef(ref);
        if(!procesVerbals.isEmpty()) {
            List<PvPhytoSanitaireCargConteneurDto> pvCargaison = new ArrayList<>();

            procesVerbals.forEach(data -> {
                //Information du Pv
                List<DetPvProduitDto> detPvProduitDtoList = new ArrayList<>();

                List<DetProcesVerbalProduit> detProcesVerbalProduits = detPvProduitRepository.findByProcesVerbal(data.getId());

                detProcesVerbalProduits.forEach(produitDetail -> {
                    ProduitDto produitDto = produitConverter.convertDtoToEntity(produitDetail.getProduit());
                    DetPvProduitDto dtoprod = new DetPvProduitDto(produitDto.getLibelle(), produitDetail.getQuantite(),
                            produitDetail.getEmplacement(), produitDetail.getMesure(), produitDetail.getOrigine(),
                            produitDetail.getMoyenTransport(), produitDetail.getMatiereActive());
                    detPvProduitDtoList.add(dtoprod);
                });


                //Liste Inspecteur


                List<DetPVInspecteurDto> detPVInspecteurDtos = new ArrayList<>();
                List<DetProcesVerbalInspecteur> detInspecteurs = detPvInspecteurRepository.findByProcesVerbal(data.getId());

                detInspecteurs.forEach(insp -> {
                    DetPVInspecteurDto detPVInspecteurDto = new DetPVInspecteurDto(insp.getInspecteur().getId(),
                            insp.getInspecteur().getNomInspecteur()+" "+insp.getInspecteur().getPrenomsInspecteur(),
                            insp.getFonction());

                    detPVInspecteurDtos.add(detPVInspecteurDto);
                });


                PvPhytoSanitaireCargConteneurDto pv = processVerbalConverter.convertDtoCargaisonToEntity(data);
                pv.setDestinataire(data.getNomPrenomsDestinataire());
                pv.setExpediteur(data.getNomPrenomsExpediteur());
                pv.setControleTechnique(data.getControleTech());
                pv.setDateInspection(data.getDateInspection());
                pv.setDatearrivee(pv.getDatearrivee());
                pv.setIdFormalite(data.getFormalite().getId());

                pv.setDetPvProduitDtos(detPvProduitDtoList);
                pv.setDetPVInspecteurDtos(detPVInspecteurDtos);

                //Information des produits

                pvCargaison.add(pv);


            });

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"),pvCargaison), HttpStatus.OK);
        }else  {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.list.vide")), HttpStatus.OK);
        }


    }
}
