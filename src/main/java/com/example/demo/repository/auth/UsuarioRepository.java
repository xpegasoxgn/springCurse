package com.example.demo.repository.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.auth.Usuarios;



    @Repository
    public interface  UsuarioRepository extends JpaRepository<Usuarios, Long>{
        Optional <Usuarios> findByUsername(String Username);
    } 

