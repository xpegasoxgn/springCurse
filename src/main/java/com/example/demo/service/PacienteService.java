package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Paciente;

public interface PacienteService {

    public List<Paciente> getPacientes() throws Exception;

    public Paciente addPaciente(Paciente paciente) throws Exception;

    public String editPaciente(Long id, Paciente paciente) throws Exception;

    public String deletePaciente(Long id) throws Exception;
    
}
