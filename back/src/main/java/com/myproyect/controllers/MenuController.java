package com.myproyect.controllers;

import java.sql.SQLException;
import java.util.List;

import com.myproyect.models.Menu;
import com.myproyect.services.MenuService;

public class MenuController {
    private final MenuService menuService;

    public MenuController() {
        this.menuService = new MenuService(null);
    }

    public void crear(Menu menu) throws SQLException {
        menuService.crear(menu);
    }

    public List<Menu> listar() throws SQLException {
        return menuService.listar();
    }

    public void actualizar(Menu menu) throws SQLException {
        menuService.actualizarMenu(menu);
    }

    public void eliminar(int id) throws SQLException {
        menuService.eliminar(id);
    }
}
