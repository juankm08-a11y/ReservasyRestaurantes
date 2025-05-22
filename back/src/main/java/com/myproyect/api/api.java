package com.myproyect.api;

import static spark.Spark.*;
import com.myproyect.utils.DatabaseConnection;

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

        get("/api/reservas/completadas-por-dia-hora", (req, res) -> {
            res.type("application/json");
            String sql = "SELECT DAYNAME(fecha) AS dia, hora, COUNT(*) AS total "
                    + "FROM reservas "
                    + "WHERE estado = 'Completada' "
                    + "GROUP BY dia, hora "
                    + "ORDER BY total DESC";

            StringBuilder sb = new StringBuilder();
            sb.append("[");
            try (
                    var conn = DatabaseConnection.getInstance();
                    var pst = conn.prepareStatement(sql);
                    var rs = pst.executeQuery()) {
                boolean first = true;
                while (rs.next()) {
                    if (!first)
                        sb.append(',');
                    first = false;
                    sb.append("{")
                            .append("\"dia\":\"").append(rs.getString("dia")).append("\",")
                            .append("\"hora\":\"").append(rs.getString("hora")).append("\",")
                            .append("\"total\":").append(rs.getInt("total"))
                            .append("}");
                }
            } catch (Exception e) {
                halt(500, "Error SQL:" + e.getMessage());
            }
            return sb.append("]").toString();

        });

        get("/api/reservas/canceladas-ultimos-3-meses", (req, res) -> {
            res.type("application/json");
            String sql = "SELECT * FROM reservas "
                    + "WHERE estado = 'Cancelada' "
                    + "AND fecha >= CURDATE() - INTERVAL 3 MONTH";
            StringBuilder sb = new StringBuilder("[");
            try (var conn = DatabaseConnection.getInstance();
                    var pst = conn.prepareStatement(sql);
                    var rs = pst.executeQuery()) {
                var md = rs.getMetaData();
                int cols = md.getColumnCount();
                boolean first = true;
                while (rs.next()) {
                    if (!first)
                        sb.append(',');
                    first = false;
                    sb.append('{');
                    for (int i = 1; i <= cols; i++) {
                        String col = md.getColumnLabel(i);
                        Object val = rs.getObject(i);
                        sb.append('"').append(col).append('"').append(':');
                        if (val instanceof Number)
                            sb.append(val);
                        else
                            sb.append('"').append(val).append('"');
                        if (i < cols)
                            sb.append(',');
                    }
                    sb.append('}');
                }
            } catch (Exception e) {
                halt(500, "Error SQL: " + e.getMessage());
            }
            sb.append("]");
            return sb.toString();
        });

    }
}
