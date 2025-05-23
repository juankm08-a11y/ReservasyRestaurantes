import React, { useState } from "react";
import { crearReserva } from "../../services/reservaService";
export default function FormularioReservas() {
  const [cliente_id, setCliente_Id] = useState("");
  const [mesa_id, setMesa_id] = useState("");
  const [fecha, setFecha] = useState("");
  const [hora, setHora] = useState("");
  const [estado, setEstado] = useState("");

  const resetForm = () => {
    setCliente_Id("");
    setMesa_id("");
    setFecha("");
    setHora("");
    setEstado("");
  };
  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!cliente_id || !mesa_id || !fecha || !hora || !estado) {
      return;
    }

    const mesaData = {
      cliente_id,
      mesa_id,
      fecha,
      hora,
      estado,
    };

    try {
      await crearReserva(mesaData);
      alert("Mesa reservada exitosamente");
      resetForm();
    } catch (error) {
      alert("Error al reserva mesa");
    }
  };

  const handleCancelar = () => {
    window.history.back();
  };
  return (
    <div className="w-full h-full flex justify-center items-center p-4">
      <form onSubmit={handleSubmit} className="space-y-4">
        <div>
          <label className="block font-bold mb-1 text-black">Cliente Id</label>
          <input
            type="number"
            value={cliente_id}
            onChange={(e) => setCliente_Id(e.target.value)}
          />
        </div>
        <div>
          <label className="block font-bold mb-1 text-black">Mesa Id</label>
          <input
            type="number"
            value={mesa_id}
            onChange={(e) => setMesa_id(e.target.value)}
          />
        </div>
        <div>
          <label className="block font-bold mb-1 text-black">Fecha</label>
          <input
            type="date"
            value={fecha}
            onChange={(e) => setFecha(e.target.value)}
          />
        </div>
        <div>
          <label className="block font-bold mb-1 text-black">Hora</label>
          <input
            type="time"
            value={hora}
            onChange={(e) => setHora(e.target.value)}
          />
        </div>
        <div>
          <select
            value={estado}
            onChange={(e) => setEstado(e.target.value)}
            className="border-2 p-2 mb-4 w-full"
          >
            <option value="">Selecciona un estado</option>
            <option value="mesas">Activa</option>
            <option value="clientes">Cancelada</option>
            <option value="horarios">Completada</option>
          </select>
        </div>

        <div className="flex flex-col md:flex-row gap-4 mt-4">
          <button
            type="submit"
            className="w-full bg-black text-white p-2 rounded hover:bg-gray-800"
          >
            Hacer una reserva
          </button>
          <button
            onClick={handleCancelar}
            className="w-full bg-black text-white p-2 rounded hover:bg-gray-800"
          >
            Cancelar
          </button>
        </div>
      </form>
    </div>
  );
}
