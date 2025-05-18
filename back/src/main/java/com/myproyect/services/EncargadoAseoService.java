package com.myproyect.services;

import java.sql.SQLException;
import java.util.List;
import com.myproyect.models.EncargadoAseo;
import com.myproyect.repositories.EncargadoAseoRepository;

public class EncargadoAseoService {
    private final EncargadoAseoRepository repository;

    public EncargadoAseoService(EncargadoAseoRepository repository) {
        this.repository = repository;
    }

    public void insertarEncargadoAseo(EncargadoAseo e) throws SQLException {
        repository.insertarEncargadoAseo(e);
    }

    public List<EncargadoAseo> obtenerTodosLosEncargados() throws SQLException {
        return repository.obtenerTodosLosEncargados();
    }

    public void actualizarEncargadoAseo(EncargadoAseo e) throws SQLException {
        repository.actualizarEncargadoAseo(e);
    }

    public void eliminarEncargadoAseo(int empleadoId) throws SQLException {
        repository.eliminarEncargadoAseo(empleadoId);
    }
}
