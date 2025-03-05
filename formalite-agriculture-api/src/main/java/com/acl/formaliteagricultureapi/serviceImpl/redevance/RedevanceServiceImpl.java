package com.acl.formaliteagricultureapi.serviceImpl.redevance;

import com.acl.formaliteagricultureapi.dto.formalite.UpdateFormaliteDto;
import com.acl.formaliteagricultureapi.dto.redevance.CallbackDto;
import com.acl.formaliteagricultureapi.dto.redevance.PaiementDto;
import com.acl.formaliteagricultureapi.dto.redevance.RedevanceDto;
import com.acl.formaliteagricultureapi.models.Formalite;
import com.acl.formaliteagricultureapi.models.Redevance;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.models.enumeration.StatutPaiement;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.playload.OsipayResponseModel;
import com.acl.formaliteagricultureapi.repository.FormaliteRepository;
import com.acl.formaliteagricultureapi.repository.RedevanceRepository;
import com.acl.formaliteagricultureapi.serviceImpl.veterinaire.animauxvivant.AutorisationExpAnimauxManyServiceImp;
import com.acl.formaliteagricultureapi.services.httpClient.HttpClientService;
import com.acl.formaliteagricultureapi.services.redevance.RedevanceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


/**
 * @author kol on 05/09/2024
 * @project formalite-agriculture-api
 */
@Service
public class RedevanceServiceImpl implements RedevanceService {

    private final Logger logger= LoggerFactory.getLogger(RedevanceServiceImpl.class);

    private final FormaliteRepository formaliteRepository;

    private final RedevanceRepository redevanceRepository;

    private final String utf8 = "UTF-8";

    @Autowired
    private HttpClientService httpClientService;

    @Autowired
    private Environment env;

    public RedevanceServiceImpl(FormaliteRepository formaliteRepository, RedevanceRepository redevanceRepository) {
        this.formaliteRepository = formaliteRepository;
        this.redevanceRepository = redevanceRepository;
    }

    @SneakyThrows
    @Override
    public ResponseEntity<?> paye(UpdateFormaliteDto request) {

        HttpClient httpClient = httpClientService.httpClient();
        String url = this.env.getProperty("message.osipaye.url");
        String type = "REDEVANCE";
        String transactionId = "TR"+new Date().getTime();

        logger.info("transactionId: {}", transactionId);

        Optional<Formalite> formalite = formaliteRepository.findById(request.getIdFormalite());

         if(formalite.isPresent()) {
            String dtoRequest = "{\n"
                    + "    \"numero\": " + "\" " + formalite.get().getNumGenere() + "\" " + ",\n"
                    + "     \"montant\":" + formalite.get().getMontantRedevance() + ",\n"
                    + "     \"client\": " + "\"" + formalite.get().getCompteClient() + "\",\n"
                    + "     \"transactionId\": " + "\"" + transactionId + "\",\n"
                    + "      \"type\": " + "\"" + type + "\" \n"
                    + "}";

            formalite.get().setStatutPaiement(StatutPaiement.PAYER);


            formaliteRepository.save(formalite.get());

            HttpUriRequest requests = RequestBuilder.post()
                    .setUri(url)
                    .setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                    .setEntity(new StringEntity(dtoRequest, utf8))
                    .build();
            logger.info("url: " + requests.getURI().toString());
            HttpResponse response = httpClient.execute(requests);
            int statusCode = response.getStatusLine().getStatusCode();
            String responseBody = EntityUtils.toString(response.getEntity(), utf8);
            ObjectMapper objectMapper = new ObjectMapper();

            OsipayResponseModel responseModel = objectMapper.readValue(responseBody, OsipayResponseModel.class);

            logger.info("responseBody: {} ", responseBody);
            logger.info("Send: statusCode  " + statusCode);
            if (statusCode == 200) {
                return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                        env.getProperty("message.succes"), responseModel), HttpStatus.OK);

            } else {
                return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                        env.getProperty("message.echec")), HttpStatus.OK);
            }

        } else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.exception.reference")), HttpStatus.OK);
        }


    }


    @Override
    public ResponseEntity<?> callbackAtd(CallbackDto request) {

            Redevance redevance = new Redevance(request.getReference(), request.getMontant());
            redevance.setDateDemande(new Date());
            redevance.setStatus(true);
            redevanceRepository.save(redevance);

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"), request), HttpStatus.OK);
        }



    @Override
    public ResponseEntity<?> callback(CallbackDto request) {
        Optional<Formalite> formalite = formaliteRepository.findByNumGenerer(request.getNumero());

        if (formalite.isPresent()) {

            Redevance redevance = new Redevance(formalite.get(), request.getReference(), request.getMontant());
            redevance.setDateDemande(new Date());
            redevance.setStatus(true);
            redevanceRepository.save(redevance);
            formalite.get().setStatutPaiement(StatutPaiement.PAYER);
            formalite.get().setDateAccepte(new Date());
            formalite.get().setUpdateAt(new Date());
            formaliteRepository.save(formalite.get());
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"), request), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.exception.reference")), HttpStatus.OK);
        }

    }

    @SneakyThrows
    @Override
    public ResponseEntity<?> payement(PaiementDto request) {
        HttpClient httpClient = httpClientService.httpClient();
        String url = this.env.getProperty("message.osipaye.url");
        String type = "REDEVANCE";

        logger.info("numeroDossier: {} ", request.getNumeroDossier() );


            String dtoRequest = "{\n"
                    + "    \"numero\": " + "\" " + request.getNumeroDossier() + "\" " + ",\n"
                    + "     \"montant\":" + request.getMontant() + ",\n"
                    + "     \"transactionId\":" + "\""+request.getTransactionId() + "\",\n"
                    + "     \"client\": " + "\"" + request.getCompteClient() + "\",\n"
                    + "      \"type\": " + "\"" + type + "\" \n"
                    + "}";
        logger.info("requete {}", dtoRequest);
            HttpUriRequest requests = RequestBuilder.post()
                    .setUri(url)
                    .setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                    .setEntity(new StringEntity(dtoRequest, utf8))
                    .build();
            logger.info("url: " + requests.getURI().toString());
            HttpResponse response = httpClient.execute(requests);
            int statusCode = response.getStatusLine().getStatusCode();
            String responseBody = EntityUtils.toString(response.getEntity(), utf8);
            ObjectMapper objectMapper = new ObjectMapper();

            OsipayResponseModel responseModel = objectMapper.readValue(responseBody, OsipayResponseModel.class);

            logger.info("responseBody: {} ", responseBody);
            logger.info("Send: statusCode  " + statusCode);
            if (statusCode == 200) {
                return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                        env.getProperty("message.succes"), responseModel), HttpStatus.OK);

            } else {
                return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                        env.getProperty("message.echec")), HttpStatus.OK);
            }

    }
}
