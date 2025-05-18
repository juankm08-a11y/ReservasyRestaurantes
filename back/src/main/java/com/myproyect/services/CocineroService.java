package com.myproyect.services;

import java.sql.SQLException;
import java.util.List;

import com.myproyect.models.Cocinero;
import com.myproyect.repositories.CocineroRepository;

public class CocineroService {
    private final CocineroRepository repository;

    public CocineroService(CocineroRepository repository) {
        this.repository = repository;
    }

    public void insertarCocinero(Cocinero c) throws SQLException {
        repository.insertarCocinero(c);
    }

    public List<Cocinero> obtenerTodCocineros() throws SQLException {
        return repository.obtenerTodosLosCocineros();
    }

    public void actualizarCocinero(Cocinero c) throws SQLException {
        repository.actualizarCocinero(c);
    }

    public void eliminarCocinero(int empleadoId) throws SQLException {
        repository.eliminarCocinero(empleadoId);
    }
}
