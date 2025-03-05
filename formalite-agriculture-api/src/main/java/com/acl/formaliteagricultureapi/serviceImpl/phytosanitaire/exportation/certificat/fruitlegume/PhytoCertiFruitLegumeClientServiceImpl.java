package com.acl.formaliteagricultureapi.serviceImpl.phytosanitaire.exportation.certificat.fruitlegume;

import com.acl.formaliteagricultureapi.dto.exports.etablissementcertificat.PhytoCertifFruitLegumeClientDto;
import com.acl.formaliteagricultureapi.dto.imports.navire.PhytosanitaireNavireClientListDto;
import com.acl.formaliteagricultureapi.dto.produit.DetCertificatProduitDto;
import com.acl.formaliteagricultureapi.models.Certificat;
import com.acl.formaliteagricultureapi.models.Produit;
import com.acl.formaliteagricultureapi.models.TypeInspPhyto;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.*;
import com.acl.formaliteagricultureapi.services.phytosanitaire.exportation.certificat.fruitlegume.PhytoCerficatFruitLegumeClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

/**
 * @author kol on 27/08/2024
 * @project formalite-agriculture-api
 */
public class PhytoCertiFruitLegumeClientServiceImpl implements PhytoCerficatFruitLegumeClientService {

    private final Logger logger = LoggerFactory.getLogger(PhytoCertiFruitLegumeClientServiceImpl.class);

    private final PhytosanitaireRepository phytosanitaireRepository;

    private final FormaliteRepository formaliteRepository;

    private final DetPhytosanitaireProduitRepository detPhytosanitaireProduitRepository;

    private final ProduitRepository produitRepository;

    private final TypePhytosanitaireRepository typePhytosanitaireRepository;

    private final StructureRepository structureRepository;

    private final DetTraitementProduitRepository detTraitementProduitRepository;

    private final  SocieteRepository societeRepository;

    private Environment env;

    public PhytoCertiFruitLegumeClientServiceImpl(PhytosanitaireRepository phytosanitaireRepository, FormaliteRepository formaliteRepository, DetPhytosanitaireProduitRepository detPhytosanitaireProduitRepository, ProduitRepository produitRepository, TypePhytosanitaireRepository typePhytosanitaireRepository, StructureRepository structureRepository, DetTraitementProduitRepository detTraitementProduitRepository, SocieteRepository societeRepository) {
        this.phytosanitaireRepository = phytosanitaireRepository;
        this.formaliteRepository = formaliteRepository;
        this.detPhytosanitaireProduitRepository = detPhytosanitaireProduitRepository;
        this.produitRepository = produitRepository;
        this.typePhytosanitaireRepository = typePhytosanitaireRepository;
        this.structureRepository = structureRepository;
        this.detTraitementProduitRepository = detTraitementProduitRepository;
        this.societeRepository = societeRepository;
    }


    @Override
    public ResponseEntity<?> create(PhytoCertifFruitLegumeClientDto request) {

        for (DetCertificatProduitDto detDto : request.getDetCertificatProduitDtos()) {
            Optional<Produit> optionalProduit = produitRepository.findByCode(
                    detDto.getProduit().getCode());
            if (!optionalProduit.isPresent()) {
                return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                        env.getProperty("message.notfound")), HttpStatus.OK);
            }
        }

        Certificat certificat = saveCertificat(request.getLieuExpedition(),
                 request.getNomExpediteur(),
                 request.getNumeroAgrement(), request.getMoyenTransport(), request.getPaysLieuDestination(),
                request.getNomDestinataire(), request.getAdresseDestinataire(),
                request.getRemarqueParticuliere()
        );
        return null;
    }

    private Certificat saveCertificat(String lieuExpedition, String nomExpediteur, String adresseDestinaire,
                               String numeroAgrement, String moyenTransport,
                                  String paysLieuDestination, String remarqueParticuliere, String nomDestinataire) {



        Optional<TypeInspPhyto> optionalTypeAutorisation = typePhytosanitaireRepository.
                findByRef(env.getProperty("message.type.phytosanitaire.ref.obtencertif"));
        if (!optionalTypeAutorisation.isPresent()) {
            throw new IllegalArgumentException(env.getProperty("message.exception.reference"));
        }


        return null;
    }




    @Override
    public ResponseEntity<?> validerDemande(PhytosanitaireNavireClientListDto request) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<?> cancel(PhytosanitaireNavireClientListDto request) {
        return null;
    }
}
