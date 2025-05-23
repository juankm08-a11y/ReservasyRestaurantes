import React from "react";
import { Link } from "react-router-dom";

export default function ConsultasAvanzadas() {
  const links = [
    { path: "/consultas/reservas-dia-hora", label: "Reservas por Día y Hora" },
    {
      path: "/consultas/reservas-mesa-hora",
      label: "Reservas por Mesa y Hora",
    },
    {
      path: "/consultas/cancelzadas-ultimos-3-meses",
      label: "Canceladas Últimos 3 Meses",
    },
    { path: "/consultas/clientes-frecuentes", label: "Clientes Frecuentes" },
    {
      path: "/consultas/porcentaje-canceladas",
      label: "Porcentaje Canceladas",
    },
  ];

  return (
    <div>
      <h1>Consultas Avanzadas</h1>
      <ul>
        {links.map((link) => (
          <li key={link.path}>
            <Link to={link.path}>{link.label}</Link>
          </li>
        ))}
      </ul>
    </div>
  );
}
