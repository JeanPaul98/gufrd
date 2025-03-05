package com.acl.vbs.services.impls;

import com.acl.vbs.responses.MSWHttpClientResponse;
import com.acl.vbs.responses.MSWUserResponse;
import com.acl.vbs.services.AuthenticationService;
import com.acl.vbs.services.KeycloakTokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final KeycloakTokenService keycloakTokenService;

    @Override
    public MSWUserResponse getAuthInfo() {
        ObjectMapper objectMapper = new ObjectMapper();
        String principal = SecurityContextHolder.getContext().getAuthentication().getName();
        String accessToken = keycloakTokenService.getKeycloakToken();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        String url = "http://102.164.230.196:9001/api/vbs/profil?email=" + principal;

        ResponseEntity<MSWHttpClientResponse> response = restTemplate.exchange(url, GET, request, MSWHttpClientResponse.class);

        MSWHttpClientResponse body = Objects.requireNonNull(response.getBody());

        if (body.getResult() != null) {
            return objectMapper.convertValue(body.getResult(), MSWUserResponse.class);
        } else throw new RuntimeException("Impossible de déterminer l'utilisateur.");
    }
}
