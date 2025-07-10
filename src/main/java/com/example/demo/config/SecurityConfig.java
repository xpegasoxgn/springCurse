package com.example.demo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.demo.config.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .cors(cors -> cors.configurationSource(corsConfigurationSource()))
      .csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .requestMatchers("/auth/login", "/auth/register", "/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
            .requestMatchers("/medicamento/**", "/receta/**").hasAuthority("ROLE_ADMIN")
            .anyRequest().authenticated()
      )
      .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
      .exceptionHandling(ex -> ex
            .accessDeniedHandler((req, res, ex2) -> {
                res.setStatus(HttpStatus.FORBIDDEN.value());
                res.setContentType("application/json");
                res.getWriter().write("{\"error\":\"Acceso denegado\"}");
            })
      );
    return http.build();
}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//prueba/

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration corsConfig = new CorsConfiguration();
    corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://localhost:4300"));
    corsConfig.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
    corsConfig.setAllowedHeaders(Arrays.asList("Authorization","Content-Type"));
    corsConfig.setExposedHeaders(Arrays.asList("Authorization"));
    corsConfig.setAllowCredentials(true);
    corsConfig.setMaxAge(3600L);
    
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", corsConfig);
    return source;
}

}
