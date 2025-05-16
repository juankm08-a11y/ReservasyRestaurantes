package com.myproyect.repository;

import com.myproyect.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.myproyect.model.Mesa;

public class MesaRepository {
    public void guardar(Mesa mesa) throws SQLException {
        String sql = "INSERT INTO mesas(numero,capacidad, ubicacion) VALUES (?,?,?)";
        try (Connection conn = DatabaseConnection.getInstance(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, mesa.getNumero());
            stmt.setInt(2, mesa.getCapacidad());
            stmt.setString(3, mesa.getUbicacion());
            stmt.executeUpdate();
        }

    }

    public List<Mesa> obtenerTodas() throws SQLException {
        List<Mesa> mesas = new ArrayList<>();
        String sql = "SELECT * FROM mesas";
        try (Connection conn = DatabaseConnection.getInstance();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                mesas.add(new Mesa(
                        rs.getInt("id"),
                        rs.getInt("numero"),
                        rs.getInt("capacidad"),
                        rs.getString("ubicacion")));
            }
        }
        return mesas;
    }

    public void actualizar(Mesa mesa) throws SQLException {
        String sql = "UPDATE mesas SET numero = ?, capacidad = ?, ubicacion = ?,WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, mesa.getNumero());
            stmt.setInt(2, mesa.getCapacidad());
            stmt.setString(3, mesa.getUbicacion());
            stmt.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE mesas WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }

    }

    public List<Mesa> obtenerMesasOrdenadasPorCapacidad() throws SQLException {
        List<Mesa> mesas = new ArrayList<>();
        String sql = "SELECT * FROM mesas ORDER BY capacidad DESC";
        try (Connection conn = DatabaseConnection.getInstance();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                mesas.add(new Mesa(
                        rs.getInt("id"),
                        rs.getInt("numero"),
                        rs.getInt("capacidad"),
                        rs.getString("ubicacion")));
            }
        }
        return mesas;
    }
}
