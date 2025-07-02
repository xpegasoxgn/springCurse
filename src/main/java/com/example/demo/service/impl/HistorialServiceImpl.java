package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Historial;
import com.example.demo.repository.HistorialRepository;
import com.example.demo.service.HistorialService;

@Service
public class HistorialServiceImpl implements HistorialService{

    @Autowired
    HistorialRepository historialRepository;

    @Override
    public List<Historial> getHistoriales() throws Exception{
        return historialRepository.findAll();
    }

    @Override
    public Historial addHistorial(Historial historial) throws Exception{
        return historialRepository.save(historial);
    }

    @Override
    public String editHistorial(Long id, Historial historial) throws Exception{
        Optional<Historial> historialExistente = historialRepository.findById(id);
        if (historialExistente.isEmpty()) {
            throw new Exception("Historial no encontrado con id: " + id);
        }
        Historial historialToUpdate = historialExistente.get();

        historialToUpdate.setCodigo(historial.getCodigo());
        historialToUpdate.setFecha(historial.getFecha());
        historialToUpdate.setDiagnostico(historial.getDiagnostico());
        historialToUpdate.setMotivo(historial.getMotivo());

        historialRepository.save(historialToUpdate);
        return "Historial actualizado correctamente";
    }

    @Override
    public String deleteHistorial(Long id) throws Exception{
        Optional<Historial> historialExistente = historialRepository.findById(id);
        if (historialExistente.isEmpty()) {
            throw new Exception("Historial no encontrado con id: " + id);
        }
        historialRepository.deleteById(id);
        return "Historial eliminado correctamente";
    }
}
