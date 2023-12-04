package com.mapigateway.security.configs;


import com.mapigateway.user.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebFluxSecurity
//@EnableReactiveMethodSecurity
@Getter
@Setter
public class SpringSecurityConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UserService myUserDetailsService;

    public SpringSecurityConfig(UserService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }



        @Bean
        public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
            http
                    .cors(withDefaults())
                    .csrf(csrf -> csrf.disable())
                    .authorizeExchange(authz ->
                                    authz

                                            .pathMatchers("/api/home", "/api/token", "/api/register").permitAll()
                                            .anyExchange().authenticated()
                                            .and().authenticationManager(reactiveAuthenticationManager())
                    )
                    .httpBasic(withDefaults())
                    .oauth2ResourceServer(oauth2 -> oauth2.jwt())

            ;
            return http.build();
        }

    @Bean
    ReactiveAuthenticationManager reactiveAuthenticationManager() {
        UserDetailsRepositoryReactiveAuthenticationManager authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager(
                myUserDetailsService
        );
        authenticationManager.setPasswordEncoder(passwordEncoder);
        return authenticationManager;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.applyPermitDefaultValues();
        configuration.addExposedHeader("Token");
        configuration.addAllowedMethod(HttpMethod.PUT);
        configuration.addAllowedMethod(HttpMethod.POST);
        configuration.addAllowedMethod(HttpMethod.DELETE);
        configuration.addAllowedMethod(HttpMethod.GET);

        configuration.setAllowCredentials(true);

        configuration.setAllowedOrigins( Collections.singletonList( "*" ) );
        configuration.addAllowedOrigin ( "*" );

        source.registerCorsConfiguration("/**",configuration);
        return source;
    }

}
