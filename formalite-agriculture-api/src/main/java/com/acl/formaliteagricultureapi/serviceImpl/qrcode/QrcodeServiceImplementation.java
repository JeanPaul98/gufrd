package com.acl.formaliteagricultureapi.serviceImpl.qrcode;

import com.acl.formaliteagricultureapi.dto.atd.QrCodeDto;
import com.acl.formaliteagricultureapi.dto.atd.ResponseFeedBackDto;
import com.acl.formaliteagricultureapi.dto.atd.ResponseQrCodeDto;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.serviceImpl.feedback.FeedbackAuthServiceImpl;
import com.acl.formaliteagricultureapi.services.feedback.FeedbackAuthService;
import com.acl.formaliteagricultureapi.services.httpClient.HttpClientService;
import com.acl.formaliteagricultureapi.services.qrcode.QrcodeService;
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
public class QrcodeServiceImplementation  implements QrcodeService {

    private final Logger logger= LoggerFactory.getLogger(QrcodeServiceImplementation.class);

    private final String utf8 = "UTF-8";

    @Autowired
    private HttpClientService httpClientService;

    @Autowired
    private Environment env;

    private final FeedbackAuthService feedbackAuthService;

    public QrcodeServiceImplementation(FeedbackAuthService feedbackAuthService) {
        this.feedbackAuthService = feedbackAuthService;
    }

    @Override
    public String generateQrCode(QrCodeDto request) throws IOException {

        HttpClient httpClient = httpClientService.httpClient();
        String url = this.env.getProperty("message.atd.qrcode.url");

        String dtoRequest = "{\n"
               + "    \"slug\": " + "\"" + request.getSlug() + "\" " + ",\n"
                + "    \"payload\": {\n"
                + "\"refDocument\": " +"\"" + request.getRefDocument() + "\" " + ",\n"
                + "\"typeDocument\": " + "\"" + request.getTypeDocument()+ "\" " + ",\n"
                + "\"modeUtilisation\": " + "\"" + request.getModeUtilisation()+ "\" " + ",\n"
                + "\"autoriteCompetente\": " + "\"" + request.getAutoriteCompetente()+ "\" " + ",\n"
                + "\"nomSociete\": " + "\"" + request.getNomSociete()+ "\" " + ",\n"
                + "\"nif\": " + "\"" + request.getNif()+ "\" " + ",\n"
                + "\"cat\": " + "\"" + request.getCat()+ "\" " + ",\n"
                + "\"exp\": " + "\"" + request.getExp()+ "\" " + ",\n"
                + "\"rat\": " + "\"" + request.getRat()+ "\" " +
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
        ResponseQrCodeDto responseModel = objectMapper.readValue(responseBody, ResponseQrCodeDto.class);
        logger.info("Send: statusCode  " + statusCode);
        if (statusCode == 200) {
          return responseModel.getLink();
        } else {
         return "";
        }
    }
}
