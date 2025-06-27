	package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity(prePostEnabled = true) // ✅ Habilita @PreAuthorize y @PostAuthorize
@SpringBootApplication
public class ProyectoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProyectoApplication.class, args);
    }
}
