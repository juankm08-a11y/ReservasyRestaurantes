package com.myproyect.services;

import java.sql.SQLException;
import java.util.List;
import com.myproyect.models.Mesero;
import com.myproyect.repositories.MeseroRepository;

public class MeseroService {
    private final MeseroRepository repository;

    public MeseroService(MeseroRepository repository) {
        this.repository = repository;
    }

    public void insertarMesero(Mesero m) throws SQLException {
        repository.insertarMesero(m);
    }

    public List<Mesero> obtenerTodosLosMeseros() throws SQLException {
        return repository.obtenerTodosLosMeseros();
    }

    public void actualizarMesero(Mesero m) throws SQLException {
        repository.actualizarMesero(m);
    }

    public void eliminarMesero(int empleadoId) throws SQLException {
        repository.eliminarMesero(empleadoId);
    }
}
