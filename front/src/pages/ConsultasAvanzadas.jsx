import React, { useEffect, useState } from "react";
import {
  getCanceladasUltimos3Meses,
  getReservasCompletadasPorDiaHora,
  getReservasPorMesaHora,
  getClientesFrecuentes,
  getPorcentajeCanceladas,
  getTotalPorEstado,
} from "../services/consultasavanzadasServices";

export default function ConsultasAvanzadas() {
  const [canceladas, setCanceladas] = useState([]);
  const [porDiaHora, setPorDiaHora] = useState([]);
  const [porMesaHora, setPorMesaHora] = useState([]);
  const [clientesFrecuentes, setClientesFrecuentes] = useState([]);
  const [porcentaje, setPorcentaje] = useState(null);
  const [data, setData] = useState([]);
  const [cargando, setCargando] = useState(true);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    setCargando(true);
    Promise.all([
      getCanceladasUltimos3Meses(),
      getReservasCompletadasPorDiaHora(),
      getReservasPorMesaHora(),
      getClientesFrecuentes(),
    ])
      .then(([res1, res2, res3, res4]) => {
        setCanceladas(res1.data);
        setPorDiaHora(res2.data);
        setPorMesaHora(res3.data);
        setClientesFrecuentes(res4.data);
        setError(null);
      })
      .catch((err) => {
        console.error("Error cargando datos:", err);
        setError("Error cargando datos de las consultas.");
      })
      .finally(() => setCargando(false));
  }, []);

  useEffect(() => {
    getPorcentajeCanceladas()
      .then((response) => setPorcentaje(response.data.porcentaje_canceladas))
      .catch((err) => console.error("Error porcentaje:", err));
  }, []);

  useEffect(() => {
    getTotalPorEstado()
      .then((res) => {
        setData(res.data);
        setLoading(false);
      })
      .catch((err) => {
        console.error(err);
        setLoading(false);
      });
  }, []);

  if (cargando)
    return <p className="text-center">Cargando consultas avanzadas...</p>;
  if (error) return <p className="text-red-600 text-center">{error}</p>;

  return (
    <div className="p-6 space-y-6 flex flex-col items-center">
      <h1 className="text-2xl font-bold">Consultas Avanzadas</h1>

      {/* 1. Reservas Completadas por Día y Hora */}
      <section className="w-full max-w-xl">
        <h2 className="text-lg font-semibold">
          1. Reservas Completadas por Día y Hora
        </h2>
        {porDiaHora.length === 0 ? (
          <p>No hay reservas completadas.</p>
        ) : (
          <table className="w-full table-auto border-collapse border">
            <thead>
              <tr>
                <th className="border px-3 py-1">Día</th>
                <th className="border px-3 py-1">Mes</th>
                <th className="border px-3 py-1">Hora</th>
                <th className="border px-3 py-1">Total</th>
              </tr>
            </thead>
            <tbody>
              {porDiaHora.map((r, i) => (
                <tr key={`${r.dia_nombre}-${r.mes_nombre}-${r.hora}-${i}`}>
                  <td className="border px-3 py-1">{r.dia_nombre}</td>
                  <td className="border px-3 py-1">{r.mes_nombre}</td>
                  <td className="border px-3 py-1">{r.hora}</td>
                  <td className="border px-3 py-1">{r.total}</td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </section>

      <section className="w-full max-w-xl">
        <h2 className="text-lg font-semibold">
          2. Reservas Canceladas Últimos 3 Meses
        </h2>
        {canceladas.length === 0 ? (
          <p>No hay reservas canceladas recientes.</p>
        ) : (
          <table className="w-full table-auto border-collapse border">
            <thead>
              <tr>
                <th className="border px-3 py-1">Reserva ID</th>
                <th className="border px-3 py-1">Fecha</th>
                <th className="border px-3 py-1">Estado</th>
              </tr>
            </thead>
            <tbody>
              {canceladas.map((r) => (
                <tr key={r.reserva_id}>
                  <td className="border px-3 py-1">{r.reserva_id}</td>
                  <td className="border px-3 py-1">
                    {r.fecha
                      ? `${new Date(r.fecha).toLocaleDateString()} (${
                          r.mes_nombre
                        })`
                      : r.mes_nombre}
                  </td>
                  <td className="border px-3 py-1">{r.estado}</td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </section>

      <section className="w-full max-w-xl">
        <h2 className="text-lg font-semibold">3. Total Reservas por Estado</h2>
        {loading ? (
          <p>Cargando...</p>
        ) : (
          <ul>
            {data.map(({ estado, total }) => (
              <li key={estado}>
                <strong>{estado}</strong>: {total}
              </li>
            ))}
          </ul>
        )}
      </section>

      <section className="w-full max-w-xl">
        <h2 className="text-lg font-semibold">
          4. Mesas más Reservadas por Hora
        </h2>
        {porMesaHora.length === 0 ? (
          <p>No hay datos de mesas reservadas.</p>
        ) : (
          <table className="w-full table-auto border-collapse border">
            <thead>
              <tr>
                <th className="border px-3 py-1">Mesa ID</th>
                <th className="border px-3 py-1">Hora</th>
                <th className="border px-3 py-1">Total Reservas</th>
              </tr>
            </thead>
            <tbody>
              {porMesaHora.map((r, i) => (
                <tr key={`${r.mesa_id}-${r.hora}-${i}`}>
                  <td className="border px-3 py-1">{r.mesa_id}</td>
                  <td className="border px-3 py-1">{r.hora}</td>
                  <td className="border px-3 py-1">{r.total_reservas}</td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </section>

      <section className="w-full max-w-xl">
        <h2 className="text-lg font-semibold">
          5. Clientes con más de 5 Visitas por Mes
        </h2>
        <table className="w-full table-auto border-collapse border">
          <thead>
            <tr>
              <th className="border px-3 py-1">Cliente ID</th>
              <th className="border px-3 py-1">Mes</th>
              <th className="border px-3 py-1">Total Visitas</th>
            </tr>
          </thead>
          <tbody>
            {clientesFrecuentes.length === 0 ? (
              <tr>
                <td colSpan={3} className="border px-3 py-1 text-center">
                  No hay clientes frecuentes registrados.
                </td>
              </tr>
            ) : (
              clientesFrecuentes.map((c, i) => (
                <tr key={`${c.cliente_id}-${c.mes_nombre}-${i}`}>
                  <td className="border px-3 py-1">{c.cliente_id}</td>
                  <td className="border px-3 py-1">{c.mes_nombre}</td>
                  <td className="border px-3 py-1">{c.total_visitas}</td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </section>

      <section className="w-full max-w-xl text-center">
        <h2 className="text-lg font-semibold">6. Porcentaje de Canceladas</h2>
        <p className="text-xl font-bold mt-2">
          {porcentaje !== null ? `${porcentaje.toFixed(2)}%` : "Sin datos"}
        </p>
      </section>
    </div>
  );
}
