package com.myproyect.controllers;

import java.sql.SQLException;
import java.util.List;

import com.myproyect.models.Empleado;
import com.myproyect.services.EmpleadoService;

public class EmpleadoController {
    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    public void crear(Empleado empleado) throws SQLException {
        empleadoService.insertarEmpleado(empleado);
    }

    public List<Empleado> listar() throws SQLException {
        return empleadoService.obtenerTodosLostEmpleados();
    }

    public Empleado ver(int id) throws SQLException {
        return empleadoService.obtenerEmpleadosPorId(id);
    }

    public void actualizar(Empleado empleado) throws SQLException {
        empleadoService.actualizarEmpleado(empleado);
    }

    public void eliminar(int id) throws SQLException {
        empleadoService.eliminarEmpleado(id);
    }
}
