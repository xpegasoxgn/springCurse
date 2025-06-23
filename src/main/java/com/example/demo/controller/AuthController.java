package com.example.demo.controller;

import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.dto.LoginRequest;
import com.example.demo.service.AuthService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
            String token=authService.login(req.getUsername(), req.getPassword(), ip);
        return ResponseEntity.ok(Map.of("token",token));
    }
    
}
