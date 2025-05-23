// src/pages/Consultas/PorcentajeCanceladas.jsx
import React, { useEffect, useState } from "react";
import { getPorcentajeCanceladas } from "../../services/consultasavanzadasServices";

export default function PorcentajeCanceladas() {
  const [porcentaje, setPorcentaje] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    getPorcentajeCanceladas()
      .then((data) => {
        // Asumiendo que el servicio devuelve [{ porcentaje_canceladas: X }]
        if (Array.isArray(data) && data.length > 0) {
          setPorcentaje(data[0].porcentaje_canceladas);
        } else {
          setError("Respuesta inesperada del servidor");
        }
      })
      .catch((err) => {
        console.error(err);
        setError("Error al obtener el porcentaje");
      })
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <p>Cargando porcentaje de canceladas…</p>;
  if (error) return <p className="text-red-600">{error}</p>;

  return (
    <div>
      <h3>Porcentaje de Reservas Canceladas</h3>
      <p style={{ fontSize: "1.5rem", fontWeight: "bold" }}>
        {porcentaje !== null
          ? `${porcentaje.toFixed(2)}%`
          : "No hay datos disponibles."}
      </p>
    </div>
  );
}
