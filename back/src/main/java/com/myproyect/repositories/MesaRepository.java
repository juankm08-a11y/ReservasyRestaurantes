package com.myproyect.repositories;

import com.myproyect.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.myproyect.models.Mesa;

public class MesaRepository {
    private final Connection connection;

    public MesaRepository(Connection connection) {
        this.connection = connection;
    }

    public void guardarMesa(Mesa mesa) throws SQLException {
        String sql = "INSERT INTO mesas(numero,capacidad, ubicacion) VALUES (?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, mesa.getNumero());
            stmt.setInt(2, mesa.getCapacidad());
            stmt.setString(3, mesa.getUbicacion());
            stmt.executeUpdate();
        }

    }

    public List<Mesa> obtenerTodasLasMesas() throws SQLException {
        List<Mesa> mesas = new ArrayList<>();
        String sql = "SELECT * FROM mesas";
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Mesa mesa = new Mesa(
                        rs.getInt("id"),
                        rs.getInt("numero"),
                        rs.getInt("capacidad"),
                        rs.getString("ubicacion"));
                mesas.add(mesa);
            }
        }
        return mesas;
    }

    public Mesa obtenerMesaPorId(int id) throws SQLException {
        String sql = "SELECT * FROM mesas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Mesa(
                            rs.getInt("id"),
                            rs.getInt("numero"),
                            rs.getInt("capacidad"),
                            rs.getString("ubicacion"));
                }
            }
        }
        return null;
    }

    public void actualizarMesa(Mesa mesa) throws SQLException {
        String sql = "UPDATE mesas SET numero = ?, capacidad = ?, ubicacion=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, mesa.getNumero());
            stmt.setInt(2, mesa.getCapacidad());
            stmt.setString(3, mesa.getUbicacion());
            stmt.executeUpdate();
        }
    }

    public void eliminarMesa(int id) throws SQLException {
        String sql = "DELETE mesas WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }

    }

}
