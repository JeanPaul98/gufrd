package com.acl.vbs.config;

import com.acl.vbs.converters.KeycloakAuthHostConverter;
import com.acl.vbs.converters.KeycloakAuthLocalClientConverter;
import com.acl.vbs.converters.KeycloakAuthLocalConverter;
import com.acl.vbs.converters.KeycloakAuthMobileConverter;
import com.acl.vbs.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class AppSecurityConfig {

    private final KeycloakAuthHostConverter hostConverter;
    private final KeycloakAuthLocalConverter localConverter;
    private final KeycloakAuthMobileConverter mobileConverter;
    private final KeycloakAuthLocalClientConverter localClientConverter;

    @Value("${keycloak.admin.issuer}")
    private String keycloakIssuer;

    @Bean
    public SecurityFilterChain localSecurityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.setBuilder(http))
                .authorizeHttpRequests(authorize ->
                        authorize.requestMatchers(AppUtils.PUBLIC_URLS).permitAll().anyRequest().authenticated()
                )
                .sessionManagement(sessions ->
                        sessions.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(localConverter))
                )
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(hostConverter))
                )
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(mobileConverter))
                )
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(localClientConverter))
                )
                .build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withIssuerLocation(keycloakIssuer).build();
    }
}
