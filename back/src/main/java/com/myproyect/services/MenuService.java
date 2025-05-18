package com.myproyect.services;

import java.sql.SQLException;
import java.util.*;

import com.myproyect.models.Menu;
import com.myproyect.repositories.MenuRepository;

public class MenuService {
    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public void crear(Menu menu) throws SQLException {
        menuRepository.insertarMenu(menu);
    }

    public List<Menu> listar() throws SQLException {
        return menuRepository.obtenerTodosLosMenus();
    }

    public void actualizarMenu(Menu menu) throws SQLException {
        menuRepository.actualizarMenu(menu);
    }

    public void eliminar(int id) throws SQLException {
        menuRepository.eliminarMenu(id);
    }
}
