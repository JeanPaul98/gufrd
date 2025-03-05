package com.acl.formaliteagricultureapi.serviceImpl.feedback;

import com.acl.formaliteagricultureapi.dto.atd.AtdAuthModelDto;
import com.acl.formaliteagricultureapi.services.feedback.FeedbackAuthService;
import com.acl.formaliteagricultureapi.services.httpClient.HttpClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HttpService;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author kol on 10/7/24
 * @project formalite-agriculture-api
 */
@Service
public class FeedbackAuthServiceImpl implements FeedbackAuthService {

    private final Logger logger= LoggerFactory.getLogger(FeedbackAuthServiceImpl.class);

    private final String utf8 = "UTF-8";

    @Autowired
    private HttpClientService  httpClientService;

    @Autowired
    private Environment env;

    public String generateTokens() throws IOException {
        HttpClient httpClient = httpClientService.httpClient();
        String url = this.env.getProperty("message.atd.auth.url");

        String dtoRequest = "{\n"
                + "    \"email\": " + "\"" + env.getProperty("message.atd.auth.email") + "\" " + ",\n"
                + "      \"password\": " + "\"" + env.getProperty("message.atd.auth.password") + "\" \n"
                + "}";

        logger.info(dtoRequest);

        HttpUriRequest requests = RequestBuilder.post()
                .setUri(url)
                .setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .setEntity(new StringEntity(dtoRequest, utf8))
                .build();

        HttpResponse response = httpClient.execute(requests);
        int statusCode = response.getStatusLine().getStatusCode();

        String responseBody = EntityUtils.toString(response.getEntity(), utf8);
        logger.info("responseBody: {} ", responseBody);

        ObjectMapper objectMapper = new ObjectMapper();
        AtdAuthModelDto responseModel = objectMapper.readValue(responseBody, AtdAuthModelDto.class);

        logger.info("Send: statusCode  " + statusCode);
        if (statusCode == 200) {
            return responseModel.getData().getToken();
        } else {
            return  "";
        }
    }
}
