package com.example.demo.service.impl;

import com.example.demo.controller.dto.RecetaDTO;
import com.example.demo.controller.dto.RecetaResponseDTO;
import com.example.demo.model.Medicamento;
import com.example.demo.model.Receta;
import com.example.demo.repository.medic.RecetaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RecetaServiceImpl {
    @Autowired
    private RecetaRepository recetaRepository;

    @Override
    public RecetaResponseDTO crear(RecetaDTO recetaDTO) {
        Receta receta = new Receta();
        // Mapear campos básicos
        receta.setNombrePaciente(recetaDTO.getNombrePaciente());
        // ... (otros campos)

        // Mapear medicamentos
        List<Medicamento> medicamentos = recetaDTO.getMedicamentos().stream()
                .map(medDto -> {
                    Medicamento med = new Medicamento();
                    med.setNombre(medDto.getNombre());
                    med.setDosis(medDto.getDosis());
                    // ... (otros campos)
                    return med;
                }).toList();

        receta.setMedicamentos(medicamentos);
        Receta recetaGuardada = recetaRepository.save(receta);
        return convertToResponseDTO(recetaGuardada);
    }

    @Override
    public RecetaResponseDTO obtenerPorId(Long id) {
        Receta receta = recetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada"));
        return convertToResponseDTO(receta);
    }

    @Override
    public List<RecetaResponseDTO> listarTodas() {
        return recetaRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    @Override
    public RecetaResponseDTO actualizar(Long id, RecetaDTO recetaDTO) {
        Receta recetaExistente = recetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada"));

        // Actualizar campos básicos
        recetaExistente.setNombrePaciente(recetaDTO.getNombrePaciente());
        // ... (otros campos)

        // Actualizar medicamentos (elimina los antiguos y crea nuevos)
        recetaExistente.getMedicamentos().clear();
        List<Medicamento> nuevosMedicamentos = recetaDTO.getMedicamentos().stream()
                .map(medDto -> {
                    Medicamento med = new Medicamento();
                    med.setNombre(medDto.getNombre());
                    // ... (otros campos)
                    return med;
                }).toList();

        recetaExistente.getMedicamentos().addAll(nuevosMedicamentos);
        Receta recetaActualizada = recetaRepository.save(recetaExistente);
        return convertToResponseDTO(recetaActualizada);
    }

    @Override
    public void eliminar(Long id) {
        Receta receta = recetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada"));
        recetaRepository.delete(receta);
    }

    private RecetaResponseDTO convertToResponseDTO(Receta receta) {
        RecetaResponseDTO dto = new RecetaResponseDTO();
        // Mapear campos básicos
        dto.setId(receta.getId());
        dto.setNombrePaciente(receta.getNombrePaciente());
        // ... (otros campos)

        // Mapear medicamentos
        List<RecetaResponseDTO.MedicamentoMinimoDTO> medicamentosDTO = receta.getMedicamentos().stream()
                .map(med -> {
                    RecetaResponseDTO.MedicamentoMinimoDTO medDTO = new RecetaResponseDTO.MedicamentoMinimoDTO();
                    medDTO.setId(med.getId());
                    medDTO.setNombre(med.getNombre());
                    medDTO.setDosis(med.getDosis());
                    return medDTO;
                }).toList();

        dto.setMedicamentos(medicamentosDTO);
        return dto;
    }
}
