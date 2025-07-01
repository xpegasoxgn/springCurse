package com.example.demo.service;

import com.example.demo.model.medico.Medico;
import java.util.List;

public interface MedicoService {
     List<Medico> listaMedicos();
     Medico getMedicoById(Long id) ;
     Medico createMedico(Medico medico) ;
     Medico updateMedico(Long id, Medico medico) ;
     void deleteMedico(Long id) ;
}