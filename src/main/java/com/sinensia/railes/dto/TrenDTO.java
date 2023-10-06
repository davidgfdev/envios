package com.sinensia.railes.dto;

public class TrenDTO {
    private int idTren;
    private String modeloTren;
    private String mercancia;
    private int vagones;

    /**
     * Constructor vacío.
     */
    public TrenDTO() {
    }

    /**
     * Constructor con ID.
     * 
     * @param Id
     */
    public TrenDTO(int idTren) {
        this.idTren = idTren;
    }

    /**
     * Constructor con parámetros.
     * 
     * @param idTren
     * @param modeloTren
     * @param mercancia
     * @param vagones
     */
    public TrenDTO(int idTren, String modeloTren, String mercancia, int vagones) {
        this.idTren = idTren;
        this.modeloTren = modeloTren;
        this.mercancia = mercancia;
        this.vagones = vagones;
    }

    public int getIdTren() {
        return idTren;
    }

    public void setIdTren(int idTren) {
        this.idTren = idTren;
    }

    public String getModeloTren() {
        return modeloTren;
    }

    public void setModeloTren(String modeloTren) {
        this.modeloTren = modeloTren;
    }

    public String getMercancia() {
        return mercancia;
    }

    public void setMercancia(String mercancia) {
        this.mercancia = mercancia;
    }

    public int getVagones() {
        return vagones;
    }

    public void setVagones(int vagones) {
        this.vagones = vagones;
    }
}
