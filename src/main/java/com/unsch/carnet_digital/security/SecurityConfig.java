package com.unsch.carnet_digital.security;

import com.unsch.carnet_digital.jwt.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthFilter jwtFilter;

    public SecurityConfig(JwtAuthFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                // ⭐ ACTIVAMOS EL CORS QUE USA TU CorsConfigurationSource()
                .cors(Customizer.withDefaults())

                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        // Rutas públicas
                        .requestMatchers(
                                "/oauth2/**",
                                "/loginSuccess",
                                "/error",
                                "/auth/**",
                                "/control_ph/**" // ⭐ Importante para permitir las imágenes
                        ).permitAll()

                        .anyRequest().authenticated()
                )

                // Login de Google
                .oauth2Login(oauth -> oauth
                        .defaultSuccessUrl("/loginSuccess", true)
                )

                // JWT
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

                .build();
    }
}
