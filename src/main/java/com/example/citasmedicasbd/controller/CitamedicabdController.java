package com.example.citasmedicasbd.controller;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.citasmedicasbd.model.Citamedicabd;
import com.example.citasmedicasbd.service.CitamedicabdService;

@RestController
@RequestMapping("/citasmedicas")
public class CitamedicabdController {
    private static final Logger log = LoggerFactory.getLogger(CitamedicabdController.class);
    @Autowired
    private CitamedicabdService citamedicabdService;

    @GetMapping
    public CollectionModel<EntityModel<Citamedicabd>> getAllCitasMedicasbds() {
        List<Citamedicabd> citamedicabds = citamedicabdService.getAllCitasMedicasbds();
        log.info("GET /citasmedicas");
        log.info("Retornando todas las citas");
        List<EntityModel<Citamedicabd>> citamedicaResources = citamedicabds.stream()
            .map( citamedicabd -> EntityModel.of(citamedicabd,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getCitasMedicasbdById(citamedicabd.getId())).withSelfRel()
            ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllCitasMedicasbds());
        CollectionModel<EntityModel<Citamedicabd>> resources = CollectionModel.of(citamedicaResources, linkTo.withRel("citas"));

        return resources;
    }

    @GetMapping("/{id}")
    public EntityModel<Citamedicabd> getCitasMedicasbdById(@PathVariable Long id) {
        Optional<Citamedicabd> citamedicabd = citamedicabdService.getCitasMedicasbdById(id);

        if (citamedicabd.isPresent()) {
            return EntityModel.of(citamedicabd.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getCitasMedicasbdById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllCitasMedicasbds()).withRel("Toda-CitaMedica"));
        } else {
            throw new CitaNotFoundException("Cita not found with id: " + id);
        }
    }

    @PostMapping
    public EntityModel<Citamedicabd> createCita(@Validated @RequestBody Citamedicabd citamedicabd) {
        Citamedicabd createCita = citamedicabdService.createCita(citamedicabd);
            return EntityModel.of(createCita,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getCitasMedicasbdById(createCita.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllCitasMedicasbds()).withRel("Toda-CitaMedica"));

    }

    @PutMapping("/{id}")
    public EntityModel<Citamedicabd> updateCita(@PathVariable Long id, @RequestBody Citamedicabd citamedicabd) {
        Citamedicabd updateCita = citamedicabdService.updateCita(id, citamedicabd);
        return EntityModel.of(updateCita,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getCitasMedicasbdById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllCitasMedicasbds()).withRel("Todas-las-Citas"));

    }

    @DeleteMapping("/{id}")
    public void deleteCita(@PathVariable Long id){
        citamedicabdService.deleteCita(id);
    }


    static class ErrorResponse {
        private final String message;
    
        public ErrorResponse(String message) {
            this.message = message;
        }
    
        public String getMessage() {
            return message;
        }
    }

}