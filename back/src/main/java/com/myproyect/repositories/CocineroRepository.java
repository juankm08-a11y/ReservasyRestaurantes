package com.myproyect.repositories;

import java.sql.*;
import java.util.*;

import com.myproyect.models.Cocinero;

public class CocineroRepository {
    private final Connection connection;

    public CocineroRepository(Connection connection) {
        this.connection = connection;
    }

    public void insertarCocinero(Cocinero c) throws SQLException {
        String sql = "INSERT INTO cocineros(id,especialidad) VALUES(?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, c.getEmpleadoId());
            stmt.setString(2, c.getEspecialidad());
            stmt.executeUpdate();
        }
    }

    public List<Cocinero> obtenerTodosLosCocineros() throws SQLException {
        List<Cocinero> list = new ArrayList<>();
        String sql = "SELECT * FROM cocineros";
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Cocinero(rs.getInt("id"), rs.getInt("empleadoId"), rs.getString("especialidad")));
            }
        }
        return list;
    }

    public void actualizarCocinero(Cocinero c) throws SQLException {
        String sql = "UPDATE cocineros SET especialidad = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, c.getEspecialidad());
            stmt.setInt(2, c.getEmpleadoId());
            stmt.executeUpdate();
        }
    }

    public void eliminarCocinero(int empleadoId) throws SQLException {
        String sql = "DELETE FROM cocineros WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, empleadoId);
            stmt.executeUpdate();
        }
    }

}
