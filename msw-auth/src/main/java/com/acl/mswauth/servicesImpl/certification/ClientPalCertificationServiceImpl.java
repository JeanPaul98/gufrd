package com.acl.mswauth.servicesImpl.certification;

import com.acl.mswauth.constants.Constants;
import com.acl.mswauth.dto.certification.DmdCertificationDto;
import com.acl.mswauth.dto.mail.MailInfoDto;
import com.acl.mswauth.model.DemandeCertification;
import com.acl.mswauth.model.MswClient;
import com.acl.mswauth.model.enumeration.Statut;
import com.acl.mswauth.playload.ApiResponseModel;
import com.acl.mswauth.repositories.ClientRepository;
import com.acl.mswauth.repositories.DemandeCertificationRepository;
import com.acl.mswauth.service.certification.ClientPalCertificationService;
import com.acl.mswauth.service.client.ClientService;
import com.acl.mswauth.service.email.EmailService;
import com.acl.mswauth.service.utils.UtilService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author kol on 10/28/24
 * @project msw-auth
 */
@Service
public class ClientPalCertificationServiceImpl implements ClientPalCertificationService {

    private Logger logger = LoggerFactory.getLogger(ClientPalCertificationServiceImpl.class);



    private final UtilService utilService;

    private final ClientRepository clientRepository;

    private final DemandeCertificationRepository demandeCertificationRepository;

    @Autowired
    private Environment env;

    private final EmailService emailService;

    public ClientPalCertificationServiceImpl(UtilService utilService, ClientRepository clientRepository, DemandeCertificationRepository demandeCertificationRepository, EmailService emailService) {
        this.utilService = utilService;
        this.clientRepository = clientRepository;
        this.demandeCertificationRepository = demandeCertificationRepository;
        this.emailService = emailService;
    }

    @Override
    @Transactional
    public ResponseEntity<?> insert(DmdCertificationDto request) {
        Optional<MswClient> clients = clientRepository.findClientByNif(request.getNif());

        if(clients.isPresent()) {
            DemandeCertification demandCertification = new DemandeCertification(utilService.generateUUID()
                   ,request.getNif(), request.getEmail(), request.getRaisonSocial());
            demandCertification.setRowUuid(utilService.generateUUID());
            demandCertification.setTypeDemande(request.getTypeDemande());
            demandCertification.setCode(utilService.randomCode());
            demandCertification.setDelaiExpirationCode(utilService.ajoutMinute(15));
            demandCertification.setCreatedAt(new Date());
            demandCertification.setStatut(Statut.EN_ATTENTE);

            logger.info("Request to save client {}", demandCertification);

            MailInfoDto mailInfoDto = new MailInfoDto(request.getEmail(), "CODE VERIFICATION",
                  sendCodeTemplate(demandCertification.getCode(), clients.get().getNomClient()) );

            emailService.sendEmail(mailInfoDto);

            demandeCertificationRepository.save(demandCertification);

           return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.application.sucess"),
                   demandCertification ), HttpStatus.CREATED);
        } else {
            DemandeCertification demandCertification = new DemandeCertification(utilService.generateUUID()
                    ,request.getNif(), request.getEmail(), request.getRaisonSocial());
            demandCertification.setRowUuid(utilService.generateUUID());
            demandCertification.setTypeDemande(request.getTypeDemande());
            demandCertification.setCode(utilService.randomCode());
            demandCertification.setCreatedAt(new Date());
            demandCertification.setStatut(Statut.NON_TROUVE);
            logger.info("Request to save client {}", demandCertification);

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.application.echec"),
                    demandCertification ), HttpStatus.OK);
        }


    }

    private String sendCodeTemplate(String code, String entreprise) {

        String textMessage =  """
                    <!DOCTYPE html>
                    <html>
                    <head>
                        <title>[CERTIFICATION] Verification Code</title>
                        <style>
                            body {
                                font-family: Arial, sans-serif;
                                background-color: #f4f4f4;
                                text-align: center;
                                margin: 0;
                                padding: 0;
                            }

                            .container {
                                max-width: 600px;
                                margin: 0 auto;
                                padding: 20px;
                                background-color: #fff;
                                border-radius: 5px;
                                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                            }

                            h1 {
                                color: #333;
                            }

                            p {
                                color: #666;
                            }

                            .button-container {
                                margin: 20px 0;
                            }

                            .button {
                                display: inline-block;
                                padding: 10px 20px;
                                background-color: #007BFF;
                                color: #fff;
                                text-decoration: none;
                                border-radius: 5px;
                                transition: background-color 0.3s ease;
                            }

                            .button:hover {
                                background-color: #0056b3;
                            }
                        </style>
                    </head>
                    <body>
                        <div class="container">
                            <h1>Certification Verification Code</h1>
                           ENTREPRISE : """+ entreprise +"""
                            <p>Votre  code  de certification:</p>
                         """+code+"""
                        
                          <p>Le code de vérification est valide pour 30 min.</p>
                        </div>
                    </body>
                    </html>
                    """;

        return textMessage;
    }


}
