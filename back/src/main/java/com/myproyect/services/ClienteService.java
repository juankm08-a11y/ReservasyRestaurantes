package com.myproyect.services;

import java.sql.SQLException;
import java.util.List;

import com.myproyect.models.Cliente;
import com.myproyect.repositories.ClienteRepository;

public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void crear(Cliente c) throws SQLException {
        clienteRepository.insertar(c);
    }

    public List<Cliente> listar() throws SQLException {
        return clienteRepository.obtenerTodos();
    }

    public Cliente ver(int id) throws SQLException {
        return clienteRepository.getById(id);
    }

    public void actualizar(Cliente c) throws SQLException {
        clienteRepository.actualizar(c);
    }

    public void eliminar(int id) throws SQLException {
        clienteRepository.eliminar(id);
    }

}
