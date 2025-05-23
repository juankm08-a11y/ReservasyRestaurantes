import React, { useEffect, useState } from "react";
import { getClientesFrecuentes } from "../services/consultasavanzadasServices";

function ClientesFrecuentes() {
  const [clientes, setClientes] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    async function fetchData() {
      try {
        const response = await getClientesFrecuentes();
        const lista = Array.isArray(response)
          ? response
          : Array.isArray(response.data)
          ? response.data
          : [];
        setClientes(lista);
      } catch (err) {
        console.error("Error al obtener clientes frecuentes:", err);
        setError("Error al cargar clientes frecuentes.");
      } finally {
        setLoading(false);
      }
    }

    fetchData();
  }, []);

  if (loading) {
    return <p>Cargando...</p>;
  }

  if (error) {
    return <p>{error}</p>;
  }

  if (!Array.isArray(clientes) || clientes.length === 0) {
    return <p>No hay clientes frecuentes para mostrar.</p>;
  }

  return (
    <div className="clientes-frecuentes-container">
      <h2>Clientes Frecuentes</h2>
      <table>
        <thead>
          <tr>
            <th>Nombre</th>
            <th>Teléfono</th>
            <th>Correo</th>
            <th>Total Compras</th>
          </tr>
        </thead>
        <tbody>
          {clientes.map((cliente) => (
            <tr key={cliente.id || cliente._id || Math.random()}>
              <td>{cliente.nombre}</td>
              <td>{cliente.telefono}</td>
              <td>{cliente.correo}</td>
              <td>{cliente.total_compras}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default ClientesFrecuentes;
