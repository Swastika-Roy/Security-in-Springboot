package com.codingshuttle.SecurityApp.SecurityApplication.config;

import com.codingshuttle.SecurityApp.SecurityApplication.entities.enums.Permission;
import com.codingshuttle.SecurityApp.SecurityApplication.filters.JwtAuthFilter;
import com.codingshuttle.SecurityApp.SecurityApplication.handlers.OAuth2SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.eclipse.jetty.http.HttpMethod;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.codingshuttle.SecurityApp.SecurityApplication.entities.enums.Permission.*;
import static com.codingshuttle.SecurityApp.SecurityApplication.entities.enums.Role.ADMIN;
import static com.codingshuttle.SecurityApp.SecurityApplication.entities.enums.Role.CREATOR;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    private static final String[] publicRoutes = {
            "/error", "/auth/**", "/home.html"
    };

    public WebSecurityConfig(JwtAuthFilter jwtAuthFilter, OAuth2SuccessHandler oAuth2SuccessHandler) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.oAuth2SuccessHandler = oAuth2SuccessHandler;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(publicRoutes).permitAll()
                        .requestMatchers(String.valueOf(HttpMethod.GET), "/posts/**").permitAll()
                        .requestMatchers(String.valueOf(HttpMethod.POST), "/posts/**").hasAnyRole(ADMIN.name(), CREATOR.name())
                        .requestMatchers(String.valueOf(HttpMethod.POST), "/posts/**").hasAuthority(POST_CREATE.name())
                        .requestMatchers(String.valueOf(HttpMethod.GET), "/posts/**").hasAuthority(POST_VIEW.name())
                        .requestMatchers(String.valueOf(HttpMethod.PUT), "/posts/**").hasAuthority(POST_UPDATE.name())
                        .requestMatchers(String.valueOf(HttpMethod.DELETE), "/posts/**").hasAuthority(POST_DELETE.name())
                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(oauth2 -> oauth2
                        .failureUrl("/login?error=true")
                        .successHandler(oAuth2SuccessHandler));
        return httpSecurity.build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
