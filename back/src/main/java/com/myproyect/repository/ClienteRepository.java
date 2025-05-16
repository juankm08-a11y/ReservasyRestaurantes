package com.myproyect.repository;

import com.myproyect.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.myproyect.model.Cliente;

public class ClienteRepository {
    public void guardar(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes(nombre,cedula, telefono,email) VALUES (?,?,?,?)";
        try (Connection conn = DatabaseConnection.getInstance(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getCedula());
            stmt.setString(3, cliente.getTelefono());
            stmt.setString(4, cliente.getEmail());
            stmt.executeUpdate();
        }

    }

    public List<Cliente> obtenerTodos() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM mesas";
        try (Connection conn = DatabaseConnection.getInstance();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                clientes.add(new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("cedula"),
                        rs.getString("telefono"),
                        rs.getString("email")));
            }
        }
        return clientes;
    }
}
