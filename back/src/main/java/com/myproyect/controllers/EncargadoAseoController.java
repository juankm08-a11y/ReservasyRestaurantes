package com.myproyect.controllers;

import java.sql.SQLException;
import java.util.List;

import com.myproyect.models.EncargadoAseo;
import com.myproyect.services.EncargadoAseoService;

public class EncargadoAseoController {
    private final EncargadoAseoService encargadoAseoService;

    public EncargadoAseoController(EncargadoAseoService encargadoAseoService) {
        this.encargadoAseoService = encargadoAseoService;
    }

    public void crear(EncargadoAseo encargadoAseo) throws SQLException {
        encargadoAseoService.insertarEncargadoAseo(encargadoAseo);
    }

    public List<EncargadoAseo> listar() throws SQLException {
        return encargadoAseoService.obtenerTodosLosEncargados();
    }

    public void actualizar(EncargadoAseo encargadoAseo) throws SQLException {
        encargadoAseoService.actualizarEncargadoAseo(encargadoAseo);
    }

    public void eliminar(int id) throws SQLException {
        encargadoAseoService.eliminarEncargadoAseo(id);
    }
}
