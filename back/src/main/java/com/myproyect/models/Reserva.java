package com.myproyect.models;

import java.time.LocalTime;

public class Reserva {
    private int reservaId;
    private int clienteId;
    private int mesaId;
    private String mes_nombre;
    private String dia_nombre;
    private LocalTime hora;
    private String estado;

    public Reserva() {
    }

    public Reserva(int reservaId, int clienteId, int mesaId, String mes_nombre, String dia_nombre, LocalTime hora,
            String estado) {
        this.reservaId = reservaId;
        this.clienteId = clienteId;
        this.mesaId = mesaId;
        this.mes_nombre = mes_nombre;
        this.dia_nombre = dia_nombre;
        this.hora = hora;
        this.estado = estado;
    }

    public int getReservaId() {
        return reservaId;
    }

    public void setReservaId(int reservaId) {
        this.reservaId = reservaId;
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

    public String getMes_Nombre() {
        return mes_nombre;
    }

    public void setMes_Nombre(String mes_nombre) {
        this.mes_nombre = mes_nombre;
    }

    public String getDia_nombre() {
        return dia_nombre;
    }

    public void setDia_nombre(String dia_nombre) {
        this.dia_nombre = dia_nombre;
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
