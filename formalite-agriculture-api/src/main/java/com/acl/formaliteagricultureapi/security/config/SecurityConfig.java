package com.acl.formaliteagricultureapi.security.config;


import com.acl.formaliteagricultureapi.converter.JwtAgriConverter;
import com.acl.formaliteagricultureapi.converter.JwtAgriLocalConverter;
import com.acl.formaliteagricultureapi.converter.JwtAgriTestConverter;
import com.acl.formaliteagricultureapi.converter.JwtAgriWebConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

    private final JwtAgriConverter jwtAgriConverter;
    private final JwtAgriLocalConverter jwtAgriLocalConverter;
    private final JwtAgriWebConverter jwtAgriWebConverter;
    private final JwtAgriTestConverter jwtAgriTestConverter;


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
    private final String[] PUBLIC_URL = {"/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html",
            "api/auth/login", "api/clients/**", "api/v1/produit/**","api/v1/typepiecejointe/**",
            "api/v1/report/processVerbal/**","api/v1/redevance/**",
            "api/v1/report/**", "api/v1/statistique/**"};

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
                .authorizeHttpRequests(auth -> auth.requestMatchers(PUBLIC_URL).
                        permitAll().anyRequest().authenticated())
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAgriConverter)))
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
    public SecurityFilterChain securityFilterAtdLocalChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.requestMatchers(PUBLIC_URL).
                        permitAll().anyRequest().authenticated())
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAgriLocalConverter)))
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
    public SecurityFilterChain securityFilterAgriAtdLocalChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.requestMatchers(PUBLIC_URL).
                        permitAll().anyRequest().authenticated())
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAgriWebConverter)))
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
    public SecurityFilterChain securityFilterAgriAtdTestChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.requestMatchers(PUBLIC_URL).
                        permitAll().anyRequest().authenticated())
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAgriTestConverter)))
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
