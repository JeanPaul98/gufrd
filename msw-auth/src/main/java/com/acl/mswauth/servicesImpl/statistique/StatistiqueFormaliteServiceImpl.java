package com.acl.mswauth.servicesImpl.statistique;

import com.acl.mswauth.dto.formalite.FormaliteStatistiqueDto;
import com.acl.mswauth.interfaces.FormaliteInterface;
import com.acl.mswauth.playload.ApiResponseFormalite;
import com.acl.mswauth.playload.ApiResponseModel;
import com.acl.mswauth.service.httpClient.HttpClientService;
import com.acl.mswauth.service.statistique.StatistiqueFormaliteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author kol on 10/21/24
 * @project msw-auth
 */
@Service
@Slf4j
public class StatistiqueFormaliteServiceImpl implements StatistiqueFormaliteService {

    private final String utf8 = "UTF-8";

    private final WebClient webClient;


    @Autowired
    private HttpClientService httpClientService;


    private final Environment env;

    public StatistiqueFormaliteServiceImpl(WebClient.Builder webClientBuilder, Environment env) {
        this.env = env;
        this.webClient = webClientBuilder.baseUrl(env.getProperty("message.formalite.statistique.url")).build();
    }


    @Override
    public ResponseEntity<?> getFormaliteTraiterPhyto() throws IOException {
        HttpClient httpClient = httpClientService.httpClient();
        String url = this.env.getProperty("message.formalite.phyto.url");

            HttpUriRequest requests = RequestBuilder.get()
                    .setUri(url)
                    .setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                    .build();
            log.info("url: " + requests.getURI().toString());
            HttpResponse response = httpClient.execute(requests);
            int statusCode = response.getStatusLine().getStatusCode();
            String responseBody = EntityUtils.toString(response.getEntity(), utf8);
            ObjectMapper objectMapper = new ObjectMapper();
            log.info("responseBody: {} ", responseBody);
            log.info("Send: statusCode  " + statusCode);
        ApiResponseModel responseModel = objectMapper.readValue(responseBody, ApiResponseModel.class);
            if (statusCode == 200) {
                return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                        env.getProperty("message.application.sucess"), responseModel.getResult()), HttpStatus.OK);

            } else {
                return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                        env.getProperty("message.application.echec")), HttpStatus.OK);
            }

    }

    @Override
    public List<FormaliteStatistiqueDto> getFormaliteStatistique() throws IOException {

        HttpClient httpClient = httpClientService.httpClient();
        String url = this.env.getProperty("message.formalite.phyto.url");

        HttpUriRequest requests = RequestBuilder.get()
                .setUri(url)
                .setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
        log.info("url: " + requests.getURI().toString());
        HttpResponse response = httpClient.execute(requests);
        int statusCode = response.getStatusLine().getStatusCode();
        String responseBody = EntityUtils.toString(response.getEntity(), utf8);
        ObjectMapper objectMapper = new ObjectMapper();

        log.info("Send: statusCode  " + statusCode);

        ApiResponseFormalite responseModel = objectMapper.readValue(responseBody, ApiResponseFormalite.class);

        List<FormaliteStatistiqueDto> formaliteStatistiqueDtos = new ArrayList<>();

        if (statusCode == 200) {

         formaliteStatistiqueDtos = responseModel.getResult();

            return formaliteStatistiqueDtos;
        } else {
          return formaliteStatistiqueDtos;
        }
    }

    @Override
    public ResponseEntity<?> getFormalitePageable(int page, int size) {

        Mono<ApiResponseModel> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(env.getProperty("message.formalite.phyto"))
                        .queryParam("page", page)
                        .queryParam("size", size)
                        .build())
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(ApiResponseModel.class);

        ApiResponseModel responseModel = response.block();
        assert responseModel != null;
        if (responseModel.getStatus().equals("OK")) {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.application.sucess"),
                    responseModel.getResult()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.application.echec"),
                    responseModel), HttpStatus.OK);
        }
    }
}
