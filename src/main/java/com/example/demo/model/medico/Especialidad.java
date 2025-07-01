package com.example.demo.model.medico;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "especialidades", schema = "medico")
public class Especialidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @OneToMany(mappedBy = "especialidad")
    private List<Medico> medicos;
    public List<Medico> getMedicos() {
        return medicos;
    }
    public void setMedicos(List<Medico> medicos) {
        this.medicos = medicos;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
