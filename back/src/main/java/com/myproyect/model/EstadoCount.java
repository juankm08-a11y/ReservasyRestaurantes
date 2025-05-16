package com.myproyect.model;

public class EstadoCount {
    private String estado;
    private int total;

    public EstadoCount(String estado, int total) {
        this.estado = estado;
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public int getTotal() {
        return total;
    }
}
