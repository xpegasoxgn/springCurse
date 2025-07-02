package com.example.demo.controller.dto;

import java.util.Date;

public class MedicamentoResponseDTO {
    private Long id;
    private String nombre;
    private Double precio;
    private Date fechaVencimiento;
    private String dosis;
    private String fabricante;
    private UsuarioMinimoDTO usuario; // Solo información básica del usuario

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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public UsuarioMinimoDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioMinimoDTO usuario) {
        this.usuario = usuario;
    }

    public static class UsuarioMinimoDTO {
            private Long id;
            private String nombre;
            private String username;

        // Getters y Setters
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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
