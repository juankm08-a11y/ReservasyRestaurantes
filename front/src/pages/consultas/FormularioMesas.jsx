import React, { useState } from "react";
import { escogerMesa } from "../../services/mesaService";
export default function FormularioMesas() {
  const [numero, setNumero] = useState("");
  const [capacidad, setCapacidad] = useState("");
  const [ubicacion, setUbicacion] = useState("");

  const resetForm = () => {
    setNumero("");
    setCapacidad("");
    setUbicacion("");
  };
  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!numero || !ubicacion || !capacidad) {
      return;
    }

    const mesaData = {
      numero,
      capacidad,
      ubicacion,
    };

    try {
      await escogerMesa(mesaData);
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
          <label className="block font-bold mb-1 text-black">Numero</label>
          <input
            type="text"
            value={numero}
            onChange={(e) => setNumero(e.target.value)}
          />
        </div>
        <div>
          <label className="block font-bold mb-1 text-black">Capacidad</label>
          <input
            type="number"
            value={capacidad}
            onChange={(e) => setCapacidad(e.target.value)}
          />
        </div>
        <div>
          <label className="block font-bold mb-1 text-black">Ubicacion</label>
          <input
            type="text"
            value={ubicacion}
            onChange={(e) => setUbicacion(e.target.value)}
          />
        </div>
        <div className="flex flex-col md:flex-row gap-4 mt-4">
          <button
            type="submit"
            className="w-full bg-black text-white p-2 rounded hover:bg-gray-800"
          >
            Reservar Mesa
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
