package com.myproyect.controllers;

import java.sql.SQLException;
import java.util.*;

import com.myproyect.models.Mesa;
import com.myproyect.services.MesaService;

public class MesaController {
    private final MesaService mesaService;

    public MesaController(MesaService mesaService) {
        this.mesaService = mesaService;
    }

    public void crear(Mesa mesa) throws SQLException {
        mesaService.crear(mesa);
    }

    public List<Mesa> listar() throws SQLException {
        return mesaService.listar();
    }

    public Mesa ver(int id) throws SQLException {
        return mesaService.ver(id);
    }

    public void actualizar(Mesa mesa) throws SQLException {
        mesaService.actualizar(mesa);
    }

    public void eliminar(int id) throws SQLException {
        mesaService.eliminar(id);
    }
}
