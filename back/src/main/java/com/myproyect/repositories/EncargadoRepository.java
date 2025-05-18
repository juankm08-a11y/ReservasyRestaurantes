package com.myproyect.repositories;

import java.sql.*;
import java.util.*;

import com.myproyect.models.EncargadoAseo;
import com.myproyect.utils.DatabaseConnection;

public class EncargadoRepository {
    private final Connection connection;

    public EncargadoRepository() throws SQLException {
        this.connection = DatabaseConnection.getInstance();
    }

    public void insertarEncargadoAseo(EncargadoAseo e) throws SQLException {
        String sql = "INSERT INTO encargados_aseo (id,zona_asignada) VALUES(?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, e.getEmpleadoId());
            stmt.setString(2, e.getZonaAsignada());
            stmt.executeUpdate();
        }
    }

    public List<EncargadoAseo> obtenerTodosLosEncargados() throws SQLException {
        List<EncargadoAseo> list = new ArrayList<>();
        String sql = "SELECT * FROM encargados_aseo";
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new EncargadoAseo(rs.getInt("id"), rs.getInt("id"), rs.getString("zona asignada")));
            }
        }
        return list;
    }

    public void actualizarEncargadoAseo(EncargadoAseo e) throws SQLException {
        String sql = "UPDATE encargados_aseo SET zona_asignada = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, e.getZonaAsignada());
            stmt.setInt(2, e.getEmpleadoId());
            stmt.executeUpdate();
        }
    }

    public void eliminarEncargadoAseo(int empleadoId) throws SQLException {
        String sql = "DELETE FROM encargados_aseo WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, empleadoId);
            stmt.executeUpdate();
        }
    }
}
