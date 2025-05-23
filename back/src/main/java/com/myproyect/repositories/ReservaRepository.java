package com.myproyect.repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.myproyect.models.Reserva;

public class ReservaRepository {
    private final Connection connection;

    public ReservaRepository(Connection connection) {
        this.connection = connection;
    }

    public void insertar(Reserva r) throws SQLException {
        String sql = "INSERT INTO reserva(cliente_id,mesa_id,fecha,hora,estado) VALUES(?,?,?,?,?)";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, r.getClienteId());
            st.setInt(2, r.getMesaId());
            st.setDate(3, Date.valueOf(r.getFecha()));
            st.setTime(4, Time.valueOf(r.getHora()));
            st.setString(5, r.getEstado());
            st.executeUpdate();
        }
    }

    public List<Reserva> obtenerTodos() throws SQLException {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM mesa";
        try (
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                reservas.add(new Reserva(
                        rs.getInt("reserva_id"),
                        rs.getInt("cliente_id"),
                        rs.getInt("mesa_id"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getTime("hora").toLocalTime(),
                        rs.getString("estado")));
            }
        }
        return reservas;
    }

    public List<Reserva> obtenerReservosTodos() throws SQLException {
        List<Reserva> list = new ArrayList<>();
        String sql = "SELECT * FROM reserva";
        try (Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Reserva(
                        rs.getInt("reserva_id"),
                        rs.getInt("cliente_id"),
                        rs.getInt("mesa_id"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getTime("hora").toLocalTime(),
                        rs.getString("estado")));
            }
        }
        return list;
    }

    public Reserva getById(int id) throws SQLException {
        String sql = "SELECT * FROM reserva WHERE id=?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Reserva(
                            rs.getInt("reserva_id"),
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

    public void actualizar(Reserva r) throws SQLException {
        String sql = "UPDATE reserva SET cliente_id=?, mesa_id=?, fecha=?, hora=?, estado=? WHERE id=?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, r.getClienteId());
            st.setInt(2, r.getMesaId());
            st.setDate(3, Date.valueOf(r.getFecha()));
            st.setTime(4, Time.valueOf(r.getHora()));
            st.setString(5, r.getEstado());
            st.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM reserva  WHERE id=?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        }

    }

    public List<String> mesasReservadasPorHorario() throws SQLException {
        String sql = "SELECT mesa_id,hora,COUNT(*) AS total FROM reservas GROUP BY mesa_id, hora ORDER BY total DESC";
        List<String> out = new ArrayList<>();
        try (Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                out.add("Mesa" + rs.getInt("mesa_id") + "Hora:" + rs.getTime("hora") + " Total: " + rs.getInt("total"));
            }
        }
        return out;
    }

    public List<String> clientesFrecuentes(int minVisitas) throws SQLException {
        String sql = "SELECT cliente_id, COUNT(*) AS total FROM reserva WHERE fecha>=CURDATE()-INTERVAL 30 DAY GROUP BY cliente_id HAVING total> ?";
        List<String> out = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, minVisitas);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    out.add("Cliente" + rs.getInt("cliente_id") +
                            "Reservas:" + rs.getInt("total"));
                }
            }
        }

        return out;
    }

    public List<Reserva> reservasCanceladasUltimoTrimestre() throws SQLException {
        String sql = "SELECT * FROM  WHERE estado='Cancelada' AND fecha >= CURDATE() - INTERVAL 3 MONTH";
        List<Reserva> out = new ArrayList<>();
        try (Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                out.add(new Reserva(
                        rs.getInt("reserva_id"),
                        rs.getInt("cliente_id"),
                        rs.getInt("mesa_id"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getTime("hora").toLocalTime(),
                        rs.getString("estado")));
            }
        }
        return out;
    }

    public List<String> horariosPopularesPorDia() throws SQLException {
        String sql = "SELECT DAYNAME(fecha) AS dia, hora, COUNT(*) AS total FROM reserva WHERE estado='Completada' GROUP BY dia, hora ORDER BY total DESC";
        List<String> out = new ArrayList<>();
        try (Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                out.add(rs.getString("dia") +
                        "Hora:" + rs.getTime("hora") +
                        "Total:" + rs.getInt("total"));
            }
        }
        return out;
    }

}
