package com.example.demo.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.config.security.JwtUtils;
import com.example.demo.model.auth.Usuarios;
import com.example.demo.model.auth.Sesion;
import com.example.demo.repository.auth.SesionRepository;
import com.example.demo.repository.auth.UsuarioRepository;
import com.example.demo.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SesionRepository sesionRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public String login(String username,String password, String ip){
        Usuarios usuario = usuarioRepository.findByUsername(username)
        .orElseThrow(()-> new RuntimeException("usuario no encontrado"));

        if(!usuario.getPassword().equals(password)){
            throw  new RuntimeException("credenciales invalidas");
        }
        String token=jwtUtils.generarToken(usuario);

        Sesion sesion=new Sesion();
        sesion.setUsuario(usuario);
        sesion.setToken(token);
        sesion.setFechaGeneracion(LocalDateTime.now());
        sesion.setFechaExpiracion(LocalDateTime.now().plusHours(2));
        sesion.setIp(ip);
        sesion.setActivo(true);
        sesionRepository.save(sesion);

        return token;
    }
}
