package com.acl.formalitesanteapi.serviceimpl.certificat;

import com.acl.formalitesanteapi.dto.certificat.RenouvelementCertificatDto;
import com.acl.formalitesanteapi.models.Certificat;
import com.acl.formalitesanteapi.models.Formalite;
import com.acl.formalitesanteapi.models.Structure;
import com.acl.formalitesanteapi.models.TypeCertificat;
import com.acl.formalitesanteapi.models.enumeration.Chaine;
import com.acl.formalitesanteapi.models.enumeration.Etat;
import com.acl.formalitesanteapi.playload.ApiResponseModel;
import com.acl.formalitesanteapi.repository.CertificatRepository;
import com.acl.formalitesanteapi.repository.FormaliteRepository;
import com.acl.formalitesanteapi.repository.StructureRepository;
import com.acl.formalitesanteapi.repository.TypeCertificatRepository;
import com.acl.formalitesanteapi.services.certificat.renouvelement.RenouvelementCerficatService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

/**
 * @author kol on 08/09/2024
 * @project formalite-sante-api
 */
@Service
public class RenouvelementCertificatServiceImpl implements RenouvelementCerficatService {

private final FormaliteRepository formaliteRepository;

private final StructureRepository structureRepository;

private final CertificatRepository certificatRepository;

private final TypeCertificatRepository typeCertificatRepository;

@Autowired
private Environment env;

    public RenouvelementCertificatServiceImpl(FormaliteRepository formaliteRepository, StructureRepository structureRepository, CertificatRepository certificatRepository, TypeCertificatRepository typeCertificatRepository) {
        this.formaliteRepository = formaliteRepository;
        this.structureRepository = structureRepository;
        this.certificatRepository = certificatRepository;
        this.typeCertificatRepository = typeCertificatRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<?> create(RenouvelementCertificatDto request) {

        //Enregistrement Autorisation
        Certificat certificat = saveCertificat(request.getNuemroCerficatRenouveler());

        //Enregistrement de la formalite
        return saveFormalite(request.getAtp(), request.getCompteClient(), request.getImo(), request.getNomNavire(),
                request.getAffreteur(), certificat);
    }

    private Certificat saveCertificat(String nuemroCerficatRenouveler ) {
        Certificat certificat = new Certificat(nuemroCerficatRenouveler);
        Optional<TypeCertificat> typeCertificat = typeCertificatRepository.findByReference(env.getProperty("message.type.certificat.ref.sante"));
        if (typeCertificat.isPresent()) {
            certificat.setTypeCertificat(typeCertificat.get());
        }else  {
            throw new IllegalArgumentException(env.getProperty("message.exception.reference"));
        }
        certificat.setCreatedAt(new Date());
        certificatRepository.save(certificat);
        return certificat;
    }


    private ResponseEntity<?> saveFormalite(String atp, String compteClient, String imo, String nomNavire,
                                            String affreteur,
                                            Certificat certificat) {

        Formalite formalite = new Formalite(atp,compteClient, nomNavire, imo, affreteur);
        formalite.setChaine(Chaine.Import);
        formalite.setNumGenere("AUTCONS" + System.currentTimeMillis());
        formalite.setCertificat(certificat);
        Optional<Structure> optionalStructure = structureRepository.findByCode(env.getProperty("message.structure.code"));
        if (!optionalStructure.isPresent()) {
            throw new IllegalArgumentException(env.getProperty("message.exception.reference"));
        }
        formalite.setStructure(optionalStructure.get());
        formalite.setEtat(Etat.NON_SOUMIS);
        formalite.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        formalite.setDateDmd(new Timestamp(System.currentTimeMillis()));
        Formalite saveFormalite = formaliteRepository.save(formalite);
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                env.getProperty("message.succes"), saveFormalite.getId()), HttpStatus.OK);

    }
}
