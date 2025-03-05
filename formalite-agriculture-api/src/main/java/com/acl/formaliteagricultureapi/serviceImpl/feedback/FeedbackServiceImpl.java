package com.acl.formaliteagricultureapi.serviceImpl.feedback;

import com.acl.formaliteagricultureapi.converter.FeedBackConverter;
import com.acl.formaliteagricultureapi.dto.atd.AtdAuthModelDto;
import com.acl.formaliteagricultureapi.dto.atd.ResponseFeedBackDto;
import com.acl.formaliteagricultureapi.dto.feedback.FeedBackDto;
import com.acl.formaliteagricultureapi.dto.feedback.FeedBackUpdateDto;
import com.acl.formaliteagricultureapi.dto.formalite.FeedBackFormaliteDto;
import com.acl.formaliteagricultureapi.models.FeedBackPublic;
import com.acl.formaliteagricultureapi.models.Formalite;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.playload.OsipayResponseModel;
import com.acl.formaliteagricultureapi.repository.FeedBackRepository;
import com.acl.formaliteagricultureapi.repository.FormaliteRepository;
import com.acl.formaliteagricultureapi.serviceImpl.autorisation.demande.animauxVivant.AutorisationAnimauxVivantClientCRUDServiceImpl;
import com.acl.formaliteagricultureapi.services.feedback.FeedbackAuthService;
import com.acl.formaliteagricultureapi.services.feedback.FeedbackService;
import com.acl.formaliteagricultureapi.services.httpClient.HttpClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

