import React, { useState } from "react";

export default function FormularioClientes() {
  const [nombre, setNombre] = useState("");
  const [cedula, setCedula] = useState("");
  const [telefono, setTelefono] = useState("");
  const [email, setEmail] = useState("");

  const resetForm = () => {
    setNombre("");
    setCedula("");
    setTelefono("");
    setEmail("");
  };
  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!nombre || !cedula || !telefono || !email) {
      alert("Por favor llene los datos");
    }

    const clienteData = {
      nombre,
      cedula,
      telefono,
      email,
    };

    try {
      const nuevo = await createCliente(clienteData);
      resetForm();
    } catch (error) {
      alert("Error al registrar el cliente");
    }
  };

  const handleCancelar = () => {
    window.history.back();
  };
  return (
    <div className="w-full h-full flex justify-center items-center p-4">
      <form onSubmit={handleSubmit} className="space-y-4">
        <div>
          <label className="block font-bold mb-1 text-black">Nombre</label>
          <input
            type="text"
            value={nombre}
            onChange={(e) => setNombre(e.target.value)}
          />
        </div>
        <div>
          <label className="block font-bold mb-1 text-black">Cedula</label>
          <input
            type="text"
            value={cedula}
            onChange={(e) => setCedula(e.target.value)}
          />
        </div>
        <div>
          <label className="block font-bold mb-1 text-black">Telefono</label>
          <input
            type="text"
            value={telefono}
            onChange={(e) => setTelefono(e.target.value)}
          />
        </div>
        <div>
          <label className="block font-bold mb-1 text-black">Email</label>
          <input
            type="text"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>
        <div className="flex flex-col md:flex-row gap-4 mt-4">
          <button
            type="submit"
            className="w-full bg-black text-white p-2 rounded hover:bg-gray-800"
          >
            Registrar Cliente
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
