package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.dto.LoginRequest;
import com.example.demo.controller.dto.RegisterRequest;
import com.example.demo.dto.jwt.JwtLoginResponse;
import com.example.demo.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    
    @Autowired
    private AuthService authService;

    @GetMapping("/listar")
    public String getPacientes() {
        return "hola funciona el jwt";
    }

    @PostMapping("/crear")
    public ResponseEntity<?> addPaciente(@RequestBody LoginRequest req,HttpServletRequest request) {
        String ip= request.getRemoteAddr();
        JwtLoginResponse response=authService.login(req);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/register")
    public ResponseEntity<?> editPaciente(@RequestBody RegisterRequest request) {
        try {
            authService.register(request);
            return  ResponseEntity.ok("Usuario registreado");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("ERROR: "+ e.getMessage());

        }
    }

    @DeleteMapping("/register")
    public ResponseEntity<?> deletePaciente(@RequestBody RegisterRequest request) {
        try {
            authService.register(request);
            return  ResponseEntity.ok("Usuario registreado");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("ERROR: "+ e.getMessage());

        }
    }
    
}
