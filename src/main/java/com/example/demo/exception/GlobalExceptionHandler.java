package com.example.demo.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String,Object>> haResponseBadCredentials(BadCredentialsException ex){

        Map<String,Object> error=new HashMap<>();
        error.put("timestamp",  LocalDateTime.now());
        error.put("status",401);
        error.put("error","Unauthorized");
        error.put("messagge","Usuario o contraseña incorrecta");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String,Object>> handleAccessDenied(AccessDeniedException ex){

        Map<String,Object> error=new HashMap<>();
        error.put("timestamp",  LocalDateTime.now());
        error.put("status",403);
        error.put("error","Forbiden");
        error.put("messagge","No tienes permisos para acceder a este recurso");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String,Object>> handleRuntime(RuntimeException ex){

        Map<String,Object> error=new HashMap<>();
        error.put("timestamp",  LocalDateTime.now());
        error.put("status",400);
        error.put("error","Bad Request");
        error.put("messagge",ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> handleGeneral(Exception ex){

        Map<String,Object> error=new HashMap<>();
        error.put("timestamp",  LocalDateTime.now());
        error.put("status",500);
        error.put("error","Internal Server Error");
        error.put("messagge","Ha ocurrido un error inesperado.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
    
    
}
