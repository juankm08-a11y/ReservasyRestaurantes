import React, { useEffect, useState } from "react";
import { getReservasCompletadasPorDiaHora } from "../services/consultasavanzadasServices";

const CompletadasPorDiaHora = () => {
  const [data, setData] = useState([]);

  useEffect(() => {
    getReservasCompletadasPorDiaHora()
      .then((res) => setData(res.data))
      .catch((err) => {
        console.error("Error:", err);
        setData([]);
      });
  }, []);

  return (
    <div>
      <h2>Reservas Completadas por Día y Hora</h2>
      <ul>
        {Array.isArray(data) ? (
          data.map((item, i) => (
            <li key={i}>
              Día: {item.dia} — Hora: {item.hora} — Total: {item.total}
            </li>
          ))
        ) : (
          <li>No hay datos o error al cargar.</li>
        )}
      </ul>
    </div>
  );
};

export default CompletadasPorDiaHora;
