import React, { useEffect, useState } from "react";
import { getClientesFrecuentes } from "../../services/consultasavanzadasServices";

export default function ClientesFrecuentes() {
  const [datos, setDatos] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    getClientesFrecuentes()
      .then((data) => setDatos(data))
      .catch((err) => console.error(err))
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <p>Cargando clientes frecuentes…</p>;
  if (datos.length === 0) return <p>No hay clientes con más de 5 visitas.</p>;

  return (
    <div>
      <h3>Clientes con más de 5 Visitas por Mes</h3>
      <table>
        <thead>
          <tr>
            <th>Cliente ID</th>
            <th>Mes</th>
            <th>Total Visitas</th>
          </tr>
        </thead>
        <tbody>
          {datos.map((c) => (
            <tr key={`${c.cliente_id}-${c.mes}`}>
              <td>{c.cliente_id}</td>
              <td>{c.mes}</td>
              <td>{c.total_visitas}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
