package com.acl.vbs.fret.services.impls;

import com.acl.vbs.fret.entities.DmdDeclarationFret;
import com.acl.vbs.fret.exceptions.DmdDeclaratrionFretNotFoundException;
import com.acl.vbs.fret.exceptions.UnauthorizedAccessException;
import com.acl.vbs.fret.repositories.DmdDeclarationFretRepository;
import com.acl.vbs.fret.requests.PaiementRequest;
import com.acl.vbs.fret.responses.MSWUserResponse;
import com.acl.vbs.fret.responses.PaiementResponse;
import com.acl.vbs.fret.services.AuthenticationService;
import com.acl.vbs.fret.services.PaiementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.acl.vbs.fret.utils.AppUtils.generateRandomString;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaiementServiceImpl implements PaiementService {

    private final RestTemplate restTemplate;
    private final DmdDeclarationFretRepository repository;
    private final AuthenticationService authenticationService;

    @Value("${osipaye.url}")
    private String url;

    //    @Override
    public PaiementResponse payement(PaiementRequest request) {
        Map<String, Object> dtoRequest = new HashMap<>();
        dtoRequest.put("numero", request.getDeclaration());
        dtoRequest.put("montant", request.getMontant());
        dtoRequest.put("transactionId", request.getTransactionId());
        dtoRequest.put("client", request.getCompteClient());
        dtoRequest.put("type", request.getType());

        log.info("requete: {}", dtoRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(dtoRequest, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            String responseBody = response.getBody();
            int statusCode = response.getStatusCodeValue();

            ObjectMapper objectMapper = new ObjectMapper();
            PaiementResponse responseModel = objectMapper.readValue(responseBody, PaiementResponse.class);

            log.info("responseBody: {}", responseBody);
            log.info("Send: statusCode {}", statusCode);

            if (statusCode == HttpStatus.OK.value()) {
                return responseModel;
            } else {
                throw new RuntimeException("Error during payment request: " + responseBody);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error during payment request: " + e.getMessage());
        }
    }

    @Override
    public PaiementResponse payementDeclaration(Long declarationId) {
        MSWUserResponse user = authenticationService.getAuthInfo();

        DmdDeclarationFret dmdDeclarationFret = repository.findById(declarationId)
                .orElseThrow(() -> new DmdDeclaratrionFretNotFoundException("Declaration Fret with id " + declarationId + " not found"));

        if (!Objects.equals(user.getGroupe(), "TRANSITAIRES") && !Objects.equals(user.getCompteClient(), dmdDeclarationFret.getDeclarant().getCompteClient())) {
            throw new UnauthorizedAccessException("Accès non autorisé");
        }

        PaiementRequest request = new PaiementRequest();
        request.setMontant(12500);
        request.setType("REDEVANCE");
        request.setCompteClient(user.getCompteClient());
        request.setDeclaration(dmdDeclarationFret.getId());
        request.setTransactionId(generateRandomString(16));
        return payement(request);
    }
}
