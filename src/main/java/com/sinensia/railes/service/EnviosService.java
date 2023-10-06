package com.sinensia.railes.service;

import java.util.List;

import com.sinensia.railes.model.Envio;

public interface EnviosService {
    void altaEnvio(int idEnvio, String nombreEstacion, String destino, double peso);
    List<Envio> envios();
}
