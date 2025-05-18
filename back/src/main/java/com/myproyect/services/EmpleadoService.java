package com.myproyect.services;

import java.sql.SQLException;
import java.util.List;

import com.myproyect.models.Empleado;
import com.myproyect.repositories.EmpleadoRepository;

public class EmpleadoService {
    private final EmpleadoRepository repository;

    public EmpleadoService(EmpleadoRepository repository) {
        this.repository = repository;
    }

    public void insertarEmpleado(Empleado e) throws SQLException {
        repository.insertarEmpleado(e);
    }

    public List<Empleado> obtenerTodosLostEmpleados() throws SQLException {
        return repository.obtenerTodosLosEmpleados();
    }

    public Empleado obtenerEmpleadosPorId(int id) throws SQLException {
        return repository.obtenerEmpleadoPorId(id);
    }

    public void actualizarEmpleado(Empleado e) throws SQLException {
        repository.actualizarEmpleado(e);
    }

    public void eliminarEmpleado(int id) throws SQLException {
        repository.eliminarEmpleado(id);
    }

}
