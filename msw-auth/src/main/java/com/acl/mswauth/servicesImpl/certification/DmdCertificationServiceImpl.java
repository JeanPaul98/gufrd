package com.acl.mswauth.servicesImpl.certification;

import com.acl.mswauth.dto.certification.DmdCertificationDto;
import com.acl.mswauth.dto.certification.VerifyDto;
import com.acl.mswauth.model.DemandeCertification;
import com.acl.mswauth.model.enumeration.Statut;
import com.acl.mswauth.model.enumeration.TypeDemande;
import com.acl.mswauth.playload.ApiResponseMessage;
import com.acl.mswauth.repositories.ClientRepository;
import com.acl.mswauth.repositories.DemandeCertificationRepository;
import com.acl.mswauth.service.certification.ClientPalCertificationService;
import com.acl.mswauth.service.certification.DmdCertificationService;
import com.acl.mswauth.service.certification.OpertateurCertificationService;
import com.acl.mswauth.service.utils.UtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/**
 * @author kol on 10/28/24
 * @project msw-auth
 */
@Service
public class DmdCertificationServiceImpl implements DmdCertificationService {

    private Logger logger = LoggerFactory.getLogger(DmdCertificationServiceImpl.class);


    private final ClientPalCertificationService clientPalCertificationService;

    private final DemandeCertificationRepository demandeCertificationRepository;

    private final OpertateurCertificationService opertateurCertificationService;


    @Autowired
    private Environment env;

    public DmdCertificationServiceImpl(ClientPalCertificationService clientPalCertificationService, DemandeCertificationRepository demandeCertificationRepository, OpertateurCertificationService opertateurCertificationService) {
        this.clientPalCertificationService = clientPalCertificationService;
        this.demandeCertificationRepository = demandeCertificationRepository;
        this.opertateurCertificationService = opertateurCertificationService;
    }


    @Override
    public ResponseEntity<?> insert(DmdCertificationDto request) {
        logger.info("Insert certification {}", request);

        return switch (request.getTypeDemande().name()) {
            case "CLIENT_PAL" -> {
                yield clientPalCertificationService.insert(request);
            }
            case "OPERATEUR" -> {
                yield opertateurCertificationService.insert(request);
            }
            default -> new ResponseEntity<>(new ApiResponseMessage(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.typedemande.notfound")), HttpStatus.OK);
        };
    }

    @Override
    public ResponseEntity<?> verify(VerifyDto verifyDto) {

        Optional<DemandeCertification> demandeCertification = demandeCertificationRepository.findByCodeValide(verifyDto.getCode(),
                verifyDto.getNif());
        if(demandeCertification.isPresent()) {

            demandeCertification.get().setStatut(Statut.TERMINE);
            demandeCertification.get().setUpdatedAt(new Date());

            demandeCertificationRepository.save(demandeCertification.get());

            return  new ResponseEntity<>(new ApiResponseMessage(HttpStatus.OK.name(), "Certification verified successfully"), HttpStatus.OK);

        } else  {
            return  new ResponseEntity<>(new ApiResponseMessage(HttpStatus.NOT_FOUND.name(), "Code de vérification éronnée"), HttpStatus.OK);

        }


    }


}
