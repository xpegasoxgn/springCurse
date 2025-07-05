package com.example.demo.repository.medic;

import com.example.demo.controller.dto.MedicamentoResponseDTO;
import com.example.demo.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    List<Medicamento> findByUsuarioUsername(String username); // Filtra por usuario
   // List<MedicamentoResponseDTO> listarTodos() throws Exception;
}
