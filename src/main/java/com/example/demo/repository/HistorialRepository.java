package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Historial;

@Repository
public interface HistorialRepository extends JpaRepository<Historial, Long>{
    Optional <Historial> findById(Long id);
    
}
