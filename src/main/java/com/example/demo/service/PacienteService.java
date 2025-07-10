package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Paciente;

public interface PacienteService {

    public List<Paciente> getPacientes() throws Exception;

    public Paciente addPaciente(Paciente paciente) throws Exception;

    public String editPaciente(Long id, Paciente paciente) throws Exception;

    public String deletePaciente(Long id) throws Exception;

    public String subirFotoPaciente(Long pacienteId, MultipartFile file) throws Exception;
    
}
