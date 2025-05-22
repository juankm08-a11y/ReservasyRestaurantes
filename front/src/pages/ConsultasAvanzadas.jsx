import { useState, useEffect } from "react";
import axios from "axios";
import React from "react";

export default function ConsultasAvanzadas() {
  const [completadas, setCompletadas] = useState([]);
  const [canceladas, setCanceladas] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      setError(null);
      try {
        const [res1, res2] = await Promise.all([
          axios.get(
            "http://localhost:8080/api/reservas/completadas-por-dia-hora"
          ),
          axios.get(
            "http://localhost:8080/api/reservas/canceladas-ultimos-3-meses"
          ),
        ]);
        setCompletadas(res1.data);
        setCanceladas(res2.data);
      } catch (err) {
        setError(err.message || "Error al cargar datos");
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  if (loading) return <p className="p-4">Cargando datos...</p>;
  if (error) return <p className="p-4 text-red-600">{error}</p>;

  return (
    <div className="max-w-4xl mx-auto p-6 space-y-8">
      <h1 className="text-2xl font-bold text-center">Consultas Avanzadas</h1>

      <section className="border p-4 rounded shadow">
        <h2 className="text-xl font-semibold mb-4">
          Reservas completadas por Día y Hora
        </h2>
        <table className="w-full table-auto border-collapse">
          <thead>
            <tr className="bg-gray-100">
              <th className="border px-2 py-1">Día</th>
              <th className="border px-2 py-1">Hora</th>
              <th className="border px-2 py-1">Total</th>
            </tr>
          </thead>
          <tbody>
            {completadas.map((r, idx) => (
              <tr key={idx} className="hover:bg-gray-50">
                <td className="border px-2 py-1">{r.dia}</td>
                <td className="border px-2 py-1">{r.hora}</td>
                <td className="border px-2 py-1 text-right">{r.total}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </section>

      <section className="border p-4 rounded shadow">
        <h2 className="text-xl font-semibold mb-4">
          Reservas canceladas (últimos 3 meses)
        </h2>
        <table className="w-full table-auto border-collapse">
          <thead>
            <tr className="bg-gray-100">
              {canceladas.length > 0 &&
                Object.keys(canceladas[0]).map((key) => (
                  <th key={key} className="border px-2 py-1 capitalize">
                    {key}
                  </th>
                ))}
            </tr>
          </thead>
          <tbody>
            {canceladas.map((r, idx) => (
              <tr key={idx} className="hover:bg-gray-50">
                {Object.values(r).map((val, i) => (
                  <td key={i} className="border px-2 py-1">
                    {val}
                  </td>
                ))}
              </tr>
            ))}
          </tbody>
        </table>
      </section>

      <div className="text-center">
        <button
          onClick={() => window.location.reload()}
          className="mt-4 px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700"
        >
          Recargar
        </button>
      </div>
    </div>
  );
}
