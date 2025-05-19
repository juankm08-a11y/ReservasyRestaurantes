package com.myproyect.controllers;

import java.sql.SQLException;
import java.util.List;

import com.myproyect.models.Mesero;
import com.myproyect.services.MeseroService;

public class MeseroController {
    private final MeseroService meseroService;

    public MeseroController() {
        this.meseroService = new MeseroService(null);
    }

    public void crear(Mesero mesero) throws SQLException {
        meseroService.insertarMesero(mesero);
    }

    public List<Mesero> listar() throws SQLException {
        return meseroService.obtenerTodosLosMeseros();
    }

    public void actualizar(Mesero mesero) throws SQLException {
        meseroService.actualizarMesero(mesero);
    }

    public void eliminar(int id) throws SQLException {
        meseroService.eliminarMesero(id);
    }

}
