import React, { useEffect, useState } from "react";
import { getReservasPorMesaHora } from "../services/consultasavanzadasServices";

const ReservasPorMesaHora = () => {
  const [data, setData] = useState([]);

  useEffect(() => {
    getReservasPorMesaHora()
      .then((res) => setData(res.data))
      .catch((err) => {
        console.error("Error:", err);
        setData([]);
      });
  }, []);

  return (
    <div>
      <h2>Mesas Más Reservadas por Hora</h2>
      <ul>
        {Array.isArray(data) ? (
          data.map((d, i) => (
            <li key={i}>
              Mesa {d.mesa_id} — Hora: {d.hora} — Total Reservas:{" "}
              {d.total_reservas}
            </li>
          ))
        ) : (
          <li>No hay datos o error al cargar.</li>
        )}
      </ul>
    </div>
  );
};

export default ReservasPorMesaHora;
