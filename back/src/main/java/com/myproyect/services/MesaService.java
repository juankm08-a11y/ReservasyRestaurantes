package com.myproyect.services;

import java.sql.SQLException;
import java.util.List;

import com.myproyect.models.Mesa;
import com.myproyect.repositories.MesaRepository;
import com.myproyect.utils.DatabaseConnection;

public class MesaService {
    private final MesaRepository repo;

    public MesaService() throws SQLException {
        this.repo = new MesaRepository(DatabaseConnection.getInstance());
    }

    public void crear(Mesa m) throws SQLException {
        repo.guardarMesa(m);
    }

    public List<Mesa> listar() throws SQLException {
        return repo.obtenerTodasLasMesas();
    }

    public Mesa ver(int id) throws SQLException {
        return repo.obtenerMesaPorId(id);
    }

    public void actualizar(Mesa m) throws SQLException {
        repo.actualizarMesa(m);
    }

    public void eliminar(int id) throws SQLException {
        repo.eliminarMesa(id);
    }

}