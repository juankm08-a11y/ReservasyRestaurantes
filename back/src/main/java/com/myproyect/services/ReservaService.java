package com.myproyect.services;

import java.sql.SQLException;
import java.util.List;

import com.myproyect.models.EstadoCount;
import com.myproyect.models.Reserva;
import com.myproyect.repositories.ReservaRepository;

public class ReservaService {
    private final ReservaRepository repo;

    public ReservaService() throws SQLException {
        this.repo = new ReservaRepository();
    }

    public void crear(Reserva r) throws SQLException {
        repo.insertar(r);
    }

    public List<Reserva> listar() throws SQLException {
        return repo.obtenerTodos();
    }

    public Reserva ver(int id) throws SQLException {
        return repo.getById(id);
    }

    public void actualizar(Reserva r) throws SQLException {
        repo.actualizar(r);
    }

    public void eliminar(int id) throws SQLException {
        repo.eliminar(id);
    }

    public List<String> mesasReservadas() throws SQLException {
        return repo.mesasReservadasPorHorario();
    }

    public List<String> clientesFrecuentes(int min) throws SQLException {
        return repo.clientesFrecuentes(min);
    }

    public List<Reserva> canceladas() throws SQLException {
        return repo.reservasCanceladasUltimoTrimestre();
    }

    public List<String> horariosPopulares() throws SQLException {
        return repo.horariosPopularesPorDia();
    }

    public void refrescarEstados() throws SQLException {
        repo.actualizarEstadoCount();
    }

    public List<EstadoCount> verEstados() throws SQLException {
        return repo.obtenerEstadoCount();
    }
}
