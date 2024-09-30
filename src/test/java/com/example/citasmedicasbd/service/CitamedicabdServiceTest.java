package com.example.citasmedicasbd.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.citasmedicasbd.model.Citamedicabd;
import com.example.citasmedicasbd.repository.CitamedicabdRepository;

@ExtendWith(MockitoExtension.class)
public class CitamedicabdServiceTest {
    @InjectMocks
    private CitamedicabdServiceImpl citamedicabdService;

    @Mock
    private CitamedicabdRepository citamedicabdRepositoryMock;

    @BeforeEach
    public void setUp() {
        Citamedicabd citamedicabd = new Citamedicabd();
        citamedicabd.setId(1L);
        citamedicabd.setPaciente("Prueba Paciente");
        citamedicabd.setDoctor("Prueba Doctor");

        // String con la fecha y hora en formato ISO-8601
        String fechaHoraStr = "2024-09-30T15:30:00";
        try {
            // Convertir el String a LocalDateTime
            LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraStr);
            // Establecer la fecha y hora en la entidad citamedicabd
            citamedicabd.setFechaHora(fechaHora);
        } catch (DateTimeParseException e) {
            System.out.println("Error al parsear la fecha y hora: " + e.getMessage());
        }
        citamedicabd.setEspecialidad("Prueba Especialidad");
    }

    @AfterEach
    public void tearDown() {
        Citamedicabd citamedicabd = null; // Liberar recursos si es necesario (en este caso solo limpiamos la referencia)
    }

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


        when(citamedicabdRepositoryMock.save(any())).thenReturn(citamedicabd);

        Citamedicabd resultado = citamedicabdService.createCita(citamedicabd);

        assertNotNull(resultado.getId());
        assertEquals("Prueba Paciente", resultado.getPaciente());
        assertEquals("Prueba Doctor", resultado.getDoctor());
        assertNotNull(resultado.getFechaHora());
        assertEquals("Prueba Especialidad", resultado.getEspecialidad());

        verify(citamedicabdRepositoryMock, times(1)).save(any());
    }
}
