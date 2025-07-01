package com.example.demo.service.impl;

import com.example.demo.model.medico.Medico;
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
        Medico newmedico = new Medico();
        newmedico.setNombre(nombre);
        newmedico.setEspecialidad(especialidad);
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
