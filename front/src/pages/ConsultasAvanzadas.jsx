import React from "react";
import { Link } from "react-router-dom";

export default function ConsultasAvanzadas() {
  const links = [
    { path: "/reservas-dia-hora", label: "Reservas por Día y Hora" },
    {
      path: "/reservas-mesa-hora",
      label: "Reservas por Mesa y Hora",
    },
    {
      path: "/canceladas-ultimos-3-meses",
      label: "Canceladas Últimos 3 Meses",
    },
    { path: "/clientes-frecuentes", label: "Clientes Frecuentes" },
    {
      path: "/porcentaje-canceladas",
      label: "Porcentaje Canceladas",
    },
  ];

  return (
    <div>
      <h1>Consultas Avanzadas</h1>
      <ul>
        {links.map((link, i) => (
          <li key={i}>
            <Link to={link.path}>{link.label}</Link>
          </li>
        ))}
      </ul>
    </div>
  );
}
