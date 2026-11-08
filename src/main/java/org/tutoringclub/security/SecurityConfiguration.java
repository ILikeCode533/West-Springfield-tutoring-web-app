

package org.tutoringclub.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain applicationSecurity(
            HttpSecurity http,
            Environment environment) throws Exception {

        boolean development = environment.matchesProfiles("DEV");

        // Diagnostic information
        System.out.println("========== SECURITY DEBUG ==========");

        System.out.println("ACTIVE PROFILES: "
            + Arrays.toString(environment.getActiveProfiles()));

        System.out.println("SPRING PROFILE PROPERTY: "
            + environment.getProperty("spring.profiles.active"));

        System.out.println("DEVELOPMENT: " + development);

        System.out.println("CUSTOM SECURITY CHAIN LOADED");

        System.out.println("====================================");

        // Development-only CSRF exception
        if (development) {
            http.csrf(csrf -> csrf
                .ignoringRequestMatchers(
                    "/api/requests",
                    "/api/requests/**"
                )
            );
        }

        // Authorization rules
        http.authorizeHttpRequests(authorize -> {

            authorize.requestMatchers(
                "/",
                "/login",
                "/error",
                "/css/**",
                "/js/**",
                "/favicon.ico"
            ).permitAll();

            if (development) {
                authorize.requestMatchers(
                    HttpMethod.POST,
                    "/api/requests",
                    "/api/requests/**"
                ).permitAll();
            }

            authorize.anyRequest().authenticated();
        });

        // Login configuration
        return http
            .formLogin(form -> form
                .loginPage("/login")
                .permitAll()
            )
            .build();
    }
}


