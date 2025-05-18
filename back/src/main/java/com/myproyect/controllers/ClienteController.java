package com.myproyect.controllers;

import java.sql.SQLException;
import java.util.List;

import com.myproyect.models.Cliente;
import com.myproyect.services.ClienteService;

public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public void crear(Cliente cliente) throws SQLException {
        clienteService.crear(cliente);
    }

    public List<Cliente> listar() throws SQLException {
        return clienteService.listar();
    }

    public Cliente ver(int id) throws SQLException {
        return clienteService.ver(id);
    }

    public void actualizar(Cliente cliente) throws SQLException {
        clienteService.actualizar(cliente);
    }

    public void eliminar(int id) throws SQLException {
        clienteService.eliminar(id);
    }
}