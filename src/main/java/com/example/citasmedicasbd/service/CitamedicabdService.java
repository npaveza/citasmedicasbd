package com.example.citasmedicasbd.service;

import java.util.List;
import java.util.Optional;

import com.example.citasmedicasbd.model.Citamedicabd;

public interface CitamedicabdService {
    List<Citamedicabd> getAllCitasMedicasbds();
    Optional<Citamedicabd> getCitasMedicasbdById(Long id);
    Citamedicabd createCita(Citamedicabd citamedicabd);
    Citamedicabd updateCita(Long id, Citamedicabd citamedicabd);
    void deleteCita(Long id);
}
