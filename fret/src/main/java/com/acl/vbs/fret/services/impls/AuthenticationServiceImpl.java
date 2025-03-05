package com.acl.vbs.fret.services.impls;

import com.acl.vbs.fret.converters.AppConverter;
import com.acl.vbs.fret.exceptions.ChargeurNotFoundException;
import com.acl.vbs.fret.repositories.ChargeurRepository;
import com.acl.vbs.fret.repositories.ParametrageRepository;
import com.acl.vbs.fret.responses.ChargeurResponse;
import com.acl.vbs.fret.responses.MSWHttpClientResponse;
import com.acl.vbs.fret.responses.MSWUserResponse;
import com.acl.vbs.fret.responses.ParametrageResponse;
import com.acl.vbs.fret.services.AuthenticationService;
import com.acl.vbs.fret.services.KeycloakTokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static org.springframework.http.HttpMethod.GET;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final RestTemplate restTemplate;
    private final ChargeurRepository chargeurRepository;
    private final KeycloakTokenService keycloakTokenService;
    private final ParametrageRepository parametrageRepository;

    @Value("${msw.host}")
    private String authHost;

    @Override
    public MSWUserResponse getAuthInfo() {
        ObjectMapper objectMapper = new ObjectMapper();
        String principal = SecurityContextHolder.getContext().getAuthentication().getName();
        String accessToken = keycloakTokenService.getKeycloakToken();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        String url = authHost + "vbs/profil?email=" + principal;

        ResponseEntity<MSWHttpClientResponse> response = restTemplate.exchange(url, GET, request, MSWHttpClientResponse.class);

        MSWHttpClientResponse body = Objects.requireNonNull(response.getBody());

        if (body.getResult() != null) {
            return objectMapper.convertValue(body.getResult(), MSWUserResponse.class);
        } else throw new RuntimeException("Impossible de déterminer l'utilisateur.");
    }

    @Override
    public MSWUserResponse getAuthChargeurInfo() {
        MSWUserResponse user = getAuthInfo();

        ChargeurResponse chargeur = chargeurRepository.findByCompteClient(user.getCompteClient())
                .map(AppConverter::toChargeurResponse)
                .orElseThrow(() -> new ChargeurNotFoundException("Chargeur with compte " + user.getCompteClient() + " not found"));
        user.setChargeur(chargeur);

        if (chargeur != null) {
            ParametrageResponse parametrage = parametrageRepository.findByChargeurId(chargeur.getId())
                    .map(AppConverter::toParametrageResponse)
                    .orElse(null);
            user.setParametrage(parametrage);
        }
        return user;
    }
}
