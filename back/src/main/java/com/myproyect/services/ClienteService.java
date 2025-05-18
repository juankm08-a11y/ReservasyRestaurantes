package com.myproyect.services;

import java.sql.SQLException;
import java.util.List;

import com.myproyect.models.Cliente;
import com.myproyect.repositories.ClienteRepository;

public class ClienteService {
    private final ClienteRepository repo;

    public ClienteService() throws SQLException {
        this.repo = new ClienteRepository();
    }

    public void crear(Cliente c) throws SQLException {
        repo.insertar(c);
    }

    public List<Cliente> listar() throws SQLException {
        return repo.obtenerTodos();
    }

    public Cliente ver(int id) throws SQLException {
        return repo.getById(id);
    }

    public void actualizar(Cliente c) throws SQLException {
        repo.actualizar(c);
    }

    public void eliminar(int id) throws SQLException {
        repo.eliminar(id);
    }

}