/**
 * @author kol on 10/6/24
 * @project formalite-agriculture-api
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final Logger logger= LoggerFactory.getLogger(FeedbackServiceImpl.class);

    private final FeedbackAuthService feedbackAuthService;


    private final FeedBackRepository feedBackRepository;

    private final FormaliteRepository formaliteRepository;

    private final HttpClientService httpClientService;

    private final String utf8 = "UTF-8";

    @Autowired
    private FeedBackConverter feedBackConverter;

    @Autowired
    private Environment env;

    public FeedbackServiceImpl(FeedbackAuthService feedbackAuthService, FeedBackRepository feedBackRepository, FormaliteRepository formaliteRepository, HttpClientService httpClientService) {
        this.feedbackAuthService = feedbackAuthService;
        this.feedBackRepository = feedBackRepository;
        this.formaliteRepository = formaliteRepository;
        this.httpClientService = httpClientService;
    }

    @Override
    public ResponseEntity<?> create(FeedBackDto request) {
        Optional<Formalite> formalite = formaliteRepository.findById(request.getIdFormalite());

        if (formalite.isPresent()) {
            FeedBackPublic feedBackPublic = feedBackConverter.convertDToEntity(request);
            feedBackPublic.setFormalite(formalite.get());
            feedBackPublic.setCreatedAt(new Date());
            feedBackRepository.save(feedBackPublic);

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),env.getProperty("message.succes"),
                    request),HttpStatus.OK);
        }else  {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),env.getProperty("message.not.found.entity"),
                    request.getIdFormalite()),HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<?> sendFeedBack(FeedBackFormaliteDto request, String etat) throws IOException {

        HttpClient httpClient = httpClientService.httpClient();
        String url = this.env.getProperty("message.atd.feedback.url");

        String dtoRequest = "{\n"
                + "    \"title\": " + "\"" + request.getTitle() + "\" " + ",\n"
                + "    \"message\": " + "\"" + request.getMessage() + "\" " + ",\n"
                + "    \"process\": " + "\"" + request.getProcess() + "\" " + ",\n"
                + "    \"record\": " + "\"" + request.getRecord() + "\" " + ",\n"
                + "    \"step\": " + "\"" + request.getStep() + "\" " + ",\n"
                + "    \"order\": " + request.getOrder() + ",\n"
                + "    \"feedbackTaskId\": " + "\"" + request.getFeedbackTaskId() + "\" " + ",\n"
                + "    \"data\": {\n"
                        + "\"statusPaiement\": " +"\"" + request.getFormaliteUpdateFeedDto().getStatutPaiement() + "\" " + ",\n"
                          + "\"etat\": " + "\"" + etat+ "\" " + ",\n"
                        + "\"motif\": " + "\"" + request.getMotifRejet()+ "\" " + ",\n"
                + "\"montant\": " +  request.getFormaliteUpdateFeedDto().getMontantRedevance() +
                            "\n} \n"
                + "}";

        logger.info(dtoRequest);

        HttpUriRequest requests = RequestBuilder.post()
                .setUri(url)
                .setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .setHeader("x-token", feedbackAuthService.generateTokens())
                .setEntity(new StringEntity(dtoRequest, utf8))
                .build();
        HttpResponse response = httpClient.execute(requests);
        int statusCode = response.getStatusLine().getStatusCode();

        String responseBody = EntityUtils.toString(response.getEntity(), utf8);
        logger.info("responseBody: {} ", responseBody);
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseFeedBackDto responseModel = objectMapper.readValue(responseBody, ResponseFeedBackDto.class);
        logger.info("Send: statusCode  " + statusCode);
        if (statusCode == 200) {
        return new  ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                env.getProperty("message.succes"),responseModel),HttpStatus.OK);
        } else {
            return new  ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.echec"),responseModel),HttpStatus.OK);
        }

    }

    @Override
    public boolean feedBackTraitement(FeedBackFormaliteDto request, String etat) throws IOException {
        HttpClient httpClient = httpClientService.httpClient();
        String url = this.env.getProperty("message.atd.feedback.url");

        String dtoRequest = "{\n"
                + "    \"title\": " + "\"" + request.getTitle() + "\" " + ",\n"
                + "    \"message\": " + "\"" + request.getMessage() + "\" " + ",\n"
                + "    \"process\": " + "\"" + request.getProcess() + "\" " + ",\n"
                + "    \"record\": " + "\"" + request.getRecord() + "\" " + ",\n"
                + "    \"step\": " + "\"" + request.getStep() + "\" " + ",\n"
                + "    \"order\": " + request.getOrder() + ",\n"
                + "    \"feedbackTaskId\": " + "\"" + request.getFeedbackTaskId() + "\" " + ",\n"
                + "    \"data\": {\n"
                + "\"statusPaiement\": " +"\"" + request.getFormaliteUpdateFeedDto().getStatutPaiement() + "\" " + ",\n"
                + "\"etat\": " + "\"" + etat+ "\" " + ",\n"
                + "\"piece\": " + "\"" + request.getPiece()+ "\" " + ",\n"
                + "\"montant\": " +  request.getFormaliteUpdateFeedDto().getMontantRedevance() +
                "\n} \n"
                + "}";

        logger.info(dtoRequest);

        HttpUriRequest requests = RequestBuilder.post()
                .setUri(url)
                .setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .setHeader("x-token", feedbackAuthService.generateTokens())
                .setEntity(new StringEntity(dtoRequest, utf8))
                .build();

        HttpResponse response = httpClient.execute(requests);
        int statusCode = response.getStatusLine().getStatusCode();
        String responseBody = EntityUtils.toString(response.getEntity(), utf8);
        logger.info("responseBody: {} ", responseBody);
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseFeedBackDto responseModel = objectMapper.readValue(responseBody, ResponseFeedBackDto.class);
        logger.info("Send: statusCode  " + statusCode);
        if (statusCode == 200) {
          return true;
        } else {
            return false;
        }
    }

    @Override
    public ResponseEntity<?> update(FeedBackUpdateDto request) {
        Optional<FeedBackPublic> feedBackPublic = feedBackRepository.findByRecord(request.getRecord());

        if (feedBackPublic.isPresent()) {
            feedBackPublic.get().setUpdatedAt(new Date());
            feedBackPublic.get().setProcess(request.getProcess());
            feedBackPublic.get().setStep(request.getStep());
            feedBackPublic.get().setOrder(request.getOrder());
            feedBackPublic.get().setFeedbackTaskId(request.getFeedbackTaskId());
            feedBackPublic.get().setDescription(request.getDescription());
            feedBackRepository.save(feedBackPublic.get());

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),env.getProperty("message.succes"),
                    request),HttpStatus.OK);
        }else  {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),env.getProperty("message.not.found.entity"),
                    request.getRecord()),HttpStatus.OK);
        }
    }

}
