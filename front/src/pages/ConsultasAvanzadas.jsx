import React from "react";

export default function ConsultasAvanzadas() {
  const links = [
    { path: "/consultas/reservas-dia-hora", label: "Reservas por Dia y Hora" },
    {
      path: "/consultas/reservas-mesa-hora",
      label: "Reservas por Mesa y Hora",
    },
  ];
  return (
    <div>
      <h1>Consultas Avanzadas</h1>
      <ul></ul>
    </div>
  );
}
