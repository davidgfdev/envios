package com.sinensia.railes.service;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sinensia.railes.dao.EnviosDao;
import com.sinensia.railes.dto.EstacionDTO;
import com.sinensia.railes.dto.TrenDTO;
import com.sinensia.railes.model.Envio;

@Service
public class EnviosServiceImpl implements EnviosService{
    
    @Autowired
    EnviosDao enviosDao;

    @Autowired
    RestTemplate restTemplate;

    private static final String URL_ESTACIONES = "http://localhost:8080/";
    private static final String URL_TRENES = "http://localhost:9000/";
    private static final int VAGONES_TOLERANCIA = 10;

    /**
     * Da de alta un nuevo envío. Este nuevo envío escojerá un tren y estación adecuados basándose en lo siguiente:
	 * Cada 100kg es un vagón.
	 * Se escogerá la primera estación encontrada en la ubicación especificada.
     * 
     * @param nombreEstacion
     * @param destino
     * @param peso
     */
    @Override
    public void altaEnvio(int idEnvio, String nombreEstacion, String destino, double peso) {   
        Envio envio = new Envio(
            idEnvio, 
            obtenerIdEstacion(nombreEstacion), 
            obtenerEstacionMasCercana(destino), 
            escogerTrenApropiado(peso), 
            peso, 
            Date.from(Instant.now()));

        if (envio.isValid()) enviosDao.save(envio);
    }

    /**
     * Lista todos los envíos.
     * 
     * @return listaEnvios
     */
    @Override
    public List<Envio> envios() {
        return enviosDao.findAll();
    }

    /**
     * Elimina un envío, si lo encuentra.
     * 
     * @param idEnvio
     */
    @Override
    public List<Envio> deleteEnvio(int idEnvio){
        enviosDao.deleteById(idEnvio);
        return enviosDao.findAll();
    }

    /**
     * Escoge un tren con vagones suficientes para soportar el peso.
     * 
     * @param peso
     * @return idTren
     */
    private int escogerTrenApropiado(Double peso){

        // Calcula los vagones necesarios para el peso. 1 vagón puede llevar 100kg.
        int vagonesNecesarios = (int) Math.ceil(peso/100);

        // Obtiene una lista de trenes que tengan ese vagón y 10 más como tolerancia.
        List<TrenDTO> listaTrenesConVagones = Arrays.asList(restTemplate.getForObject(
            URL_TRENES + "trenes/" + (vagonesNecesarios) + "/" + (vagonesNecesarios + VAGONES_TOLERANCIA), 
            TrenDTO[].class)
        );

        // Si le llegan trenes entre esos vagones, utilizará el primer tren que encuentre.
        if (!listaTrenesConVagones.isEmpty()){
            return listaTrenesConVagones.stream()
                .sorted((tren1, tren2) -> tren1.getVagones() - tren2.getVagones())
                .toList()
                .get(0)
                .getIdTren();
        } else {

            // Si no hay trenes entre esos vagones, 
            // modificará el primer tren disponible añadiendole vagones.
            List<TrenDTO> listaTrenes = Arrays.asList(restTemplate.getForObject(
                URL_TRENES + "trenes",
                TrenDTO[].class)
            );

            if (!listaTrenes.isEmpty()){
                int trenModificable = listaTrenes.get(0).getIdTren();
                restTemplate.put(URL_TRENES + "tren/" + trenModificable + "/" + vagonesNecesarios, null);
                return trenModificable;
            }
        }

        return -1;
    }

    /**
     * Obtiene el ID de la estaación con el nombre designado.
     * 
     * @param nombreEstacion
     * @return idEstacion
     */
    private int obtenerIdEstacion(String nombreEstacion){
        EstacionDTO estacionOrigen = restTemplate.getForObject(
            URL_ESTACIONES + "estacion/" + nombreEstacion,
            EstacionDTO.class);

        if (estacionOrigen != null){
            return estacionOrigen.getIdEstacion();
        }

        return -1;
    }

    /**
     * Obtiene la primera estación que exista en el destino especificado.
     * 
     * @param destino
     * @return idEstacion
     */
    private int obtenerEstacionMasCercana(String destino){
        List<EstacionDTO> estacionesDestino = Arrays.asList(restTemplate.getForObject(
            URL_ESTACIONES + "estaciones/" + destino, 
            EstacionDTO[].class));

        if (!estacionesDestino.isEmpty()){
            return estacionesDestino.get(0).getIdEstacion();
        }

        return -1;
    }
}
