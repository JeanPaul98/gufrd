package com.acl.formaliteagricultureapi.serviceImpl.phytosanitaire.exportation.certificat.etablissement;

import com.acl.formaliteagricultureapi.dto.exports.vegetaux.certificat.DmdCerticatPhytoDto;
import com.acl.formaliteagricultureapi.models.*;
import com.acl.formaliteagricultureapi.models.enumeration.Chaine;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.*;
import com.acl.formaliteagricultureapi.services.phytosanitaire.exportation.certificat.etablissement.DmdCertificatPhytoCDService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

/**
 * @author kol on 03/09/2024
 * @project formalite-agriculture-api
 */
@Service
public class DmdCertificatPhytoCDServiceImpl implements DmdCertificatPhytoCDService {

    private final Logger logger = LoggerFactory.getLogger(DmdCertificatPhytoCDServiceImpl.class);

    private final  CertificatRepository certificatRepository;

    private final FormaliteRepository formaliteRepository;

    private final TypeCertificatRepository typeCertificatRepository;


    private final StructureRepository structureRepository;

    @Autowired
    private Environment env;

    public DmdCertificatPhytoCDServiceImpl(CertificatRepository certificatRepository, FormaliteRepository formaliteRepository, TypeCertificatRepository typeCertificatRepository, StructureRepository structureRepository) {
        this.certificatRepository = certificatRepository;
        this.formaliteRepository = formaliteRepository;
        this.typeCertificatRepository = typeCertificatRepository;
        this.structureRepository = structureRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<?> create(DmdCerticatPhytoDto request) {

        Certificat certificat = saveCertificat(request.getNomDemandeur(), request.getAdresseDemandeur());

        return saveFormalite(request.getCompteClient(), certificat);
    }

    private Certificat saveCertificat(String nomDemandeur , String adresseDemandeur ) {
        Certificat certificats = new Certificat();
        certificats.setNomDemandeur(nomDemandeur);
        certificats.setAdresseDemandeur(adresseDemandeur);
        Optional<TypeCertificat> optionalTypeCertificat = typeCertificatRepository.
                findByRef(env.getProperty("message.type.certificat.ref.phytosanitaire"));
        if (optionalTypeCertificat.isPresent()) {

            certificats.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        }else {
            throw new IllegalArgumentException(env.getProperty("message.exception.reference"));
        }

        return certificatRepository.save(certificats);
    }

    private ResponseEntity<?> saveFormalite(String compteClient, Certificat certificat) {
        Formalite formalite = new Formalite();
        formalite.setCompteClient(compteClient);
        formalite.setChaine(Chaine.Export);
        formalite.setNumGenere("DMDCERTI" + System.currentTimeMillis());
        formalite.setCertificat(certificat);
        Optional<Structure> optionalStructure = structureRepository.findByCode(env.getProperty("message.structure.vegetaux"));
        if (!optionalStructure.isPresent()) {
            throw new IllegalArgumentException(env.getProperty("message.exception.reference"));
        }
        formalite.setStructure(optionalStructure.get());
        formalite.setEtat(Etat.NON_SOUMIS);
        formalite.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        formalite.setDateDmd(new Timestamp(System.currentTimeMillis()));
        Formalite saveFormalite = formaliteRepository.save(formalite);
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                env.getProperty("message.succes"),saveFormalite.getId()), HttpStatus.OK);
    }



}
