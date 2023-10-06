package com.sinensia.railes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinensia.railes.model.Envio;
import com.sinensia.railes.service.EnviosService;

@RestController
public class EnviosController {
    
    @Autowired
    EnviosService enviosService;

    /**
     * Da de alta en BD un envío nuevo.
     * 
     * @param idEnvio
     * @param nombreEstacion
     * @param destino
     * @param peso
     */
    @PostMapping(value = "envio/{idEnvio}/{nombreEstacion}/{destino}/{peso}")
    public void altaEnvio(
        @PathVariable int idEnvio,
        @PathVariable String nombreEstacion,
        @PathVariable String destino,
        @PathVariable double peso
    ){
        enviosService.altaEnvio(idEnvio, nombreEstacion, destino, peso);
    }

    /**
     * Lista todos los envios que hay en la BD.
     * 
     * @return listaEnvios
     */
    @GetMapping(value = "envios")
    public List<Envio> envios(){
        return enviosService.envios();
    }
}
