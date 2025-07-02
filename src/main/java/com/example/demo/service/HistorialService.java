package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Historial;

public interface HistorialService {

    public List<Historial> getHistoriales() throws Exception;

    public Historial addHistorial(Historial historial) throws Exception;

    public String editHistorial(Long id, Historial historial) throws Exception;

    public String deleteHistorial(Long id) throws Exception;
    
}
