import React, { useEffect, useState } from "react";
import {
  getCanceladasUltimos3Meses,
  getReservasCompletadasPorDiaHora,
  getReservasPorMesaHora,
  getClientesFrecuentes,
  getPorcentajeCanceladas,
} from "../services/consultasavanzadasServices";

export default function ConsultasAvanzadas() {
  const [canceladas, setCanceladas] = useState([]);
  const [porDiaHora, setPorDiaHora] = useState([]);
  const [porMesaHora, setPorMesaHora] = useState([]);
  const [clientesFrecuentes, setClientesFrecuentes] = useState([]);
  const [porcentaje, setPorcentaje] = useState(null);
  const [cargando, setCargando] = useState(true);

  useEffect(() => {
    setCargando(true);
    Promise.all([
      getCanceladasUltimos3Meses(),
      getReservasCompletadasPorDiaHora(),
      getReservasPorMesaHora(),
      getClientesFrecuentes(),
      getPorcentajeCanceladas(),
    ])
      .then(([res1, res2, res3, res4, res5]) => {
        setCanceladas(res1.data);
        setPorDiaHora(res2.data);
        setPorMesaHora(res3.data);
        setClientesFrecuentes(res4.data);
        const pct =
          res5.data.porcentaje_canceladas ??
          res5.data[0]?.porcentaje_canceladas ??
          null;
        setPorcentaje(pct);
      })
      .catch((err) => console.error("Error cargando datos:", err))
      .finally(() => setCargando(false));
  }, []);

  if (cargando)
    return <p className="text-center">Cargando consultas avanzadas...</p>;

  return (
    <div className="p-6 space-y-6">
      <h1 className="text-2xl font-bold">Consultas Avanzadas</h1>

      <section>
        <h2 className="text-lg font-semibold">
          1. Reservas Canceladas Últimos 3 Meses
        </h2>
        {canceladas.length === 0 ? (
          <p>No hay reservas canceladas recientes.</p>
        ) : (
          <table className="table-auto border mt-2 border-collapse border-gray-300">
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
                  <td className="border px-3 py-1">{r.fecha}</td>
                  <td className="border px-3 py-1">{r.estado}</td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </section>

      <section>
        <h2 className="text-lg font-semibold">
          2. Reservas Completadas por Día y Hora
        </h2>
        {porDiaHora.length === 0 ? (
          <p>No hay reservas completadas.</p>
        ) : (
          <table className="table-auto border mt-2 border-collapse border-gray-300">
            <thead>
              <tr>
                <th className="border px-3 py-1">Día</th>
                <th className="border px-3 py-1">Hora</th>
                <th className="border px-3 py-1">Total</th>
              </tr>
            </thead>
            <tbody>
              {porDiaHora.map((r, i) => (
                <tr key={`${r.dia}-${r.hora}-${i}`}>
                  <td className="border px-3 py-1">{r.dia}</td>
                  <td className="border px-3 py-1">{r.hora}</td>
                  <td className="border px-3 py-1">{r.total}</td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </section>

      <section>
        <h2 className="text-lg font-semibold">
          3. Mesas más Reservadas por Hora
        </h2>
        {porMesaHora.length === 0 ? (
          <p>No hay datos de mesas reservadas.</p>
        ) : (
          <table className="table-auto border mt-2 border-collapse border-gray-300">
            <thead>
              <tr>
                <th className="border px-3 py-1">Mesa ID</th>
                <th className="border px-3 py-1">Hora</th>
                <th className="border px-3 py-1">Total Reservas</th>
              </tr>
            </thead>
            <tbody>
              {porMesaHora.map((r, index) => (
                <tr key={`${r.mesa_id}-${r.hora}-${index}`}>
                  <td className="border px-3 py-1">{r.mesa_id}</td>
                  <td className="border px-3 py-1">{r.hora}</td>
                  <td className="border px-3 py-1">{r.total_reservas}</td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </section>

      <section>
        <h2 className="text-lg font-semibold">
          4. Clientes con más de 5 Visitas por Mes
        </h2>
        {clientesFrecuentes.length === 0 ? (
          <p>No hay clientes frecuentes registrados.</p>
        ) : (
          <table className="table-auto border mt-2 border-collapse border-gray-300">
            <thead>
              <tr>
                <th className="px-3 py-1 border">Cliente ID</th>
                <th className="px-3 py-1 border">Mes</th>
                <th className="px-3 py-1 border">Total Visitas</th>
              </tr>
            </thead>
            <tbody>
              {clientesFrecuentes.map((c, i) => (
                <tr key={`${c.cliente_id}-${c.mes}-${i}`}>
                  <td className="border px-3 py-1">{c.cliente_id}</td>
                  <td className="border px-3 py-1">{c.mes}</td>
                  <td className="border px-3 py-1">{c.total_visitas}</td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </section>

      <section>
        <h2 className="text-lg font-semibold">5. Porcentaje de Canceladas</h2>
        <p className="text-xl font-bold">
          {porcentaje !== null ? `${porcentaje.toFixed(2)}%` : "Sin datos"}
        </p>
      </section>
    </div>
  );
}
