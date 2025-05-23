import React, { useEffect, useState } from "react";
import { getPorcentajeCanceladas } from "../services/consultasavanzadasServices";

const PorcentajeCanceladas = () => {
  const [porcentaje, setPorcentaje] = useState(null);

  useEffect(() => {
    getPorcentajeCanceladas()
      .then((res) => setPorcentaje(res.data))
      .catch((err) => console.error("Error:", err));
  }, []);

  return (
    <div>
      <h2>Porcentaje de Reservas Canceladas</h2>
      {porcentaje !== null ? <p>{porcentaje}%</p> : <p>Cargando...</p>}
    </div>
  );
};

export default PorcentajeCanceladas;
