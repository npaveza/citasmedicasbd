package com.example.citasmedicasbd.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.citasmedicasbd.model.Citamedicabd;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CitamedicabdRepositoryTest {
    @Autowired
    private CitamedicabdRepository citamedicaRepository;

    @Test
    public void guardaCitaTest(){
        Citamedicabd citamedicabd = new Citamedicabd();
        // String con la fecha y hora en formato ISO-8601
        String fechaHoraStr = "2024-09-30T15:30:00";

        citamedicabd.setId(1L);
        citamedicabd.setPaciente("Prueba Paciente");
        citamedicabd.setDoctor("Prueba Doctor");
        try {
            // Convertir el String a LocalDateTime
            LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraStr);
            
            // Establecer la fecha y hora en la entidad citamedicabd
            citamedicabd.setFechaHora(fechaHora);
        } catch (DateTimeParseException e) {
            System.out.println("Error al parsear la fecha y hora: " + e.getMessage());
        }
        citamedicabd.setEspecialidad("Prueba Especialidad");

        Citamedicabd resultado = citamedicaRepository.save(citamedicabd);

        assertNotNull(resultado.getId());
        assertEquals("Prueba Paciente", resultado.getPaciente());
        assertEquals("Prueba Doctor", resultado.getDoctor());
        assertNotNull(resultado.getFechaHora());
        assertEquals("Prueba Especialidad", resultado.getEspecialidad());

    }
}
