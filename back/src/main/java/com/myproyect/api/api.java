package com.myproyect.api;

import static spark.Spark.*;

public class Api {
    public static void init() {
        port(8080);
    }

    public static void routes() {

        get("/lista-clientes", (req, res) -> "ver clientes");
        post("/crear-clientes", (req, res) -> "Crear cliente");
        put("/actualizar-clientes", (req, res) -> "Actualizar cliente");
        delete("/eliminar-clientes", (req, res) -> "Eliminar Cliente");

        get("/lista-mesas", (req, res) -> "ver mesas");
        post("/escoger-mesas", (req, res) -> "escoger mesa");
        put("/actualizar-mesas", (req, res) -> "Actualizar mesa");
        delete("/eliminar-mesas", (req, res) -> "Eliminar mesa");

        get("/lista-reservas", (req, res) -> "ver reservas");
        post("/crear-reservas", (req, res) -> "ver Reserva");
        put("/actualizar-reservas", (req, res) -> "Actualizar reserva");
        delete("/eliminar-reservas", (req, res) -> "Eliminar reserva");

    }
}
