package com.example.demo.repository.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.auth.Rol;
import java.util.List;




    @Repository
    public interface  RolRepository extends JpaRepository<Rol, Long>{
        Optional <Rol>  findByNombre (String nombre);
    } 

