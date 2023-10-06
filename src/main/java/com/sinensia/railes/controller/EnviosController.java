package com.sinensia.railes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    private static final String JSON_MEDIA_TYPE = MediaType.APPLICATION_JSON_VALUE;

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
    @GetMapping(value = "envios", produces = JSON_MEDIA_TYPE)
    public List<Envio> envios(){
        return enviosService.envios();
    }

    /**
     * Elimina un envío.
     * 
     * @param idEnvio
     * @return listaEnvios
     */
    @DeleteMapping(value = "envio/{idEnvio}", produces = JSON_MEDIA_TYPE)
    public List<Envio> eliminarEnvio(@PathVariable int idEnvio){
        return enviosService.deleteEnvio(idEnvio);
    }
}
