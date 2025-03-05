package com.acl.vbs.fret.utils;

import com.acl.vbs.fret.models.KeyCloakUserModel;
import com.acl.vbs.fret.responses.HttpResponse;
import com.acl.vbs.fret.responses.SuccessResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AppUtils {
    // Base URL pour les API de l'application
    public static final String API_BASE_URL = "/api/v1";
    // Ensemble de caractères pour générer des chaînes alphanumériques
    public static final String ALPHA_NUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWYZabcdefghijklmnopqrstuvwxyz0123456789";
    // Ensemble de caractères uniquement numériques
    public static final String NUMERIC = "0123456789";
    // Liste des URLs publiques accessibles sans authentification
    public static final String[] PUBLIC_URLS = {
            "/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html"
    };
    // Liste des hôtes autorisés pour certaines opérations (exemple : appels API externes)
    public static final List<String> AUTHORIZE_HOST = List.of("http://83.136.219.161:8000");

    public static final String USER_FOLDER = System.getProperty("user.home") + "/fret/files/";

    // Instance de Random sécurisée pour générer des valeurs aléatoires
    private static final Random RANDOM = new SecureRandom();


    /**
     * Génère un code numérique aléatoire de longueur spécifiée.
     *
     * @param length Longueur du code à générer.
     * @return Une chaîne contenant uniquement des chiffres.
     */
    public static String generateRandomCode(int length) {
        StringBuilder value = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            value.append(NUMERIC.charAt(RANDOM.nextInt(NUMERIC.length())));
        }
        return new String(value);
    }

    /**
     * Génère une chaîne alphanumérique aléatoire de longueur spécifiée.
     *
     * @param length Longueur de la chaîne à générer.
     * @return Une chaîne contenant des lettres et des chiffres.
     */
    public static String generateRandomString(int length) {
        StringBuilder value = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            value.append(ALPHA_NUMERIC.charAt(RANDOM.nextInt(ALPHA_NUMERIC.length())));
        }
        return new String(value);
    }

    public static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex == -1 || dotIndex == fileName.length() - 1) {
            return ""; // No extension found or it's the last character
        } else {
            return fileName.substring(dotIndex + 1);
        }
    }

    /**
     * Crée une réponse standardisée pour les succès HTTP.
     *
     * @param message Message descriptif de la réponse.
     * @param status  Code HTTP de la réponse.
     * @param data    Données à inclure dans la réponse.
     * @param <T>     Type des données incluses.
     * @return Un objet ResponseEntity contenant la réponse formatée.
     */
    public static <T> ResponseEntity<HttpResponse> successResponse(String message, HttpStatusCode status, T data) {
        HttpResponse response = Optional.of(data).map(t -> SuccessResponse.builder().data(t).build()).stream()
                .peek(successResponse -> successResponse.setMessage(message))
                .peek(successResponse -> successResponse.setStatus(status.value()))
                .findFirst().orElseThrow();
        return ResponseEntity.status(status).body(response);
    }

    /**
     * Extrait les rôles liés à une ressource spécifique depuis un JWT Keycloak.
     *
     * @param resourceId Identifiant de la ressource cible.
     * @param jwt        Jeton JWT contenant les informations de rôle.
     * @return Une collection de rôles sous forme de GrantedAuthority.
     */
    @SuppressWarnings("unchecked")
    public static Collection<? extends GrantedAuthority> extractResourceRoles(String resourceId, Jwt jwt) {
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        if (resourceAccess == null || resourceAccess.get(resourceId) == null) {
            return Collections.emptySet();
        }

        Map<String, Object> resource = (Map<String, Object>) resourceAccess.get(resourceId);
        Collection<String> resourceRoles = (Collection<String>) resource.get("roles");

        if (resourceRoles == null) {
            return Collections.emptySet();
        }

        return resourceRoles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toSet());
    }

    /**
     * Extrait les informations utilisateur depuis un JWT Keycloak.
     *
     * @param jwt Jeton JWT contenant les informations utilisateur.
     * @return Un objet KeyCloakUserModel avec les détails de l'utilisateur.
     */
    public static KeyCloakUserModel getKeycloakUser(Jwt jwt) {
        // Extraction des informations utilisateur depuis les claims du JWT
        String email = jwt.getClaim("email").toString();
        String name = jwt.getClaim("name").toString();
        String fullname = jwt.getClaim("family_name").toString();
        return new KeyCloakUserModel(name, fullname, email, name); // Construction du modèle utilisateur
    }

}
