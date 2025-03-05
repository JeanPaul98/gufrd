package com.acl.formaliteagricultureapi.serviceImpl.general;

import com.acl.formaliteagricultureapi.dto.societe.SocieteDto;
import com.acl.formaliteagricultureapi.models.Societe;
import com.acl.formaliteagricultureapi.models.TypeSociete;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.SocieteRepository;
import com.acl.formaliteagricultureapi.repository.TypeSocieteRepository;
import com.acl.formaliteagricultureapi.services.general.SocieteService;
import com.acl.formaliteagricultureapi.services.httpClient.HttpClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.core.util.Json;
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
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class SocieteServiceImpl implements SocieteService {

    private final SocieteRepository societeRepository;
    private final TypeSocieteRepository typeSocieteRepository;

    private final String utf8 = "UTF-8";

    @Autowired
    private HttpClientService httpClientService;

    Logger logger = LoggerFactory.getLogger(SocieteServiceImpl.class);

    @Autowired
    private Environment env;

    public SocieteServiceImpl(SocieteRepository societeRepository, TypeSocieteRepository typeSocieteRepository) {
        this.societeRepository = societeRepository;
        this.typeSocieteRepository = typeSocieteRepository;
    }

    @Override
    public ResponseEntity<?> listSociete() {
        List<Societe> societe = societeRepository.findAll();
        if(societe.isEmpty()){
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.list.vide"),
                    societeRepository.findAll()), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"), societeRepository.findAll()), HttpStatus.OK);
        }
    }


    @Override
    public ResponseEntity<?> addSociete(SocieteDto societeDto) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        Optional<Societe> optionalSociete = societeRepository.findByNif(societeDto.getNif());
        if (optionalSociete.isPresent()) {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    "La sociéte est déja créer"), HttpStatus.OK);
        }else{
            Societe societe = new Societe();
            societe.setNif(societeDto.getNif());
            societe.setRaisonSociale(societeDto.getRaisonSociale());
            societe.setContact(societeDto.getContact());

            societe.setFormeJuridique(societeDto.getFormeJuridique());
            societe.setNumRccm(societeDto.getNumRccm());

            societe.setAdresse(societeDto.getAdresse());
            societe.setEmail(societeDto.getEmail());
            societe.setNumeroEnreg(societeDto.getNumeroEnreg());
            Optional<TypeSociete> typeSociete = typeSocieteRepository.findById(societeDto.getTypeSociete());
            typeSociete.ifPresent(societe::setTypeSociete);
            Societe savedSociete = societeRepository.save(societe);

            logger.info("Societe {}",societeDto);

            HttpClient httpClient = httpClientService.httpClient();


            String requestDto = "{\n"  +
                            "\" numeroEnreg\":" + "\""+ societeDto.getNumeroEnreg() + "\" " + ",\n   " +
                                    " \"nif\":" + "\"" +societeDto.getNif()  +"\" " + ",\n  " +
                    "  \"raisonSociale\":" +  "\"" +societeDto.getRaisonSociale()+ "\" "+ ",\n  " +
                                    "  \"formeJuridique\":" + "\"" + societeDto.getFormeJuridique() + "\"" + ",\n  " +
                    "  \"numRccm\":" +  "\"" +  societeDto.getNumRccm() + "\""  +",\n   " +
                                    " \"adresse\":" + "\""+societeDto.getAdresse()  + "\"" + ",\n   " +
                    " \"contact\":" + "\"" +societeDto.getContact() + "\"" + ",\n    " +
                                    "\"email\":" + "\"" + societeDto.getEmail() + "\""  +"\n  " +
                    "\n}";

            logger.info("La requete {}",requestDto);


            HttpUriRequest requests = RequestBuilder.post()
                    .setUri(env.getProperty("app.url.api.societe.create"))
                    .setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                    .setEntity(new StringEntity(requestDto, utf8))
                    .build();

            HttpResponse response = httpClient.execute(requests);

            // Get the http status code
            int statusCode = response.getStatusLine().getStatusCode();

            logger.info("Statut {}", statusCode);

            String responseBody = EntityUtils.toString(response.getEntity(), env.getProperty("app.utf8"));

            logger.info("RESPONSE  {}", responseBody);

            ObjectMapper objectMapper = new ObjectMapper();
            ApiResponseModel    apiResponseModel = objectMapper.readValue(responseBody, ApiResponseModel.class);
            if(statusCode==201 || statusCode==200){
                return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                        "Creation effectuée avec succès", apiResponseModel),
                        HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_ACCEPTABLE.name(),
                        "Opération échouée"), HttpStatus.OK);
            }
        }
    }

    @Override
    public ResponseEntity<?> updateSociete(SocieteDto societeDto, long id) {
        Optional<Societe> optionalSociete = societeRepository.findById(id);
        if (optionalSociete.isEmpty()) {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.societe.notFound")), HttpStatus.NOT_FOUND);
        }
        Societe societe = optionalSociete.get();
        societe.setNif(societeDto.getNif());
        societe.setRaisonSociale(societeDto.getRaisonSociale());
        societe.setContact(societeDto.getContact());
        societe.setFormeJuridique(societeDto.getFormeJuridique());
        societe.setNumRccm(societeDto.getNumRccm());
        societe.setAdresse(societeDto.getAdresse());
        societe.setEmail(societeDto.getEmail());
        societe.setNumeroEnreg(societeDto.getNumeroEnreg());
        Optional<TypeSociete> typeSociete = typeSocieteRepository.findById(societeDto.getTypeSociete());
        typeSociete.ifPresent(societe::setTypeSociete);

        Societe updatedSociete = societeRepository.save(societe);
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                env.getProperty("message.update.success"), updatedSociete), HttpStatus.OK);
    }
}
