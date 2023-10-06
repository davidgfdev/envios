package com.sinensia.railes.dto;

public class EstacionDTO {
    private int idEstacion;
    private String nombreEstacion;
    private String ubicacion;
    
    /**
     * Constructor por defecto.
     */
    public EstacionDTO() {
    }

    /**
     * Constructor con ID.
     * 
     * @param idEstacion
     */
    public EstacionDTO(int idEstacion) {
        this.idEstacion = idEstacion;
    }

    /**
     * Constructor con parámetros.
     * 
     * @param idEstacion
     * @param nombreEstacion
     * @param ubicacion
     */
    public EstacionDTO(int idEstacion, String nombreEstacion, String ubicacion) {
        this.idEstacion = idEstacion;
        this.nombreEstacion = nombreEstacion;
        this.ubicacion = ubicacion;
    }

    public int getIdEstacion() {
        return idEstacion;
    }

    public void setIdEstacion(int idEstacion) {
        this.idEstacion = idEstacion;
    }

    public String getNombreEstacion() {
        return nombreEstacion;
    }

    public void setNombreEstacion(String nombreEstacion) {
        this.nombreEstacion = nombreEstacion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
