package com.acl.mswauth.security.config;


import com.acl.mswauth.converter.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthConverter JwtAuthConverter;
    private final JwtAclLocalConverter jwtAclLocalConverter;
    private final JwtAdminAuthConverter jwtAdminAuthConverter;
    private final JwtVbsConverter jwtVbsConverter;

    //private final JwtUserAdminConverter jwtUserAdminConverter;

    private static final String[] AUTH_WHITELIST = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };
    private final String[] PUBLIC_URL = {"/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "api/auth/login", "api/clients/**",
            "/api/auth", "/api/connected/**", "/api/jasper/**","api/users/**", "/api/groupes/**", "api/v1/statistique/**",
    "api/formalite/**", "api/v1/guford/**", "api/societe/**"};

    //Todo: Mettre le swagger sans passer par le token

    /**
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.requestMatchers(PUBLIC_URL).permitAll().anyRequest().authenticated())
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(JwtAuthConverter)))
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(corsCustomizer -> corsCustomizer.configurationSource(request -> {
                            CorsConfiguration config = new CorsConfiguration();
                            config.setAllowedOrigins(Collections.singletonList("*"));
                            config.setAllowedMethods(Collections.singletonList("*"));
                            config.setAllowCredentials(false);
                            config.setAllowedHeaders(Collections.singletonList("*"));
                            config.setExposedHeaders(List.of("Authorization"));
                            config.setMaxAge(3600L);
                            return config;
                        })
                );
        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterAclLocalChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.requestMatchers(PUBLIC_URL).permitAll().anyRequest().authenticated())
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAclLocalConverter)))
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(corsCustomizer -> corsCustomizer.configurationSource(request -> {
                            CorsConfiguration config = new CorsConfiguration();
                            config.setAllowedOrigins(Collections.singletonList("*"));
                            config.setAllowedMethods(Collections.singletonList("*"));
                            config.setAllowCredentials(false);
                            config.setAllowedHeaders(Collections.singletonList("*"));
                            config.setExposedHeaders(List.of("Authorization"));
                            config.setMaxAge(3600L);
                            return config;
                        })
                );
        return http.build();
    }

    @Bean
    @Order(3)
    public SecurityFilterChain securityFilterApiLocalChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.requestMatchers(PUBLIC_URL).permitAll().anyRequest().authenticated())
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAdminAuthConverter)))
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(corsCustomizer -> corsCustomizer.configurationSource(request -> {
                            CorsConfiguration config = new CorsConfiguration();
                            config.setAllowedOrigins(Collections.singletonList("*"));
                            config.setAllowedMethods(Collections.singletonList("*"));
                            config.setAllowCredentials(false);
                            config.setAllowedHeaders(Collections.singletonList("*"));
                            config.setExposedHeaders(List.of("Authorization"));
                            config.setMaxAge(3600L);
                            return config;
                        })
                );
        return http.build();
    }


    @Bean
    @Order(4)
    public SecurityFilterChain securityFilterApiVbsChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .securityMatcher("/api/vbs/**")
                .authorizeHttpRequests(auth -> auth.requestMatchers(PUBLIC_URL).permitAll().anyRequest().authenticated())
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtVbsConverter)))
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(corsCustomizer -> corsCustomizer.configurationSource(request -> {
                            CorsConfiguration config = new CorsConfiguration();
                            config.setAllowedOrigins(Collections.singletonList("*"));
                            config.setAllowedMethods(Collections.singletonList("*"));
                            config.setAllowCredentials(false);
                            config.setAllowedHeaders(Collections.singletonList("*"));
                            config.setExposedHeaders(List.of("Authorization"));
                            config.setMaxAge(3600L);
                            return config;
                        })
                );
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
