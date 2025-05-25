package com.myproyect.repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.myproyect.models.Reserva;

public class ReservaRepository implements Repository<Reserva> {
    private final Connection connection;

    public ReservaRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Reserva> findAll() throws SQLException {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reserva";
        try (Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
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

    @Override
    public Reserva getById(Integer id) throws SQLException {
        String sql = "SELECT * FROM reserva WHERE reserva_id = ?";
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

    @Override
    public void save(Reserva r) throws SQLException {
        if (r.getReservaId() == 0) {
            String sql = "INSERT INTO reserva(cliente_id, mesa_id, fecha, hora, estado) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                st.setInt(1, r.getClienteId());
                st.setInt(2, r.getMesaId());
                st.setDate(3, Date.valueOf(r.getFecha()));
                st.setTime(4, Time.valueOf(r.getHora()));
                st.setString(5, r.getEstado());
                st.executeUpdate();

                try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        r.setReservaId(generatedKeys.getInt(1));
                    }
                }
            }
        } else {
            // UPDATE
            String sql = "UPDATE reserva SET cliente_id = ?, mesa_id = ?, fecha = ?, hora = ?, estado = ? WHERE reserva_id = ?";
            try (PreparedStatement st = connection.prepareStatement(sql)) {
                st.setInt(1, r.getClienteId());
                st.setInt(2, r.getMesaId());
                st.setDate(3, Date.valueOf(r.getFecha()));
                st.setTime(4, Time.valueOf(r.getHora()));
                st.setString(5, r.getEstado());
                st.setInt(6, r.getReservaId());
                st.executeUpdate();
            }
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM reserva WHERE reserva_id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        }
    }

    public List<String> mesasReservadasPorHorario() throws SQLException {
        String sql = "SELECT mesa_id, hora, COUNT(*) AS total_reservas FROM reserva WHERE estado = 'Completada' GROUP BY mesa_id, hora ORDER BY total_reservas DESC";
        List<String> out = new ArrayList<>();
        try (Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                out.add("Mesa " + rs.getInt("mesa_id") +
                        " - Hora: " + rs.getTime("hora") +
                        " - Total: " + rs.getInt("total"));
            }
        }
        return out;
    }

    public List<String> clientesFrecuentes(int minVisitas) throws SQLException {
        String sql = "SELECT cliente_id, COUNT(*) AS total FROM reserva WHERE fecha >= CURDATE() - INTERVAL 30 DAY GROUP BY cliente_id HAVING total > ?";
        List<String> out = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, minVisitas);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    out.add("Cliente " + rs.getInt("cliente_id") +
                            " - Reservas: " + rs.getInt("total"));
                }
            }
        }
        return out;
    }

    public List<Reserva> reservasCanceladasUltimoTrimestre() throws SQLException {
        String sql = "SELECT * FROM reserva WHERE estado = 'Cancelada' AND fecha >= CURDATE() - INTERVAL 3 MONTH";
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
        String sql = "SELECT DAYNAME(fecha) AS dia, hora, COUNT(*) AS total FROM reserva WHERE estado = 'Completada' GROUP BY dia, hora ORDER BY total DESC";
        List<String> out = new ArrayList<>();
        try (Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                out.add(rs.getString("dia") +
                        " - Hora: " + rs.getTime("hora") +
                        " - Total: " + rs.getInt("total"));
            }
        }
        return out;
    }
}
