import React, { useEffect, useState } from "react";
import { getReservasCompletadasPorDiaHora } from "../../services/consultasavanzadasServices";

const ReservasPorDiaHora = () => {
  const [data, setData] = useState([]);

  useEffect(() => {
    getReservasCompletadasPorDiaHora()
      .then((res) => setData(res.data))
      .catch((err) => console.error("Error:", err));
  }, []);

  return (
    <div className="w-full h-full flex justify-center items-center p-4">
      <h2>Reservas Completadas por Día y Hora</h2>
      <ul>
        {Array.isArray(data) && data.length > 0 ? (
          data.map((item, i) => (
            <li key={i}>
              {typeof item === "object" ? JSON.stringify(item) : item}
            </li>
          ))
        ) : (
          <li>No hay datos disponibles</li>
        )}
      </ul>
    </div>
  );
};

export default ReservasPorDiaHora;
