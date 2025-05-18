package com.myproyect.config;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class CorsUtil implements HttpHandler {
    public static void habilitarCors(HttpExchange exchange) {
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS ");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        CorsUtil.habilitarCors(exchange);

        if ("OPTIONS".equals(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(204, 1);
        }
    }
}
