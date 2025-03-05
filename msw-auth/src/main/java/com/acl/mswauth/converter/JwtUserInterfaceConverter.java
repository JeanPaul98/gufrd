package com.acl.mswauth.converter;

import com.acl.mswauth.security.model.KeyCloakUser;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author kol on 10/5/24
 * @project msw-auth
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUserInterfaceConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    @Value("${jwt.auth.client.epal.resource-id}")
    private String resourceId;

    @Value("${jwt.auth.principle-attribute}")
    private String principleAttribute;


    @Override
    public AbstractAuthenticationToken convert(@Nonnull Jwt jwt) {
        log.debug("Converting JWT to AuthenticationToken for subject: {}", jwt.getSubject());

        // Extract authorities
        Collection<GrantedAuthority> authorities = Stream
                .concat(jwtGrantedAuthoritiesConverter.convert(jwt).stream(), extractResourceRoles(jwt).stream())
                .collect(Collectors.toSet());

        log.debug("Granted authorities extracted: {}", authorities);

        // Return the authentication token
        return new JwtAuthenticationToken(jwt, authorities, getPrincipalClaimName(jwt));
    }

//    @Override
//    public AbstractAuthenticationToken convert(@Nonnull Jwt jwt) {
//        Collection<GrantedAuthority> authorities = Stream
//                .concat(jwtGrantedAuthoritiesConverter.convert(jwt).stream(), extractResourceRoles(jwt).stream())
//                .collect(Collectors.toSet());
//        return new JwtAuthenticationToken(jwt, authorities, getPrincipalClaimName(jwt));
//    }

    private String getPrincipalClaimName(Jwt jwt) {
        String claimName = JwtClaimNames.SUB;

        if (principleAttribute != null && !principleAttribute.isEmpty()) {
            log.debug("Using custom principle attribute: {}", principleAttribute);
            claimName = principleAttribute;
        }

        String principal = jwt.getClaim(claimName);
        log.debug("Principal claim ({}): {}", claimName, principal);

        // Default to email claim if available
        if (principal == null) {
            log.warn("Principal claim not found, falling back to email claim.");
            principal = jwt.getClaim("email");
        }

        return principal;
    }

//    private String getPrincipaleClaimName(Jwt jwt) {
//        String claimName = JwtClaimNames.SUB;
//
//        if (principeAtribute != null) {
//            claimName = principeAtribute;
//        }
//        return jwt.getClaim("email");
//    }

    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
        log.debug("Extracting resource roles from JWT.");

        getKeycloakUser(jwt);

        if (jwt.getClaim("resource_access") == null) {
            log.warn("No resource_access claim found in JWT.");
            return Set.of();
        }

        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        log.debug("Resource access claim found: {}", resourceAccess);

        if (!resourceAccess.containsKey(resourceId)) {
            log.warn("No resource found for resourceId: {}", resourceId);
            return Set.of();
        }

        Map<String, Object> resource = (Map<String, Object>) resourceAccess.get(resourceId);
        Collection<String> resourceRoles = (Collection<String>) resource.get("roles");

        log.debug("Roles for resource {}: {}", resourceId, resourceRoles);

        return resourceRoles.stream()
                .map(role -> {
                    log.debug("Mapping role: {}", role);
                    return new SimpleGrantedAuthority("ROLE_" + role);
                })
                .collect(Collectors.toSet());
    }

//    private Collection<? extends GrantedAuthority> extractRessourceRole(Jwt jwt) {
//        Map<String, Object> resourceAccess;
//        Map<String, Object> resource;
//        Collection<String> resourceRoles;
//        if (jwt.getClaim("resource_access") == null) {
//            return Set.of();
//        }
//        getKeycloakUser(jwt);
//        resourceAccess = jwt.getClaim("resource_access");
//        if (resourceAccess.get(resourceId) == null) {
//            return Set.of();
//        }
//        resource = (Map<String, Object>) resourceAccess.get(resourceId);
//        resourceRoles = (Collection<String>) resource.get("roles");
//        return resourceRoles
//                .stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role)
//                ).collect(Collectors.toSet());
//
//    }

    private void getKeycloakUser(Jwt jwt) {
        String email = jwt.getClaim("email");
        String name = jwt.getClaim("name");
        String fullName = jwt.getClaim("family_name");

        log.info("Keycloak User Details - Email: {}, Name: {}, Full Name: {}", email, name, fullName);

        KeyCloakUser user = new KeyCloakUser(name, fullName, email, name);
        log.info("Created KeyCloakUser: {}", user);
    }

//    private void getKeycloakUser(Jwt jwt) {
//        String email = jwt.getClaim("email").toString();
//        String name = jwt.getClaim("name").toString();
//        String fullname = jwt.getClaim("family_name").toString();
//        log.info("Infos utilisateur , {} , {}", email, name);
//        KeyCloakUser user = new KeyCloakUser(name, fullname, email, name);
//        log.info("keycloakUser , {}", user);
//    }
}
