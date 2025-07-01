package com.example.demo.controller;


import com.example.demo.model.medico.Medico;
import com.example.demo.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoService medicoService;

    @GetMapping("/listar")
    public List<Medico> getAllMedicos() {
        return medicoService.listaMedicos();
    }

    @GetMapping("/{id}")
    public Medico getMedicoById(Long id) {
        return medicoService.getMedicoById(id);
    }

    @PostMapping("crear")
    public Medico createMedico(@RequestBody Medico medico) {
        return medicoService.createMedico(medico);
    }

    @PutMapping("editar/{id}")
    public Medico updateMedico(@PathVariable Long id, @RequestBody Medico medico) {
        return medicoService.updateMedico(id, medico);
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Void> deleteMedico(@PathVariable Long id) {
        medicoService.deleteMedico(id);
        return ResponseEntity.noContent().build();
    }
}
