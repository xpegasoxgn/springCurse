package com.example.demo.service;

import com.example.demo.controller.dto.RecetaDTO;
import com.example.demo.controller.dto.RecetaResponseDTO;

import java.util.List;

public interface RecetaService {
    RecetaResponseDTO crear(RecetaDTO recetaDTO);
    RecetaResponseDTO obtenerPorId(Long id);
    List<RecetaResponseDTO> listarTodas();
    RecetaResponseDTO actualizar(Long id, RecetaDTO recetaDTO);
    void eliminar(Long id);
}
