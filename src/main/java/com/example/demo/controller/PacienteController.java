package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Paciente;
import com.example.demo.service.PacienteService;

@RestController
@RequestMapping("/api_paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/paciente")
    public ResponseEntity<List<Paciente>> getPacientes() {
        try {
            return ResponseEntity.ok(pacienteService.getPacientes());
        } 
        catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/paciente")
    public ResponseEntity<Paciente> addPaciente(@RequestBody Paciente paciente) {
        try {
            return ResponseEntity.ok(pacienteService.addPaciente(paciente));
        } 
        catch (Exception e) {
            return null;
        }
    }

    @PutMapping("/paciente/{id}")
    public ResponseEntity<String> editPaciente(@PathVariable Long id, @RequestBody Paciente paciente) {
        try {
            return ResponseEntity.ok(pacienteService.editPaciente( id, paciente));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/paciente/{id}")
    public ResponseEntity<String> deletePaciente(@PathVariable Long id) {
        try {
            pacienteService.deletePaciente(id);
            return ResponseEntity.ok("Paciente eliminado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    
}
