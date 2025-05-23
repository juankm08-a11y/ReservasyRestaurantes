package com.myproyect.repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.myproyect.models.Cliente;

public class ClienteRepository implements Repository<Cliente> {

    private final Connection connection;

    public ClienteRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Cliente c) throws SQLException {
        String sql = "INSERT INTO cliente(nombre, cedula, telefono, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, c.getNombre());
            st.setString(2, c.getCedula());
            st.setString(3, c.getTelefono());
            st.setString(4, c.getEmail());
            st.executeUpdate();
        }
    }

    @Override
    public List<Cliente> findAll() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                clientes.add(new Cliente(
                        rs.getInt("cliente_id"),
                        rs.getString("nombre"),
                        rs.getString("cedula"),
                        rs.getString("telefono"),
                        rs.getString("email")));
            }
        }
        return clientes;
    }

    @Override
    public Cliente getById(Integer id) throws SQLException {
        String sql = "SELECT * FROM cliente WHERE cliente_id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                            rs.getInt("cliente_id"),
                            rs.getString("nombre"),
                            rs.getString("cedula"),
                            rs.getString("telefono"),
                            rs.getString("email"));
                }
            }
        }
        return null;
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM cliente WHERE cliente_id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        }
    }
}
