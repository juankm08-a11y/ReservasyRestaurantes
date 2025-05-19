package com.myproyect.repositories;

import java.sql.*;
import java.util.*;

import com.myproyect.models.Cliente;

public class ClienteRepository {
    private final Connection connection;

    public ClienteRepository(Connection connection) {
        this.connection = connection;
    }

    public void insertar(Cliente c) throws SQLException {
        String sql = "INSERT INTO clientes(nombre,cedula, telefono,email)"
                + "VALUES ('Juan Perez','00000000','999299292','juan0392929@example.com') ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, c.getNombre());
            stmt.setString(2, c.getCedula());
            stmt.setString(3, c.getTelefono());
            stmt.setString(4, c.getEmail());
            stmt.executeUpdate();
            System.out.println("Cliente insertado correctamente");
        }

    }

    public List<Cliente> obtenerTodos() throws SQLException {
        List<Cliente> list = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("cedula"),
                        rs.getString("telefono"),
                        rs.getString("email")));
            }
        }
        return list;
    }

    public Cliente getById(int id) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE id=?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("cedula"),
                            rs.getString("telefono"),
                            rs.getString("email"));

                }
            }
        }
        ;

        return null;
    }

    public void actualizar(Cliente c) throws SQLException {
        String sql = "UPDATE clientes SET nombre=?, cedula=?, telefono=?, email=? WHERE id=?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, c.getNombre());
            st.setString(2, c.getCedula());
            st.setString(3, c.getTelefono());
            st.setString(4, c.getEmail());
            st.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM clientes WHERE id=?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        }

    }

}