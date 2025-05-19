package com.myproyect;

import java.sql.Connection;
import java.sql.DriverManager;
import com.myproyect.controllers.*;
import com.myproyect.api.Api;
import com.myproyect.config.CorsConfig;
import com.myproyect.utils.DatabaseConnection;

public class Main {
    public static void main(String[] args) {
        try (Connection myConnection = DatabaseConnection.getInstance()) {
            System.out.println("Conexion exitosa");
        } catch (Exception e) {
            System.out.println("Conexion fallida" + e.getMessage());
            e.printStackTrace();
        }
        Api.init();
        CorsConfig.enableCors();

        new ClienteController();
        new MesaController();
        new ReservaController();
        new ReservaController();
        new CocineroController();
        new EncargadoAseoController();
        new MenuController();
        Api.routes();

    }

}