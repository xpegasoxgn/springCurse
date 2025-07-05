package com.example.demo.service.impl;

import com.example.demo.controller.dto.MedicamentoDTO;
import com.example.demo.controller.dto.MedicamentoResponseDTO;
import com.example.demo.model.Medicamento;
import com.example.demo.model.auth.Usuarios;
import com.example.demo.repository.auth.UsuarioRepository;
import com.example.demo.repository.medic.MedicamentoRepository;
import com.example.demo.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicamentoServiceImp implements MedicamentoService {
    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository; // Para obtener el usuario autenticado

    @Override
    public void crear(MedicamentoDTO medicamentoDTO, String username) {
        Usuarios usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Medicamento medicamento = new Medicamento();
        medicamento.setNombre(medicamentoDTO.getNombre());
        medicamento.setDosis(medicamentoDTO.getDosis());
        medicamento.setFabricante(medicamentoDTO.getFabricante());
        medicamento.setFechaVencimiento(medicamentoDTO.getFechaVencimiento());
        medicamento.setPrecio(medicamentoDTO.getPrecio());
        medicamento.setUsuario(usuario); // Asigna el usuario que creó el registro
        medicamentoRepository.save(medicamento);
    }
    @Override
    public MedicamentoResponseDTO actualizar(Long id, MedicamentoDTO medicamentoDTO) throws Exception {
        Medicamento medicamentoExistente = medicamentoRepository.findById(id)
                .orElseThrow(() -> new Exception("Medicamento no encontrado con id: " + id));

        // Actualiza los campos
        medicamentoExistente.setNombre(medicamentoDTO.getNombre());
        medicamentoExistente.setPrecio(medicamentoDTO.getPrecio());
        medicamentoExistente.setFechaVencimiento(medicamentoDTO.getFechaVencimiento());
        medicamentoExistente.setDosis(medicamentoDTO.getDosis());
        medicamentoExistente.setFabricante(medicamentoDTO.getFabricante());

        Medicamento medicamentoActualizado = medicamentoRepository.save(medicamentoExistente);
        return convertToResponseDTO(medicamentoActualizado);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        if (!medicamentoRepository.existsById(id)) {
            throw new Exception("Medicamento no encontrado con id: " + id);
        }
        medicamentoRepository.deleteById(id);
    }


    @Override
    public List<MedicamentoResponseDTO> listarTodos() throws Exception {
        List<Medicamento> medicamentos = medicamentoRepository.findAll();
        return medicamentos.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    private MedicamentoResponseDTO convertToResponseDTO(Medicamento medicamento) {
        MedicamentoResponseDTO dto = new MedicamentoResponseDTO();
        dto.setId(medicamento.getId());
        dto.setNombre(medicamento.getNombre());
        dto.setPrecio(medicamento.getPrecio());
        dto.setFechaVencimiento(medicamento.getFechaVencimiento());
        dto.setDosis(medicamento.getDosis());
        dto.setFabricante(medicamento.getFabricante());

        if (medicamento.getUsuario() != null) {
            MedicamentoResponseDTO.UsuarioMinimoDTO usuarioDTO = new MedicamentoResponseDTO.UsuarioMinimoDTO();
            usuarioDTO.setId(medicamento.getUsuario().getId());
            usuarioDTO.setNombre(medicamento.getUsuario().getNombre());
            usuarioDTO.setUsername(medicamento.getUsuario().getUsername());
            dto.setUsuario(usuarioDTO);
        }

        return dto;
    }
}
