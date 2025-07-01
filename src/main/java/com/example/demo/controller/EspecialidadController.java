package com.example.demo.controller;


import com.example.demo.model.medico.Especialidad;
import com.example.demo.service.EspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadController {
    @Autowired
    private EspecialidadService especialidadService;

    @PostMapping("crear")
    public String createEspecialidad(@RequestBody Especialidad especialidad) {
        try {
            especialidadService.createEspecialidad(especialidad);
            return "Especialidad creada exitosamente";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al crear la especialidad: " + e.getMessage();
        }
    }

    @PostMapping("editar")
    public String updateEspecialidad(@RequestBody Especialidad especialidad) {
        try {
            especialidadService.updateEspecialidad(especialidad);
            return "Especialidad actualizada exitosamente";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al actualizar la especialidad: " + e.getMessage();
        }
    }
}
