package com.myproyect.services;

import java.sql.SQLException;
import java.util.*;
import java.sql.Connection;

import com.myproyect.models.Menu;
import com.myproyect.repositories.MenuRepository;
import com.myproyect.utils.DatabaseConnection;

public class MenuService {
    private final MenuRepository repo;

    public MenuService() throws SQLException {
        Connection conn = DatabaseConnection.getInstance();
        this.repo = new MenuRepository(conn);
    }

    public void crear(Menu menu) throws SQLException {
        repo.insertarMenu(menu);
    }

    public List<Menu> listar() throws SQLException {
        return repo.obtenerTodosLosMenus();
    }

    public void actualizarMenu(Menu menu) throws SQLException {
        repo.actualizarMenu(menu);
    }

    public void eliminar(int id) throws SQLException {
        repo.eliminarMenu(id);
    }
}
