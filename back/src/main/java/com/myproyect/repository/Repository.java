package com.myproyect.repository;

import java.sql.SQLException;
import java.util.List;

public interface Repository<C> {
    List<C> findAll() throws SQLException;

    C getByID(Integer id) throws SQLException;

    void save(C c);

    void delete(Integer id);
}
