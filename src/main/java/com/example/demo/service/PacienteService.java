package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.jwt.JwtLoginResponse;
import com.example.demo.controller.PacienteController;
import com.example.demo.model.Paciente;

public interface PacienteService {

    public List<Paciente> getPacientes() throws Exception;

    public String addPaciente(Paciente paciente) throws Exception;

    public String editPaciente(Paciente paciente) throws Exception;

    public String deletePaciente(Long id) throws Exception;
    
}
