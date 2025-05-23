import React, { useEffect, useState } from "react";
import { getCanceladasUltimos3Meses } from "../services/api";

const ReservasCanceladasUltimoTrimestre = () => {
  const [reservas, setReservas] = useState([]);

  useEffect(() => {
    getCanceladasUltimos3Meses()
      .then((res) => setReservas(res.data))
      .catch((err) => console.error("Error:", err));
  }, []);

  return (
    <div className="w-full h-full flex justify-center items-center p-4">
      <h2>Reservas Canceladas Últimos 3 Meses</h2>
      <ul>
        {reservas.map((r, i) => (
          <li key={i}>
            ID: {r.id}, Cliente: {r.clienteId}, Fecha: {r.fecha}, Hora: {r.hora}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ReservasCanceladasUltimoTrimestre;
