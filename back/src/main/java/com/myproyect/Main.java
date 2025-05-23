package com.myproyect;

import java.sql.Connection;
import java.sql.DriverManager;
import com.myproyect.api.Api;
import com.myproyect.config.CorsConfig;
import com.myproyect.utils.DatabaseConnection;

public class Main {
    public static void main(String[] args) {
        try (Connection myConnection = DatabaseConnection.getInstance()) {
            System.out.println("Conexion exitosa");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());

        }
        Api.init();
        CorsConfig.enableCors();

        Api.routes();

    }

}