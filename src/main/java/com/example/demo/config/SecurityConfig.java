package com.example.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
            .csrf(csrf -> csrf.disable())
            .exceptionHandling(ex-> 
                ex.accessDeniedHandler((request, response , accessDeniedException)->{
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    response.setContentType("aplication/json");
                    response.getWriter().write("{\"error\":\"Acceso denegado- no tienes permisos\"}");
                }))
            .authorizeHttpRequests(auth -> auth
               
                
                .requestMatchers("/auth/login").permitAll()
                .requestMatchers("/auth/hello").authenticated()
                .anyRequest().authenticated()// necesidad para login 
            );

        return http.build();

    }

     @Bean
     public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
     }
}
