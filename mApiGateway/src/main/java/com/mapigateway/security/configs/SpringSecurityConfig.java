package com.mapigateway.security.configs;


import com.mapigateway.user.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

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
                    .cors(crs -> crs.disable())
                    .csrf(csrf -> csrf.disable())
                    .authorizeExchange(authz ->
                                    authz

                                            .pathMatchers("/home", "/token", "/register").permitAll()
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
/*

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) throws Exception {

        return http
                .authorizeExchange()
                .pathMatchers("/home/**").authenticated()
                .anyExchange().permitAll().and()
                .csrf().disable()
                .build();

        return http
                .cors().disable()
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register", "/login**", "/home", "**").permitAll()
                        //.anyRequest().authenticated()
                )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .userDetailsService(myUserDetailsService)

                .formLogin(withDefaults())
                .httpBasic(withDefaults())
                .build();
    }
*/

    /*
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange()
                .pathMatchers("/register", "/login**", "/home", "/token").permitAll() // Allow access to public endpoints
                .anyExchange().authenticated() // Require authentication for all other endpoints
                .and()
                .formLogin() // Enable form-based login
                .and()
                .httpBasic(); // Enable basic authentication

        return http.build();
    }
*/

}
