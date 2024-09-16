package com.example.citasmedicasbd.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.citasmedicasbd.model.Citamedicabd;
import com.example.citasmedicasbd.repository.CitamedicabdRepository;

@Service
public class CitamedicabdServiceImpl implements CitamedicabdService{
    @Autowired
    private CitamedicabdRepository citamedicabdRepository;

    @Override
    public List<Citamedicabd> getAllCitasMedicasbds(){
        return citamedicabdRepository.findAll();
    }

    @Override
    public Optional<Citamedicabd> getCitasMedicasbdById(Long id){
        return citamedicabdRepository.findById(id);
    }

    @Override
    public Citamedicabd createCita(Citamedicabd citamedicabd) {
        return citamedicabdRepository.save(citamedicabd);
    }

    @Override
    public Citamedicabd updateCita(Long id, Citamedicabd citamedicabd) {
        if(citamedicabdRepository.existsById(id)){
            citamedicabd.setId(id);
            return citamedicabdRepository.save(citamedicabd);
        } else {
            return null;
        }
    }

    @Override
    public void deleteCita(Long id) {
        citamedicabdRepository.deleteById(id);
    }
}
