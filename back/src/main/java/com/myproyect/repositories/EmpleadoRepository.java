package com.myproyect.repositories;

import java.sql.*;
import java.util.*;

import com.myproyect.models.Empleado;

public class EmpleadoRepository {
    private final Connection connection;

    public EmpleadoRepository(Connection connection) {
        this.connection = connection;
    }

    public void insertarEmpleado(Empleado e) throws SQLException {
        String sql = "INSERT INTO empleados(nombre, rol,telefono) VALUES(?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, e.getNombre());
            stmt.setString(2, e.getRol());
            stmt.setString(3, e.getTelefono());
            stmt.executeUpdate();
        }
    }

    public List<Empleado> obtenerTodosLosEmpleados() throws SQLException {
        List<Empleado> list = new ArrayList<>();
        String sql = "SELECT * FROM empleados";
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Empleado(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("rol"),
                        rs.getString("telefono")));
            }
        }
        return list;

    }

    public Empleado obtenerEmpleadoPorId(int id) throws SQLException {
        String sql = "SELECT * FROM empleados WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Empleado(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("rol"),
                            rs.getString("telefono"));
                }
            }
        }
        return null;
    }

    public void actualizarEmpleado(Empleado e) throws SQLException {
        String sql = "UPDATE empleados SET nombre = ?, rol = ?; telefono = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, e.getNombre());
            stmt.setString(2, e.getRol());
            stmt.setString(3, e.getTelefono());
            stmt.setInt(4, e.getId());
            stmt.executeUpdate();
        }
    }

    public void eliminarEmpleado(int id) throws SQLException {
        String sql = "DELETE FROM empleados WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
