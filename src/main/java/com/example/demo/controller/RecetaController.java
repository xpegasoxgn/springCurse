package com.example.demo.controller;

import com.example.demo.controller.dto.RecetaDTO;
import com.example.demo.controller.dto.RecetaResponseDTO;
import com.example.demo.service.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receta")
public class RecetaController {
    @Autowired
    private RecetaService recetaService;

    @PostMapping
    public ResponseEntity<RecetaResponseDTO> crear(@RequestBody RecetaDTO recetaDTO) {
        return ResponseEntity.ok(recetaService.crear(recetaDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecetaResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(recetaService.obtenerPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<RecetaResponseDTO>> listarTodas() {
        return ResponseEntity.ok(recetaService.listarTodas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecetaResponseDTO> actualizar(
            @PathVariable Long id,
            @RequestBody RecetaDTO recetaDTO
    ) {
        return ResponseEntity.ok(recetaService.actualizar(id, recetaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        recetaService.eliminar(id);
        return ResponseEntity.ok("Receta con ID " + id + " eliminada correctamente");
    }
}
