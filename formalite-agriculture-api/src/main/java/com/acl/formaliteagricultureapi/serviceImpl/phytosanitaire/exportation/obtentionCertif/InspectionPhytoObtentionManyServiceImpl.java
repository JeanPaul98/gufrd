package com.acl.formaliteagricultureapi.serviceImpl.phytosanitaire.exportation.obtentionCertif;

import com.acl.formaliteagricultureapi.converter.ProduitConverter;
import com.acl.formaliteagricultureapi.dto.exports.vegetaux.inspection.InspectionPhytoObtentionCertifListDto;
import com.acl.formaliteagricultureapi.dto.imports.cargaison.PhytosanitaireCargaisonClientListDto;
import com.acl.formaliteagricultureapi.dto.produit.DetPhytosanitaireProduitDto;
import com.acl.formaliteagricultureapi.dto.produit.DetTraitementProduitDto;
import com.acl.formaliteagricultureapi.dto.produit.ProduitDto;
import com.acl.formaliteagricultureapi.models.DetPhytosanitaireProduit;
import com.acl.formaliteagricultureapi.models.DetTraitementProduit;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.DetPhytosanitaireProduitRepository;
import com.acl.formaliteagricultureapi.repository.DetTraitementProduitRepository;
import com.acl.formaliteagricultureapi.repository.FormaliteRepository;
import com.acl.formaliteagricultureapi.requete.PhytosanitaireClientInterface;
import com.acl.formaliteagricultureapi.serviceImpl.phytosanitaire.importation.cargaison.InspectionPhytoCargaisonClientManyServiceImpl;
import com.acl.formaliteagricultureapi.services.phytosanitaire.exportation.obtentionCertif.InspectionPhytoObtentionManyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kol on 02/09/2024
 * @project formalite-agriculture-api
 */
@Service
public class InspectionPhytoObtentionManyServiceImpl implements InspectionPhytoObtentionManyService {

    private final Logger logger= LoggerFactory.getLogger(InspectionPhytoCargaisonClientManyServiceImpl.class);

    private final FormaliteRepository formaliteRepository;

    private final DetPhytosanitaireProduitRepository detPhytosanitaireProduitRepository;

    private final DetTraitementProduitRepository detTraitementProduitRepository;

    private final ProduitConverter produitConverter;

    @Autowired
    private Environment env;

    public InspectionPhytoObtentionManyServiceImpl(FormaliteRepository formaliteRepository, DetPhytosanitaireProduitRepository detPhytosanitaireProduitRepository, DetTraitementProduitRepository detTraitementProduitRepository, ProduitConverter produitConverter) {
        this.formaliteRepository = formaliteRepository;
        this.detPhytosanitaireProduitRepository = detPhytosanitaireProduitRepository;
        this.detTraitementProduitRepository = detTraitementProduitRepository;
        this.produitConverter = produitConverter;
    }


    @Override
    public ResponseEntity<?> listPhytosanitaire(Etat etat, String ref) {
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                getListDemande(etat,ref)), HttpStatus.OK);
    }

    private List<InspectionPhytoObtentionCertifListDto> getListDemande(Etat etat, String ref) {
        List<PhytosanitaireClientInterface> enelevementClientListInterfaces =
                formaliteRepository.findPhytosanitaireClientList(etat.getLabel(), ref);

      //Phytosanitaire produit Dto
        List<DetPhytosanitaireProduitDto> detPhytosanitaireProduitDtos = new ArrayList<>();

        //Traitement de produit
        List<DetTraitementProduitDto> detTraitementProduitDtos = new ArrayList<>();

        List<InspectionPhytoObtentionCertifListDto> phytosanitaireNavireClientListDtos = new ArrayList<>();



        //Parcours la liste De détail Produit
        //Crée le constructeur de la liste
        enelevementClientListInterfaces.forEach(data -> {
            List<DetPhytosanitaireProduit> detail = detPhytosanitaireProduitRepository.findByPhytosanitaire(data.getIdPhytosanitaire());
            detail.forEach(produitDetail -> {

                ProduitDto produitDto = produitConverter.convertDtoToEntity(produitDetail.getProduit());
                DetPhytosanitaireProduitDto dtoProduitDetail = new DetPhytosanitaireProduitDto(produitDto, produitDetail.getQuantite()
                        ,produitDetail.getConteneur(), produitDetail.getFournisseur(), produitDetail.getDescriptionEnvoi(),
                        produitDetail.getPaysEtLieuOrigin(), produitDetail.getNombreColis());

                detPhytosanitaireProduitDtos.add(dtoProduitDetail);
            });

            List<DetTraitementProduit> detailTraitementProduitDtos = detTraitementProduitRepository.findByPhytosanitaire(data.getIdPhytosanitaire());
            detailTraitementProduitDtos.forEach(produitDetail -> {
                DetTraitementProduitDto traitementProduitDto = new DetTraitementProduitDto();
                ProduitDto produitDto = produitConverter.convertDtoToEntity(produitDetail.getProduit());
                traitementProduitDto.setProduit(produitDto);
                traitementProduitDto.setConcentration(produitDetail.getConcentration());
                traitementProduitDto.setTemperature(produitDetail.getTemperature());
                traitementProduitDto.setDuree(produitDetail.getDuree());
                detTraitementProduitDtos.add(traitementProduitDto);
            });
            InspectionPhytoObtentionCertifListDto clientListDto = new InspectionPhytoObtentionCertifListDto(
                    data.getIdFormalite(), data.getAtp(), data.getImo(),data.getEtat(), data.getNumGenerer(),
                    data.getTypePhytosanitaire(), data.getDateSoumission(), data.getDateDemande(), data.getAffreteur(),
                    data.getDateAccepte(), detPhytosanitaireProduitDtos, data.getMontantRedevance(),
                    data.getCompteClient()
            );

            phytosanitaireNavireClientListDtos.add(clientListDto);
        });

        return phytosanitaireNavireClientListDtos;
    }


    @Override
    public ResponseEntity<?> listStatDemande() {
        return null;
    }
}
