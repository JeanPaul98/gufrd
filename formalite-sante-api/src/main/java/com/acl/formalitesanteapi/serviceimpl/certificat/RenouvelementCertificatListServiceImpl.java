package com.acl.formalitesanteapi.serviceimpl.certificat;

import com.acl.formalitesanteapi.dto.certificat.RenouvelementCertificatListDto;
import com.acl.formalitesanteapi.models.enumeration.Etat;
import com.acl.formalitesanteapi.playload.ApiResponseModel;
import com.acl.formalitesanteapi.repository.FormaliteRepository;
import com.acl.formalitesanteapi.requette.CertificatInterfaces;
import com.acl.formalitesanteapi.services.certificat.renouvelement.RenouvelementCertificatListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kol on 09/09/2024
 * @project formalite-sante-api
 */
@Service
public class RenouvelementCertificatListServiceImpl implements RenouvelementCertificatListService {

    private final FormaliteRepository formaliteRepository;

    @Autowired
    private Environment env;

    public RenouvelementCertificatListServiceImpl(FormaliteRepository formaliteRepository) {
        this.formaliteRepository = formaliteRepository;
    }


    @Override
    public ResponseEntity<?> listCertificat(Etat etat, String ref) {
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),
                getListCertificat(etat.getLabel(),ref)), HttpStatus.OK) ;
    }

    private List<RenouvelementCertificatListDto> getListCertificat(String etat , String ref) {

        List<RenouvelementCertificatListDto> listCertificat = new ArrayList<>();

        List<CertificatInterfaces> certificatInterfaces = formaliteRepository.findCertificat(etat,ref);

        certificatInterfaces.forEach(cert -> {
            RenouvelementCertificatListDto data = new RenouvelementCertificatListDto();
            data.setAffreteur(cert.getAffreteur());
            data.setAtp(cert.getAtp());
            data.setIdCertificat(cert.getIdCertificat());
            data.setChaine(cert.getChaine());
            data.setEtat(cert.getEtat());
            data.setCompteClient(cert.getCompteClient());
            data.setImo(cert.getImmo());
            data.setDateDemande(cert.getDateDemande());
            data.setIdFormalite(cert.getIdFormalite());
            data.setNomNavire(cert.getNomNavire());
            data.setNuemroCerficatRenouveler(cert.getNumeroEnregistrement());
            data.setDateTraitement(cert.getDateTraitement());
            data.setDateAccepte(cert.getDateAccepte());
            data.setDateSoumission(cert.getDateSoumission());
            data.setNumGenerer(cert.getNumGenerer());
            data.setMontantRedevance(cert.getMontantRedevance());

            listCertificat.add(data);
        });

        return listCertificat;

    }
}
