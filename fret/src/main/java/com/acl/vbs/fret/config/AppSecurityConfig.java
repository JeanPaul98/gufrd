package com.acl.vbs.fret.config;

import com.acl.vbs.fret.converters.KeycloakAuthHostConverter;
import com.acl.vbs.fret.converters.KeycloakAuthLocalClientConverter;
import com.acl.vbs.fret.converters.KeycloakAuthLocalConverter;
import com.acl.vbs.fret.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import static com.acl.vbs.fret.utils.AppUtils.PUBLIC_URLS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class AppSecurityConfig {

    private final KeycloakAuthHostConverter hostConverter;
    private final KeycloakAuthLocalConverter localConverter;
    private final KeycloakAuthLocalClientConverter localClientConverter;

    @Value("${keycloak.admin.issuer}")
    private String keycloakIssuer;

    @Bean
    public SecurityFilterChain localSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(localClientConverter)))
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(localConverter)))
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(hostConverter)))
                .authorizeHttpRequests(authorize -> authorize.requestMatchers(PUBLIC_URLS).permitAll().anyRequest().authenticated())
                .sessionManagement(sessions -> sessions.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(cors -> cors.setBuilder(http))
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withIssuerLocation(keycloakIssuer).build();
    }

//    private HttpSecurity httpSecurity(HttpSecurity http) throws Exception {
//        return http.csrf(AbstractHttpConfigurer::disable)
//                .cors(cors -> cors.setBuilder(http))
//                .authorizeHttpRequests(authorize ->
//                        authorize.requestMatchers(AppUtils.PUBLIC_URLS).permitAll().anyRequest().authenticated()
//                )
//                .sessionManagement(sessions ->
//                        sessions.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                );
//    }
}
