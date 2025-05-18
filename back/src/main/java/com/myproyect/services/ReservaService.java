package com.myproyect.services;

import java.sql.SQLException;
import java.util.List;

import com.myproyect.models.EstadoCount;
import com.myproyect.models.Reserva;
import com.myproyect.repositories.ReservaRepository;

public class ReservaService {
    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public void crear(Reserva r) throws SQLException {
        reservaRepository.insertar(r);
    }

    public List<Reserva> listar() throws SQLException {
        return reservaRepository.obtenerTodos();
    }

    public Reserva ver(int id) throws SQLException {
        return reservaRepository.getById(id);
    }

    public void actualizar(Reserva r) throws SQLException {
        reservaRepository.actualizar(r);
    }

    public void eliminar(int id) throws SQLException {
        reservaRepository.eliminar(id);
    }

    public List<String> mesasReservadas() throws SQLException {
        return reservaRepository.mesasReservadasPorHorario();
    }

    public List<String> clientesFrecuentes(int min) throws SQLException {
        return reservaRepository.clientesFrecuentes(min);
    }

    public List<Reserva> canceladas() throws SQLException {
        return reservaRepository.reservasCanceladasUltimoTrimestre();
    }

    public List<String> horariosPopulares() throws SQLException {
        return reservaRepository.horariosPopularesPorDia();
    }

    public void refrescarEstados() throws SQLException {
        reservaRepository.actualizarEstadoCount();
    }

    public List<EstadoCount> verEstados() throws SQLException {
        return reservaRepository.obtenerEstadoCount();
    }
}
