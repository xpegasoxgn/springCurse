package com.example.demo.service.impl;

import com.example.demo.model.medico.Especialidad;
import com.example.demo.repository.medico.EspecialidadRepository;
import com.example.demo.service.EspecialidadService;
import com.example.demo.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EspecialidadServiceImpl implements EspecialidadService {
    @Autowired
    private EspecialidadRepository especialidadRepository;
    @Override
    public Especialidad createEspecialidad(Especialidad especialidad) {
        return especialidadRepository.save(especialidad);
    }
    @Override
    public Especialidad updateEspecialidad(Especialidad especialidad) {
        if (especialidadRepository.existsById(especialidad.getId())) {
            return especialidadRepository.save(especialidad);
        }
        return null;
    }
}
