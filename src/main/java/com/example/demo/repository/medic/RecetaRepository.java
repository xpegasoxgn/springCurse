package com.example.demo.repository.medic;

import com.example.demo.model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecetaRepository extends JpaRepository<Receta,Long> {
}
