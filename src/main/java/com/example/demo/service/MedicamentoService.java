package com.example.demo.service;

import com.example.demo.controller.dto.MedicamentoDTO;
import com.example.demo.controller.dto.MedicamentoResponseDTO;

import java.util.List;

public interface MedicamentoService {
    public void crear(MedicamentoDTO medicamentoDTO, String username) throws  Exception;
    public List<MedicamentoResponseDTO> listarTodos() throws Exception;
    MedicamentoResponseDTO actualizar(Long id, MedicamentoDTO medicamentoDTO) throws Exception;
    void eliminar(Long id) throws Exception;
}
