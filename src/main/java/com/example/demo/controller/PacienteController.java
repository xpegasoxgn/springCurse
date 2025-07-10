package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Paciente;
import com.example.demo.service.CloudinaryService;
import com.example.demo.service.PacienteService;

@RestController
@RequestMapping("/api_paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private CloudinaryService cloudinaryService;

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
    @PostMapping("/{id}/foto")
    public ResponseEntity<?> uploadFoto(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        try {
            String url = pacienteService.subirFotoPaciente(id, file);
            return ResponseEntity.ok(Map.of(
                "message", "Foto subida correctamente",
                "url", url
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

}
