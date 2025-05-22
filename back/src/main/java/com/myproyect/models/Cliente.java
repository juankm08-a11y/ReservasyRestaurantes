package com.myproyect.models;

public class Cliente {
    private int cliente_id;
    private String nombre;
    private String cedula;
    private String telefono;
    private String email;

    public Cliente() {
    }

    public Cliente(int cliente_id, String nombre, String cedula, String telefono, String email) {
        this.cliente_id = cliente_id;
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
        this.email = email;
    }

    public int getCliente_Id() {
        return cliente_id;
    }

    public void setCliente_Id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
