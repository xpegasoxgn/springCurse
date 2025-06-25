package com.example.demo.service;

import com.example.demo.controller.dto.LoginRequest;
import com.example.demo.dto.jwt.JwtLoginResponse;

public interface  AuthService {
    public JwtLoginResponse login(LoginRequest request);
}
