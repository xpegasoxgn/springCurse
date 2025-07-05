package com.example.demo.service.impl;

import com.example.demo.model.medico.Especialidad;
import com.example.demo.model.medico.Medico;
import com.example.demo.repository.medico.EspecialidadRepository;
import com.example.demo.repository.medico.MedicoRepository;
import com.example.demo.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
@Service
public class MedicoServiceImpl implements MedicoService {
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private EspecialidadRepository especialidadRepository;
    @Override
    public List<Medico> listaMedicos() {
        return medicoRepository.findAll();
    }

    @Override
    public Medico getMedicoById(Long id) {
        return medicoRepository.findById(id).orElse(null);
    }

    @Override
    public Medico createMedico(Medico medico) {
        Especialidad especialidad = especialidadRepository
                .findById(medico.getEspecialidad().getId())
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));

        medico.setEspecialidad(especialidad);
        return medicoRepository.save(medico);
    }


    @Override
    public Medico updateMedico(Long id, Medico medico) {
        if (medicoRepository.existsById(id)) {
            medico.setId(id);
            return medicoRepository.save(medico);
        }
        return null;
    }

    @Override
    public void deleteMedico(Long id) {
        medicoRepository.deleteById(id);
    }
}
