package com.myproyect.model;

public class Cocinero {
    private int id;
    private int empleadoId;
    private String especialidad;

    public Cocinero(int id, int empleadoId, String especialidad) {
        this.id = id;
        this.empleadoId = empleadoId;
        this.especialidad = especialidad;
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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

}
