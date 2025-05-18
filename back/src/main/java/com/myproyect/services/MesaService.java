package com.myproyect.services;

import java.sql.SQLException;
import java.util.List;

import com.myproyect.models.Mesa;
import com.myproyect.repositories.MesaRepository;

public class MesaService {
    private final MesaRepository mesaRepository;

    public MesaService(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
    }

    public void crear(Mesa m) throws SQLException {
        mesaRepository.guardarMesa(m);
    }

    public List<Mesa> listar() throws SQLException {
        return mesaRepository.obtenerTodasLasMesas();
    }

    public Mesa ver(int id) throws SQLException {
        return mesaRepository.obtenerMesaPorId(id);
    }

    public void actualizar(Mesa m) throws SQLException {
        mesaRepository.actualizarMesa(m);
    }

    public void eliminar(int id) throws SQLException {
        mesaRepository.eliminarMesa(id);
    }

}