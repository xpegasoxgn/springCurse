package com.example.demo.model.auth;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="usuarios", schema="authjwt")
public class Usuarios {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String paterno;
    private String materno;
    private String username;
    private String password;
    private String email;
    private boolean activo;
    
    @Column (name="fecha_creacion")
    private LocalDateTime fec_creacion=LocalDateTime.now();

    @ManyToMany (fetch=FetchType.EAGER)
    @JoinTable(
        name="usuario_rol", schema="authjwt",
        joinColumns=@JoinColumn(name="usuario_id"),
        inverseJoinColumns=@JoinColumn(name="rol_id")
    )
    private Set<Rol>roles;

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

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public LocalDateTime getFec_creacion() {
        return fec_creacion;
    }

    public void setFec_creacion(LocalDateTime fec_creacion) {
        this.fec_creacion = fec_creacion;
    }


    
    
}
