package com.example.demo.controller;

import com.example.demo.controller.dto.MedicamentoDTO;
import com.example.demo.controller.dto.MedicamentoResponseDTO;
import com.example.demo.model.Medicamento;
import com.example.demo.service.MedicamentoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicamento")
public class MedicamentoController {
    @Autowired
    private MedicamentoService medicamentoService;

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody MedicamentoDTO request,
                                   HttpServletRequest httpRequest) {
        try {
            // Obtiene el username del request (como lo haces en AuthController)
            String username = httpRequest.getUserPrincipal().getName();

            medicamentoService.crear(request, username);
            return ResponseEntity.ok("Medicamento registrado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity<List<MedicamentoResponseDTO>> listarTodos() {
        try {
            return ResponseEntity.ok(medicamentoService.listarTodos());
        } catch (Exception e) {
            throw new RuntimeException("Error al acceder a la base de datos");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable Long id,
            @RequestBody MedicamentoDTO request,
            HttpServletRequest httpRequest) {
        try {
            String username = httpRequest.getUserPrincipal().getName();
            MedicamentoResponseDTO medicamentoActualizado = medicamentoService.actualizar(id, request);
            return ResponseEntity.ok(medicamentoActualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            medicamentoService.eliminar(id);
            return ResponseEntity.ok("Medicamento eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

}
