package com.myproyect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import com.myproyect.api.Api;
import com.myproyect.config.CorsConfig;
import com.myproyect.models.Cliente;
import com.myproyect.models.Mesa;
import com.myproyect.models.Reserva;
import com.myproyect.repositories.ClienteRepository;
import com.myproyect.repositories.MesaRepository;
import com.myproyect.repositories.ReservaRepository;
import com.myproyect.utils.DatabaseConnection;

public class Main {
    public static void main(String[] args) {
        try (Connection myConnection = DatabaseConnection.getInstance()) {
            System.out.println("Conexion exitosa");
            ReservaRepository reservaRepo = new ReservaRepository(myConnection);
            ClienteRepository clienteRepo = new ClienteRepository(myConnection);
            MesaRepository mesaRepo = new MesaRepository(myConnection);

            List<Reserva> reservas = reservaRepo.findAll();
            System.out.println("Reservas encontradas: " + reservas.size());

            List<Cliente> clientes = clienteRepo.findAll();
            System.out.println("Clientes encontrados: " + clientes.size());

            List<Mesa> mesas = mesaRepo.findAll();
            System.out.println("Mesas encontradas: " + mesas.size());
        } catch (Exception e) {
            System.out.println("Conexion fallida" + e.getMessage());
            e.printStackTrace();
        }
        Api.init();
        CorsConfig.enableCors();

        Api.routes();

    }

}