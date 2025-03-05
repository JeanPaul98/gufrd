package com.acl.vbs.services.impls;

import com.acl.vbs.entities.BonEntreeCamion;
import com.acl.vbs.exceptions.BonEntreeCamionNotFoundException;
import com.acl.vbs.exceptions.UnauthorizedAccessException;
import com.acl.vbs.repositories.BonEntreeCamionRepository;
import com.acl.vbs.requests.PaiementRequest;
import com.acl.vbs.responses.BonEntreeCamionRedevenceResponse;
import com.acl.vbs.responses.MSWUserResponse;
import com.acl.vbs.responses.PaiementResponse;
import com.acl.vbs.services.AuthenticationService;
import com.acl.vbs.services.BonEntreeCamionService;
import com.acl.vbs.services.PaiementService;
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

import static com.acl.vbs.utils.AppUtils.generateRandomString;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaiementServiceImpl implements PaiementService {

    private final RestTemplate restTemplate;
    private final BonEntreeCamionRepository repository;
    private final AuthenticationService authenticationService;
    private final BonEntreeCamionService bonEntreeCamionService;

    @Value("${osipaye.url}")
    private String url;

    //    @Override
    public PaiementResponse payement(PaiementRequest request) {
        Map<String, Object> dtoRequest = new HashMap<>();
        dtoRequest.put("type", request.getType());
        dtoRequest.put("montant", request.getMontant());
        dtoRequest.put("numero", request.getNumBonEntree());
        dtoRequest.put("client", request.getCompteClient());
        dtoRequest.put("transactionId", request.getTransactionId());

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
    public PaiementResponse payementBonEntree(String numBonEntreeCamion) {
        MSWUserResponse user = authenticationService.getAuthInfo();

        BonEntreeCamion dmdDeclarationFret = repository.findByNumBonEntreeCamion(numBonEntreeCamion)
                .orElseThrow(() -> new BonEntreeCamionNotFoundException("Bon d'entrée camion with num " + numBonEntreeCamion + " not found"));

        if (!Objects.equals(user.getGroupe(), "TRANSITAIRES") && !Objects.equals(user.getCompteClient(), dmdDeclarationFret.getTransitaire().getClient().getCompteClient())) {
            throw new UnauthorizedAccessException("Accès non autorisé");
        }

        PaiementRequest request = new PaiementRequest();
        BonEntreeCamionRedevenceResponse redevance = bonEntreeCamionService.getRedevance(numBonEntreeCamion);

        request.setType("REDEVANCE");
        request.setMontant(redevance.getRedevance());
        request.setCompteClient(user.getCompteClient());
        request.setTransactionId(generateRandomString(16));
        request.setNumBonEntree(dmdDeclarationFret.getNumBonEntreeCamion());
        return payement(request);
    }
}
