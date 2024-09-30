package com.example.citasmedicasbd.model;

import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "citamedica")
public class Citamedicabd extends RepresentationModel<Citamedicabd>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotBlank(message = "No puede ingresar un nombre vacio")
    @Column(name= "paciente")
    private String paciente;
    @NotBlank(message = "No puede ingresar un nombre vacio")
    @Column(name= "doctor")
    private String doctor;
    @Column(name= "fechaHora")
    private LocalDateTime fechaHora;
    @NotBlank(message = "No puede ingresar una especialidad vacia")
    @Column(name= "especialidad")
    private String especialidad;


    public Citamedicabd() {
    }

    public Citamedicabd(Long id, String paciente, String doctor, LocalDateTime fechaHora, String especialidad) {
        this.id = id;
        this.paciente = paciente;
        this.doctor = doctor;
        this.fechaHora = fechaHora;
        this.especialidad = especialidad;
    }
    // Getters y Setters

    public Long getId(){
        return id;
    }

    public String getPaciente(){
        return paciente;
    }

    public String getDoctor(){
        return doctor;
    }

    public LocalDateTime getFechaHora(){
        return fechaHora;
    }

    public String getEspecialidad(){
        return especialidad;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setPaciente(String paciente){
        this.paciente = paciente;
    }

    public void setDoctor(String doctor){
        this.doctor = doctor;
    }

    public void setFechaHora(LocalDateTime fechaHora){
        this.fechaHora = fechaHora;
    }

    public void setEspecialidad(String especialidad){
        this.especialidad = especialidad;
    }
}
