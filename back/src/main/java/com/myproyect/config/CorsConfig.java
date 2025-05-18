package com.myproyect.config;

import static spark.Spark.*;

public class CorsConfig {
    public static void enableCors() {
        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }
            String accessControlRequestMehod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMehod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMehod);
            }
            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
            response.header("Access-Control-Allow-Headers", "Content-Type, Authorization");
        });
    }
}
