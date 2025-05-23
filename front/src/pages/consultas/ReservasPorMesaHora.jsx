import React, { useEffect, useState } from "react";
import { getReservasPorMesaHora } from "../../services/consultasavanzadasServices";

const ReservasPorMesaHora = () => {
  const [data, setData] = useState([]);

  useEffect(() => {
    getReservasPorMesaHora()
      .then((res) => setData(res.data))
      .catch((err) => console.error("Error:", err));
  }, []);

  return (
    <div>
      <h2>Mesas Más Reservadas por Hora</h2>
      <ul>
        {data.map((d, i) => (
          <li key={i}>{d}</li>
        ))}
      </ul>
    </div>
  );
};

export default ReservasPorMesaHora;
