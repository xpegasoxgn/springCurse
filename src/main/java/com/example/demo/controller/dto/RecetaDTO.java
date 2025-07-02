package com.example.demo.controller.dto;

import java.util.Date;
import java.util.List;

public class RecetaDTO {

    private String nombrePaciente;
    private String ci;
    private Date fechaNacimiento;
    private String diagnostico;
    private String indicaciones;
    private List<MedicamentoDTO> medicamentos;
}
