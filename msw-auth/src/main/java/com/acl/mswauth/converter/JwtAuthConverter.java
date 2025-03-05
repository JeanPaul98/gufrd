package com.acl.mswauth.converter;

import com.acl.mswauth.security.model.KeyCloakUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private final Logger logger = LoggerFactory.getLogger(JwtUserTestConverter.class);


    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter =
            new JwtGrantedAuthoritiesConverter();

    @Value("${jwt.auth.client.epal.resource-id}")
    private String resourceId;
    @Value("${jwt.auth.principle-attribute}")
    private String principeAtribute;

    @Override
    public AbstractAuthenticationToken convert(Jwt source) {
        Collection<GrantedAuthority> authorities = Stream
                .concat(jwtGrantedAuthoritiesConverter.convert(source).stream(), extractRessourceRole(source).stream())
                .collect(Collectors.toSet());
        return new JwtAuthenticationToken(source, authorities, getPrincipaleClaimName(source));
    }


    private String getPrincipaleClaimName(Jwt jwt) {
        String claimName = JwtClaimNames.SUB;

        if (principeAtribute != null) {
            claimName = principeAtribute;
        }
        return jwt.getClaim("email");
    }

    private Collection<? extends GrantedAuthority> extractRessourceRole(Jwt jwt) {
        Map<String, Object> resourceAccess;
        Map<String, Object> resource;
        Collection<String> resourceRoles;
        if (jwt.getClaim("resource_access") == null) {
            return Set.of();
        }
      //  getKeycloakUser(jwt);
        resourceAccess = jwt.getClaim("resource_access");
        if (resourceAccess.get(resourceId) == null) {
            return Set.of();
        }
        resource = (Map<String, Object>) resourceAccess.get(resourceId);
        resourceRoles = (Collection<String>) resource.get("roles");
        return resourceRoles
                .stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role)
                ).collect(Collectors.toSet());

    }

    private KeyCloakUser getKeycloakUser(Jwt jwt) {
        String email = jwt.getClaim("email").toString();
        String name = jwt.getClaim("name").toString();
        String fullname = jwt.getClaim("family_name").toString();
        logger.info("email , {}", email);
        logger.info("name , {}", name);

        KeyCloakUser user = new KeyCloakUser(name, fullname, email, name);
        logger.info("keycloakUser , {}", user);

        return user;

    }

}
