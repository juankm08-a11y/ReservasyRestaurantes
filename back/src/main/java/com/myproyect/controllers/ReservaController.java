package com.myproyect.controllers;

import java.sql.SQLException;
import java.util.List;

import com.myproyect.models.EstadoCount;
import com.myproyect.models.Reserva;
import com.myproyect.services.ReservaService;

public class ReservaController {
    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    public void crear(Reserva reserva) throws SQLException {
        reservaService.crear(reserva);
    }

    public List<Reserva> listar() throws SQLException {
        return reservaService.listar();
    }

    public Reserva ver(int id) throws SQLException {
        return reservaService.ver(id);
    }

    public void actualizar(Reserva reserva) throws SQLException {
        reservaService.actualizar(reserva);
    }

    public void eliminar(int id) throws SQLException {
        reservaService.eliminar(id);
    }

    public List<String> obtenerMesasReservadasPorHorario() throws SQLException {
        return reservaService.mesasReservadas();
    }

    public List<String> obtenerClientesFrecuentes() throws SQLException {
        return reservaService.clientesFrecuentes(5);
    }

    public List<Reserva> obtenerReservasCanceladasUltimoTrimestre() throws SQLException {
        return reservaService.canceladas();
    }

    public List<String> obtenerHorarioMayorOcupacionPorDia() throws SQLException {
        return reservaService.horariosPopulares();
    }

    public List<EstadoCount> obtenerEstadoCounts() throws SQLException {
        return reservaService.verEstados();
    }

}
