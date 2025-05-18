package com.myproyect.repository;

import com.myproyect.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.myproyect.model.EstadoCount;
import com.myproyect.model.Reserva;

public class ReservaRepository {
    private final Connection conn;

    public ReservaRepository() throws SQLException {
        this.conn = DatabaseConnection.getInstance();
    }

    public void insertar(Reserva r) throws SQLException {
        String sql = "INSERT INTO reservas(cliente_id,mesa_id,fecha,hora,estado) VALUES(?,?,?,?,?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, r.getClienteId());
            st.setInt(2, r.getMesaId());
            st.setDate(3, Date.valueOf(r.getFecha()));
            st.setTime(4, Time.valueOf(r.getHora()));
            st.setString(5, r.getEstado());
            st.executeUpdate();
        }
    }

    public List<Reserva> obtenerTodas() throws SQLException {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM mesas";
        try (
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

    public List<Reserva> obtenerReservosTodos() throws SQLException {
        List<Reserva> list = new ArrayList<>();
        String sql = "SELECT * FROM reservas";
        try (Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Reserva(
                        rs.getInt("id"),
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
        String sql = "SELECT * FROM reservas WHERE id=?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
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

    public void actualizar(Reserva r) throws SQLException {
        String sql = "UPDATE reservas SET cliente_id=?, mesa_id=?, fecha=?, hora=?, estado=?,WHERE id=?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, r.getClienteId());
            st.setInt(2, r.getMesaId());
            st.setDate(3, Date.valueOf(r.getFecha()));
            st.setTime(4, Time.valueOf(r.getHora()));
            st.setString(5, r.getEstado());
            st.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE reservas  WHERE id=?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        }

    }

    public List<String> mesasReservadasPorHorario() throws SQLException {
        String sql = "SELECT * FROM mesa_id,hora,COUNT(*) AS total FROM reservas GROUP BY mesa_id, hora ORDER BY total DESC";
        List<String> out = new ArrayList<>();
        try (Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                out.add("Mesa" + rs.getInt("mesa_id") + "Hora:" + rs.getTime("hora") + " Total: " + rs.getInt("total"));
            }
        }
        return out;
    }

    public List<String> clientesFrecuentes(int minVisitas) throws SQLException {
        String sql = "SELECT cliente_id, COUNT(*) AS total FROM reservas WHERE fecha>=CURDATE()-INTERVAL 30 DAY GROUP BY cliente_id HAVING total> ?";
        List<String> out = new ArrayList<>();
        try (PreparedStatement st = conn.prepareStatement(sql)) {
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
        String sql = "SELECT * FROM reservas WHERE estado='Cancelada' AND fecha>=CURDATE-INTERVALE 3 MONTH";
        List<Reserva> out = new ArrayList<>();
        try (Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                out.add(new Reserva(
                        rs.getInt("id"),
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
        String sql = "SELECT DAYNAME(fecha) AS dia, hora, COUNT(*) AS total FROM reservas WHERE estado='Confirmada' AND GROUP BY dia, hora ORDER BY total DESC";
        List<String> out = new ArrayList<>();
        try (Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                out.add(rs.getString("dia") +
                        "Hora:" + rs.getTime("hora") +
                        "Total:" + rs.getInt("total"));
            }
        }
        return out;
    }

    public void actualizarEstadoCount() throws SQLException {
        List<EstadoCount> counts = new ArrayList<>();
        String q = "SELECT estado, COUNT(*) AS total FROM reservas GROUP BY estado";
        try (Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(q)) {
            while (rs.next()) {
                counts.add(new EstadoCount(rs.getString("estado"), rs.getInt("total")));
            }
        }
        try (Statement st = conn.createStatement()) {
            st.execute("TRUNCATE TABLE estado_count");
        }
        String ins = "INSERT INTO estado_count(estado,total) VALUES(?,?)";
        try (PreparedStatement pst = conn.prepareStatement(ins)) {
            for (EstadoCount ec : counts) {
                pst.setString(1, ec.getEstado());
                pst.setInt(2, ec.getTotal());
                pst.executeUpdate();
            }
        }
    }

    public List<EstadoCount> obtenerEstadoCount() throws SQLException {
        List<EstadoCount> out = new ArrayList<>();
        String sql = "SELECT * FROM estado_count";
        try (Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                out.add(new EstadoCount(rs.getString("estado"), rs.getInt("total")));
            }
        }

        return out;

    }

}
