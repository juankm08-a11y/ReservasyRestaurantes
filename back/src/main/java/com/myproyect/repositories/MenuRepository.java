package com.myproyect.repositories;

import java.sql.*;
import java.util.*;

import com.myproyect.models.Menu;

public class MenuRepository {
    private final Connection connection;

    public MenuRepository(Connection connection) {
        this.connection = connection;
    }

    public void insertarMenu(Menu menu) throws SQLException {
        String sql = "INSERT INTO menu(nombre, descripcion,precio) VALUES(?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, menu.getNombre());
            stmt.setString(2, menu.getDescripcion());
            stmt.setDouble(3, menu.getPrecio());
            stmt.executeUpdate();
        }
    }

    public List<Menu> obtenerTodosLosMenus() throws SQLException {
        List<Menu> list = new ArrayList<>();
        String sql = "SELECT * FROM menu";
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Menu(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("precio")));
            }
        }
        return list;
    }

    public void actualizarMenu(Menu menu) throws SQLException {
        String sql = "UPDATE menu SET nombre = ?, descripcion = ?, precio = ? WHERE id=? ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, menu.getNombre());
            stmt.setString(2, menu.getDescripcion());
            stmt.setDouble(3, menu.getPrecio());
            stmt.setInt(4, menu.getId());
            stmt.executeUpdate();
        }
    }

    public void eliminarMenu(int id) throws SQLException {
        String sql = "DELETE FROM menu WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

}
