package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.dto.LoginRequest;
import com.example.demo.dto.jwt.JwtLoginResponse;
import com.example.demo.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;



@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req,HttpServletRequest request) {
        //TODO: process POST request
            String ip= request.getRemoteAddr();
            JwtLoginResponse response=authService.login(req);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/hello")
    public String getMethodName() {
        return "hola funciona el jwt";
    }
    
    
}
