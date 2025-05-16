package com.myproyect.repository;

import com.myproyect.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.myproyect.model.EstadoCount;
import com.myproyect.model.Reserva;

public class ReservaRepository {
    public void guardar(Reserva reserva) throws SQLException {
        String sql = "INSERT INTO reservas(cliente_id,mesa_id, fecha, hora, estado) VALUES (?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getInstance(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reserva.getClienteId());
            stmt.setInt(2, reserva.getMesaId());
            stmt.setDate(3, Date.valueOf(reserva.getFecha()));
            stmt.setTime(4, Time.valueOf(reserva.getHora()));
            stmt.setString(5, reserva.getEstado());

            stmt.executeUpdate();
        }

    }

    public List<Reserva> obtenerTodas() throws SQLException {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM mesas";
        try (Connection conn = DatabaseConnection.getInstance();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                reservas.add(new Reserva(
                        rs.getInt("id"),
                        rs.getInt("cliente_id"),
                        rs.getInt("mesa_id"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getTime("hora").toLocalTime(),
                        rs.getString("estado")));
            }
        }
        return reservas;
    }

    public void actualizar(Reserva reserva) throws SQLException {
        String sql = "UPDATE reservas SET cliente_id = ?, mesa_id = ?, fecha = ?, hora = ?, estado = ?,WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reserva.getClienteId());
            stmt.setInt(2, reserva.getMesaId());
            stmt.setDate(3, Date.valueOf(reserva.getFecha()));
            stmt.setTime(4, Time.valueOf(reserva.getHora()));
            stmt.setString(5, reserva.getEstado());
            stmt.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE reservas  WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }

    }

    public List<EstadoCount> contarReservasPorEstado() throws SQLException {
        List<EstadoCount> resultados = new ArrayList<>();
        String sql = "SELECT estado, COUNT(*) AS total FROM reservas GROUP BY estado";
        try (Connection conn = DatabaseConnection.getInstance();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                resultados.add(new EstadoCount(
                        rs.getString("estado"),
                        rs.getInt("total")));
            }
        }
        return resultados;
    }

    public List<Reserva> obtenerReservasOrdenadasPorFechaHora() throws SQLException {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reservas ORDER BY fecha DESC, hora DESC";
        try (Connection conn = DatabaseConnection.getInstance();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                reservas.add(new Reserva(
                        rs.getInt("id"),
                        rs.getInt("cliente_id"),
                        rs.getInt("mesa_id"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getTime("hora").toLocalTime(),
                        rs.getString("estado")));
            }
        }
        return reservas;
    }

    public List<Reserva> findAll() throws SQLException {
        return obtenerTodas();
    }

    public Reserva getById(int id) throws SQLException {
        String sql = "SELECT * FROM reservas WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Reserva(
                            rs.getInt("id"),
                            rs.getInt("cliente_id"),
                            rs.getInt("mesa_id"),
                            rs.getDate("fecha").toLocalDate(),
                            rs.getTime("hora").toLocalTime(),
                            rs.getString("estado"));
                }
            }
        }
        return null;
    }
}
