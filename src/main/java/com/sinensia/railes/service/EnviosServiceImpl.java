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
     * Escoge un tren con vagones suficientes para soportar el peso.
     * 
     * @param peso
     * @return idTren
     */
    private int escogerTrenApropiado(Double peso){
        int vagonesNecesarios = (int) Math.ceil(peso/100);
        List<TrenDTO> listaTrenes = Arrays.asList(restTemplate.getForObject(
            URL_TRENES + "/trenes/" + (vagonesNecesarios) + "/" + (vagonesNecesarios + 10), 
            TrenDTO[].class));

        if (!listaTrenes.isEmpty()){
            listaTrenes = listaTrenes.stream()
                .sorted((tren1, tren2) -> tren1.getVagones() - tren2.getVagones())
                .toList();

            return listaTrenes.get(0).getIdTren();
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
