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

import com.example.demo.model.Historial;
import com.example.demo.service.HistorialService;

@RestController
@RequestMapping("/api_historial")
public class HistorialController {

    @Autowired
    private HistorialService historialService;

    @GetMapping("/historial")
    public ResponseEntity<List<Historial>> getHistorials() {
        try {
            return ResponseEntity.ok(historialService.getHistoriales());
        } 
        catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/historial")
    public ResponseEntity<Historial> addHistorial(@RequestBody Historial historial) {
        try {
            return ResponseEntity.ok(historialService.addHistorial(historial));
        } 
        catch (Exception e) {
            return null;
        }
    }

    @PutMapping("/historial/{id}")
    public ResponseEntity<String> editHistorial(@PathVariable Long id, @RequestBody Historial historial) {
        try {
            return ResponseEntity.ok(historialService.editHistorial( id, historial));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/historial/{id}")
    public ResponseEntity<String> deleteHistorial(@PathVariable Long id) {
        try {
            historialService.deleteHistorial(id);
            return ResponseEntity.ok("Historial eliminado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    
}
