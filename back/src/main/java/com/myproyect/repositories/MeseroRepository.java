package com.myproyect.repositories;

import java.sql.*;
import java.util.*;

import com.myproyect.models.Mesero;

public class MeseroRepository {
    private final Connection connection;

    public MeseroRepository(Connection connection) {
        this.connection = connection;
    }

    public void insertarMesero(Mesero m) throws SQLException {
        String sql = "INSERT INTO meseros(id,turno) VALUES(?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, m.getEmpleadoId());
            stmt.setString(2, m.getTurno());
            stmt.executeUpdate();
        }
    }

    public List<Mesero> obtenerTodosLosMeseros() throws SQLException {
        List<Mesero> list = new ArrayList<>();
        String sql = "SELECT * FROM meseros";
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Mesero(
                        rs.getInt("id"),
                        rs.getInt("empleadoId"),
                        rs.getString("turno")));
            }
        }
        return list;
    }

    public void actualizarMesero(Mesero m) throws SQLException {
        String sql = "UPDATE meseros SET turno = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, m.getTurno());
            stmt.setInt(2, m.getEmpleadoId());
            stmt.executeUpdate();
        }
    }

    public void eliminarMesero(int empleadoId) throws SQLException {
        String sql = "DELETE FROM meseros WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, empleadoId);
            stmt.executeUpdate();
        }
    }
}
