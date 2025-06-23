package com.example.demo.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.demo.model.auth.Usuarios;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {
    @Value ("${jwt.secret}")
    private String secret;

    @Value ("${jwt.expiration}")
    private Long expirationInMs;

    //generar token
    public String generarToken(Usuarios usuario){
        return Jwts.builder()
            .setSubject(usuario.getUsername())//el usuario al que pertenece el token
            .setIssuedAt(new Date())//fecha de creacion
            .setExpiration(new Date(System.currentTimeMillis()+expirationInMs))//vence en 2 horas
            .signWith(SignatureAlgorithm.HS256,secret)//Firma con algoritmo hs256y la clave secreta
            .compact();// generamos el token como un string armamos el token
    }

    //validar token 

    public boolean validarToken(String token){

        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //extraer username
    public String obtenerUsernameDelToken(String token){
        Claims claims= Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .getBody();
        return claims.getSubject();
    }
}
