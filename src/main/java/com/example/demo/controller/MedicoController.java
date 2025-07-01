package com.example.demo.controller;


import com.example.demo.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public String getMedicos() {
        return medicoService.getAllMedicos();
    }

    @GetMapping("/{id}")
    public String getMedicoById(Long id) {
        return medicoService.getMedicoById(id);
    }

    @PostMapping
    public String createMedico(String nombre, String especialidad) {
        return medicoService.createMedico(nombre, especialidad);
    }
    @PutMapping("/{id}")
    public String updateMedico(@PathVariable Long id, @RequestBody String nombre, @RequestBody String especialidad) {
        return medicoService.updateMedico(id, nombre, especialidad);
    }
    @DeleteMapping("/{id}")
    public String deleteMedico(@PathVariable Long id) {
        return medicoService.deleteMedico(id);
    }

}
