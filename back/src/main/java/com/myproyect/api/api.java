package com.myproyect.api;

import static spark.Spark.*;
import com.myproyect.utils.DatabaseConnection;

public class Api {
    public static void init() {
        port(8080);
    }

    public static void routes() {

        get("/api/lista-clientes", (req, res) -> "ver clientes");
        post("/api/crear-clientes", (req, res) -> "Crear cliente");
        put("/api/actualizar-clientes", (req, res) -> "Actualizar cliente");
        delete("/api/eliminar-clientes", (req, res) -> "Eliminar Cliente");

        get("/api/lista-mesas", (req, res) -> "ver mesas");
        post("/api/escoger-mesas", (req, res) -> "escoger mesa");
        put("/api/actualizar-mesas", (req, res) -> "Actualizar mesa");
        delete("/api/eliminar-mesas", (req, res) -> "Eliminar mesa");

        get("/api/lista-reservas", (req, res) -> "ver reservas");
        post("/api/crear-reservas", (req, res) -> "ver Reserva");
        put("/api/actualizar-reservas", (req, res) -> "Actualizar reserva");
        delete("/api/eliminar-reservas", (req, res) -> "Eliminar reserva");

        // Corregido: usar dia_nombre y mes_nombre directamente, sin funciones
        // DAYNAME/MONTHNAME
        get("/api/reservas/completadas-por-dia-hora", (req, res) -> {
            res.type("application/json");
            String sql = "SELECT dia_nombre, mes_nombre, hora, COUNT(*) AS total "
                    + "FROM reserva "
                    + "WHERE estado = 'Completada' "
                    + "GROUP BY dia_nombre, mes_nombre, hora "
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
                            .append("\"dia_nombre\":\"").append(rs.getString("dia_nombre")).append("\",")
                            .append("\"mes_nombre\":\"").append(rs.getString("mes_nombre")).append("\",")
                            .append("\"hora\":\"").append(rs.getString("hora")).append("\",")
                            .append("\"total\":").append(rs.getInt("total"))
                            .append("}");
                }
            } catch (Exception e) {
                halt(500, "Error SQL: " + e.getMessage());
            }
            return sb.append("]").toString();
        });

        // Corregido: sin funciones DAYNAME, MONTHNAME y sin filtro sobre fecha (que no
        // existe)
        get("/api/reservas/canceladas-ultimos-3-meses", (req, res) -> {
            res.type("application/json");
            String sql = "SELECT * FROM reserva "
                    + "WHERE estado = 'Cancelada' "
                    + "AND mes_nombre >= MONTHNAME(CURDATE() - INTERVAL 3 MONTH)";

            StringBuilder sb = new StringBuilder("[");
            try (
                    var conn = DatabaseConnection.getInstance();
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

        get("/api/reservas/completadas-por-mesa-hora", (req, res) -> {
            res.type("application/json");
            String sql = "SELECT mesa_id, hora, COUNT(*) AS total_reservas "
                    + "FROM reserva "
                    + "WHERE estado = 'Completada' "
                    + "GROUP BY mesa_id, hora "
                    + "ORDER BY hora ASC, mesa_id ASC, total_reservas ASC";
            StringBuilder sb = new StringBuilder("[");
            try (
                    var conn = DatabaseConnection.getInstance();
                    var pst = conn.prepareStatement(sql);
                    var rs = pst.executeQuery()) {
                boolean first = true;
                while (rs.next()) {
                    if (!first)
                        sb.append(',');
                    first = false;
                    sb.append('{')
                            .append("\"mesa_id\":").append(rs.getInt("mesa_id")).append(',')
                            .append("\"hora\":\"").append(rs.getString("hora")).append("\",")
                            .append("\"total_reservas\":").append(rs.getInt("total_reservas"))
                            .append('}');
                }
            } catch (Exception e) {
                halt(500, "Error SQL: " + e.getMessage());
            }
            sb.append("]");
            return sb.toString();
        });

        get("/api/reservas/total-por-estado", (req, res) -> {
            res.type("application/json");
            String sql = "SELECT estado, COUNT(*) AS total FROM reserva GROUP BY estado";

            StringBuilder sb = new StringBuilder("[");
            try (
                    var conn = DatabaseConnection.getInstance();
                    var pst = conn.prepareStatement(sql);
                    var rs = pst.executeQuery()) {
                boolean first = true;
                while (rs.next()) {
                    if (!first)
                        sb.append(",");
                    first = false;

                    sb.append("{")
                            .append("\"estado\":\"").append(rs.getString("estado")).append("\",")
                            .append("\"total\":").append(rs.getInt("total"))
                            .append("}");
                }
            } catch (Exception e) {
                halt(500, "Error SQL: " + e.getMessage());
            }
            sb.append("]");
            return sb.toString();
        });

        // Corregido: usar mes_nombre directo, sin funciones MONTH(), MONTHNAME(), ni
        // columna fecha
        get("/api/reservas/clientes-frecuentes", (req, res) -> {
            res.type("application/json");
            String sql = "SELECT cliente_id, mes_nombre, COUNT(*) AS total_visitas "
                    + "FROM reserva "
                    + "WHERE estado = 'Completada' "
                    + "GROUP BY cliente_id, mes_nombre "
                    + "HAVING total_visitas > 5";
            StringBuilder sb = new StringBuilder("[");
            try (
                    var conn = DatabaseConnection.getInstance();
                    var pst = conn.prepareStatement(sql);
                    var rs = pst.executeQuery()) {
                boolean first = true;
                while (rs.next()) {
                    if (!first)
                        sb.append(',');
                    first = false;
                    sb.append('{')
                            .append("\"cliente_id\":").append(rs.getInt("cliente_id")).append(',')
                            .append("\"mes_nombre\":\"").append(rs.getString("mes_nombre")).append("\",")
                            .append("\"total_visitas\":").append(rs.getInt("total_visitas"))
                            .append('}');
                }
            } catch (Exception e) {
                halt(500, "Error SQL: " + e.getMessage());
            }
            sb.append("]");
            return sb.toString();
        });

        get("/api/reservas/porcentaje-canceladas", (req, res) -> {
            res.type("application/json");
            String sql = "SELECT "
                    + "(SELECT COUNT(*) FROM reserva WHERE estado = 'Cancelada') * 100.0 / COUNT(*) AS porcentaje_canceladas "
                    + "FROM reserva";
            try (var conn = DatabaseConnection.getInstance();
                    var pst = conn.prepareStatement(sql);
                    var rs = pst.executeQuery()) {

                if (rs.next()) {
                    return "{\"porcentaje_canceladas\":" + rs.getDouble("porcentaje_canceladas") + "}";
                } else {
                    return "{\"porcentaje_canceladas\":0}";
                }
            } catch (Exception e) {
                halt(500, "Error SQL: " + e.getMessage());
                return null;
            }
        });

    }
}
