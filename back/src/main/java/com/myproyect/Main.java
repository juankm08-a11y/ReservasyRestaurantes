package com.myproyect;

import java.sql.Connection;
import java.sql.DriverManager;

import com.myproyect.utils.DatabaseConnection;

public class Main {
    public static void main(String[] args) {
        try (Connection myConnection = DatabaseConnection.getInstance()) {
            System.out.println("Conexion exitosa");
        } catch (Exception e) {
            System.out.println("Conexion fallida");
        }
    }
}