package com.myproyect.models;

public class Mesero {
    private int id;
    private int empleadoId;
    private String turno;

    public Mesero() {
    }

    public Mesero(int id, int empleadoId, String turno) {
        this.id = id;
        this.empleadoId = empleadoId;
        this.turno = turno;
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

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

}
