package com.example.citasmedicasbd.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;

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

    private Citamedicabd cita1, cita2, cita3;

    @BeforeEach
    public void setUp() {
        // Creación de la primera cita (ID: 1)
        cita1 = new Citamedicabd();
        cita1.setId(1L);
        cita1.setPaciente("Paciente 1");
        cita1.setDoctor("Doctor 1");
        cita1.setFechaHora(LocalDateTime.parse("2024-09-30T15:30:00"));
        cita1.setEspecialidad("Especialidad 1");

        // Creación de la segunda cita (ID: 2)
        cita2 = new Citamedicabd();
        cita2.setId(2L);
        cita2.setPaciente("Paciente 2");
        cita2.setDoctor("Doctor 2");
        cita2.setFechaHora(LocalDateTime.parse("2024-10-01T10:00:00"));
        cita2.setEspecialidad("Especialidad 2");

        // Creación de la tercera cita (ID: 3)
        cita3 = new Citamedicabd();
        cita3.setId(3L);
        cita3.setPaciente("Paciente 3");
        cita3.setDoctor("Doctor 3");
        cita3.setFechaHora(LocalDateTime.parse("2024-10-02T14:00:00"));
        cita3.setEspecialidad("Especialidad 3");
    }

    @AfterEach
    public void tearDown() {
        cita1 = cita2 = cita3 = null;
    }

    @Test
    public void guardaCitaTest() {
        when(citamedicabdRepositoryMock.findById(1L)).thenReturn(Optional.empty());
        when(citamedicabdRepositoryMock.save(any())).thenReturn(cita1);

        if (citamedicabdRepositoryMock.findById(1L).isEmpty()) {
            Citamedicabd resultado1 = citamedicabdService.createCita(cita1);

            assertNotNull(resultado1.getId());
            assertEquals("Paciente 1", resultado1.getPaciente());
            assertEquals("Doctor 1", resultado1.getDoctor());
            assertNotNull(resultado1.getFechaHora());
            assertEquals("Especialidad 1", resultado1.getEspecialidad());

            verify(citamedicabdRepositoryMock, times(1)).save(cita1);
        }

        when(citamedicabdRepositoryMock.save(any())).thenReturn(cita2);
        Citamedicabd resultado2 = citamedicabdService.createCita(cita2);

        assertNotNull(resultado2.getId());
        assertEquals("Paciente 2", resultado2.getPaciente());
        assertEquals("Doctor 2", resultado2.getDoctor());
        assertNotNull(resultado2.getFechaHora());
        assertEquals("Especialidad 2", resultado2.getEspecialidad());

        when(citamedicabdRepositoryMock.save(any())).thenReturn(cita3);
        Citamedicabd resultado3 = citamedicabdService.createCita(cita3);

        assertNotNull(resultado3.getId());
        assertEquals("Paciente 3", resultado3.getPaciente());
        assertEquals("Doctor 3", resultado3.getDoctor());
        assertNotNull(resultado3.getFechaHora());
        assertEquals("Especialidad 3", resultado3.getEspecialidad());
    }

    @Test
    public void modificaCitaTest() {
    if (citamedicabdRepositoryMock.findById(2L).isPresent()) {
        cita2.setPaciente("Paciente 2 Modificado");
        cita2.setDoctor("Doctor 2 Modificado");

        when(citamedicabdRepositoryMock.save(any(Citamedicabd.class))).thenReturn(cita2);

        when(citamedicabdService.updateCita(2L, cita2)).thenReturn(cita2);

        Citamedicabd resultado = citamedicabdService.updateCita(2L, cita2);

        assertNotNull(resultado, "El resultado no debería ser null");
        assertEquals("Paciente 2 Modificado", resultado.getPaciente());
        assertEquals("Doctor 2 Modificado", resultado.getDoctor());

        // Verificar que se llamó al método save una vez
        verify(citamedicabdRepositoryMock, times(1)).save(cita2);
        }
    }

    @Test
    public void eliminaCitaTest() {
        // Simulación de encontrar cita con ID 3
        when(citamedicabdRepositoryMock.findById(3L)).thenReturn(Optional.of(cita3));

        if (citamedicabdRepositoryMock.findById(3L).isPresent()) {
            doNothing().when(citamedicabdRepositoryMock).deleteById(3L);

            citamedicabdService.deleteCita(3L);

            verify(citamedicabdRepositoryMock, times(1)).deleteById(3L);
        }
    }
}