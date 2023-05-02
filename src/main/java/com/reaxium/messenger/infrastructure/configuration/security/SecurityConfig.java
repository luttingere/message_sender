package com.reaxium.messenger.infrastructure.configuration.security;

import com.reaxium.messenger.infrastructure.security.PasswordEncoderImpl;
import com.reaxium.messenger.infrastructure.security.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@Slf4j
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoderImpl passwordEncoder;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeHttpRequests()
                .requestMatchers(EndpointRequest.to("health")).permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic(Customizer.withDefaults());
        log.info("Setting Security Filter Chain");
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        log.info("Setting DAO Authentication Provider");
        return authProvider;
    }

    @Bean
    public PasswordEncoderImpl passwordEncoder() {
        return this.passwordEncoder;
    }

}
