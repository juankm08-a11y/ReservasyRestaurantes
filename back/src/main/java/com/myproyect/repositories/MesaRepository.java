package com.myproyect.repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.myproyect.models.Mesa;

public class MesaRepository implements Repository<Mesa> {

    private final Connection connection;

    public MesaRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Mesa m) throws SQLException {
        String sql = "INSERT INTO mesa(numero, capacidad, ubicacion) VALUES (?, ?, ?)";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, m.getNumero());
            st.setInt(2, m.getCapacidad());
            st.setString(3, m.getUbicacion());
            st.executeUpdate();
        }
    }

    @Override
    public List<Mesa> findAll() throws SQLException {
        List<Mesa> mesas = new ArrayList<>();
        String sql = "SELECT * FROM mesa";
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                mesas.add(new Mesa(
                        rs.getInt("mesa_id"),
                        rs.getInt("numero"),
                        rs.getInt("capacidad"),
                        rs.getString("ubicacion")));
            }
        }
        return mesas;
    }

    @Override
    public Mesa getById(Integer id) throws SQLException {
        String sql = "SELECT * FROM mesa WHERE mesa_id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Mesa(
                            rs.getInt("mesa_id"),
                            rs.getInt("numero"),
                            rs.getInt("capacidad"),
                            rs.getString("ubicacion"));
                }
            }
        }
        return null;
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM mesa WHERE mesa_id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        }
    }
}
