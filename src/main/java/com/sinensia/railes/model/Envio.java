package com.sinensia.railes.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "envios")
public class Envio {
    @Id
    private int idEnvio;
    private int idEstacionInicial;
    private int idEstacionDestino;
    private int idTren;
    private double peso;
    private Date fecha;
    
    /**
     * Constructor vacío.
     */
    public Envio() {
    }

    /**
     * Constructor con ID.
     * 
     * @param idEnvio
     */
    public Envio(int idEnvio) {
        this.idEnvio = idEnvio;
    }

    /**
     * Constructor con parámetros.
     * 
     * @param idEnvio
     * @param idEstacionInicial
     * @param idEstacionDestino
     * @param idTren
     * @param peso
     * @param fecha
     */
    public Envio(int idEnvio, int idEstacionInicial, int idEstacionDestino, int idTren, double peso, Date fecha) {
        this.idEnvio = idEnvio;
        this.idEstacionInicial = idEstacionInicial;
        this.idEstacionDestino = idEstacionDestino;
        this.idTren = idTren;
        this.peso = peso;
        this.fecha = fecha;
    }

    public int getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(int idEnvio) {
        this.idEnvio = idEnvio;
    }

    public int getIdEstacionInicial() {
        return idEstacionInicial;
    }

    public void setIdEstacionInicial(int idEstacionInicial) {
        this.idEstacionInicial = idEstacionInicial;
    }

    public int getIdEstacionDestino() {
        return idEstacionDestino;
    }

    public void setIdEstacionDestino(int idEstacionDestino) {
        this.idEstacionDestino = idEstacionDestino;
    }

    public int getIdTren() {
        return idTren;
    }

    public void setIdTren(int idTren) {
        this.idTren = idTren;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isValid(){
        if (idEnvio == -1)  return false; 
        if (idEstacionInicial == -1)  return false; 
        if (idEstacionDestino == -1)  return false; 
        return (idTren != -1); 
    }
}
