package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{
    Optional <Paciente> findById(Long id);
} 
