package com.acl.vbs.fret.converters;

import com.acl.vbs.fret.models.KeyCloakUserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.acl.vbs.fret.utils.AppUtils.extractResourceRoles;
import static com.acl.vbs.fret.utils.AppUtils.getKeycloakUser;

@Component
public class KeycloakAuthLocalConverter implements Converter<Jwt, UsernamePasswordAuthenticationToken> {

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    @Value("${keycloak.admin.client.ids[2]}")
    private String resourceId;

    @Override
    public UsernamePasswordAuthenticationToken convert(Jwt source) {
        Collection<GrantedAuthority> authorities = Stream
                .concat(jwtGrantedAuthoritiesConverter.convert(source).stream(), extractResourceRoles(resourceId, source).stream())
                .collect(Collectors.toSet());

        KeyCloakUserModel sourceUser = getKeycloakUser(source);

        return new UsernamePasswordAuthenticationToken(sourceUser, source, authorities);
    }

}