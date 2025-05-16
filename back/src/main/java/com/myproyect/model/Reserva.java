package com.myproyect.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reserva {
    private int id;
    private int clienteId;
    private int mesaId;
    private LocalDate fecha;
    private LocalTime hora;
    private String estado;

    public Reserva() {
    }

    public Reserva(int id, int clienteId, int mesaId, LocalDate fecha, LocalTime hora, String estado) {
        this.id = id;
        this.clienteId = clienteId;
        this.mesaId = mesaId;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getMesaId() {
        return mesaId;
    }

    public void setMesaId(int mesaId) {
        this.mesaId = mesaId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
