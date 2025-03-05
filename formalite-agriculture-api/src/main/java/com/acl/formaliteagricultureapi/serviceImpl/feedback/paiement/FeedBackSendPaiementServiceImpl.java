package com.acl.formaliteagricultureapi.serviceImpl.feedback.paiement;

import com.acl.formaliteagricultureapi.dto.atd.ResponseFeedBackDto;
import com.acl.formaliteagricultureapi.dto.formalite.FeedBackFormaliteDto;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.FeedBackRepository;
import com.acl.formaliteagricultureapi.repository.FeedbackSrvPaiementRepository;
import com.acl.formaliteagricultureapi.repository.FormaliteRepository;
import com.acl.formaliteagricultureapi.serviceImpl.feedback.FeedbackServiceImpl;
import com.acl.formaliteagricultureapi.services.feedback.FeedbackAuthService;
import com.acl.formaliteagricultureapi.services.feedbackPaiement.FeedbackPaiementSendService;
import com.acl.formaliteagricultureapi.services.httpClient.HttpClientService;
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

/**
 * @author kol on 10/7/24
 * @project formalite-agriculture-api
 */
@Service
public class FeedBackSendPaiementServiceImpl implements FeedbackPaiementSendService {


    private final Logger logger= LoggerFactory.getLogger(FeedbackServiceImpl.class);


    private final FeedbackSrvPaiementRepository feedbackSrvPaiementRepository;

    private final FormaliteRepository formaliteRepository;

    @Autowired
    private  HttpClientService httpClientService;

    private final FeedbackAuthService feedbackAuthService;

    private final String utf8 = "UTF-8";

    @Autowired
    private  Environment env;


    public FeedBackSendPaiementServiceImpl(FeedbackSrvPaiementRepository feedbackSrvPaiementRepository, FormaliteRepository formaliteRepository,
                                           FeedbackAuthService feedbackAuthService) {
        this.feedbackSrvPaiementRepository = feedbackSrvPaiementRepository;
        this.formaliteRepository = formaliteRepository;
        this.feedbackAuthService = feedbackAuthService;
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
                + "\"piece\": " + "\"" + request.getPiece()+ "\" " + ",\n"
                + "\"transactionId\": " + "\"" + request.getTransactionId()+ "\" " + ",\n"
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
        logger.info("url: " + requests.getURI().toString());
        HttpResponse response = httpClient.execute(requests);
        int statusCode = response.getStatusLine().getStatusCode();
        logger.info("code {}",response.getStatusLine().getStatusCode() );
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
}
