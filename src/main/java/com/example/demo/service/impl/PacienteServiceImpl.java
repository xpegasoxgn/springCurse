package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Paciente;
import com.example.demo.repository.PacienteRepository;
import com.example.demo.service.PacienteService;

@Service
public class PacienteServiceImpl implements PacienteService{

    @Autowired
    PacienteRepository pacienteRepository;

    @Override
    public List<Paciente> getPacientes() throws Exception{
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente addPaciente(Paciente paciente) throws Exception{
        return pacienteRepository.save(paciente);
    }

    @Override
    public String editPaciente(Long id, Paciente paciente) throws Exception{
        Optional<Paciente> pacienteExistente = pacienteRepository.findById(id);
        if (pacienteExistente.isEmpty()) {
            throw new Exception("Paciente no encontrado con id: " + id);
        }
        Paciente pacienteToUpdate = pacienteExistente.get();

        pacienteToUpdate.setNombre(paciente.getNombre());
        pacienteToUpdate.setApellido(paciente.getApellido());
        pacienteToUpdate.setCi(paciente.getCi());
        pacienteToUpdate.setFecha_nacimiento(paciente.getFecha_nacimiento());
        pacienteToUpdate.setSexo(paciente.getSexo());
        pacienteToUpdate.setDireccion(paciente.getDireccion());
        pacienteToUpdate.setTelefono(paciente.getTelefono());

        pacienteRepository.save(pacienteToUpdate);
        return "Paciente actualizado correctamente";
    }

    @Override
    public String deletePaciente(Long id) throws Exception{
        Optional<Paciente> pacienteExistente = pacienteRepository.findById(id);
        if (pacienteExistente.isEmpty()) {
            throw new Exception("Paciente no encontrado con id: " + id);
        }
        pacienteRepository.deleteById(id);
        return "Paciente eliminado correctamente";
    }
    
}
