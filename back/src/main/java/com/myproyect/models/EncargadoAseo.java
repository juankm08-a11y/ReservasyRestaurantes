package com.myproyect.models;

public class EncargadoAseo {
    private int id;
    private int empleadoId;
    private String zonaAsignada;

    public EncargadoAseo(int id, int empleadoId, String zonaAsignada) {
        this.id = id;
        this.empleadoId = empleadoId;
        this.zonaAsignada = zonaAsignada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public String getZonaAsignada() {
        return zonaAsignada;
    }

    public void setZonaAsignada(String zonaAsignada) {
        this.zonaAsignada = zonaAsignada;
    }

}