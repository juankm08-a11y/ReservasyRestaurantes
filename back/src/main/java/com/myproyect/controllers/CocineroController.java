package com.myproyect.controllers;

import java.sql.SQLException;
import java.util.List;

import com.myproyect.models.Cocinero;
import com.myproyect.services.CocineroService;

public class CocineroController {
    private final CocineroService cocineroService;

    public CocineroController() {
        this.cocineroService = new CocineroService(null);
    }

    public void crear(Cocinero cocinero) throws SQLException {
        cocineroService.insertarCocinero(cocinero);
    }

    public List<Cocinero> listar() throws SQLException {
        return cocineroService.obtenerTodosLosCocineros();
    }

    public void actualizar(Cocinero cocinero) throws SQLException {
        cocineroService.actualizarCocinero(cocinero);
    }

    public void eliminar(int id) throws SQLException {
        cocineroService.eliminarCocinero(id);
    }

}
