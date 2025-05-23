import { useState } from "react";
import React from "react";
import "./App.css";
import { Route, Routes } from "react-router-dom";
import Inicio from "./pages/Inicio";
import RegistroClientes from "./pages/RegistroClientes";
import RegistroMesas from "./pages/RegistroMesas";
import RegistroReservas from "./pages/RegistroReservas";
import ConsultasAvanzadas from "./pages/ConsultasAvanzadas";
import ReservasPorDiaHora from "./pages/consultas/ReservasPorDiaHora";
import ReservasCanceladasUltimoTrimestre from "./pages/consultas/ReservasCanceladasUltimoTrimestre";
import ClientesFrecuentes from "./pages/consultas/ClientesFrecuentes";

function App() {
  return (
    <div className="bg-[#7fe77f]">
      <h1 className="flex justify-center items-center">
        Reservas y Restaurantes
      </h1>
      <p>Bienvenido a nuestra plataforma software de reservas y Restaurantes</p>
      <Routes>
        <Route path="/" element={<Inicio />} />
        <Route path="/registro-clientes" element={<RegistroClientes />} />
        <Route path="/registro-mesas" element={<RegistroMesas />} />
        <Route path="/registro-reservas" element={<RegistroReservas />} />
        <Route path="/consultas" element={<ConsultasAvanzadas />}>
          <Route path="reservas-dia-hora" element={<ReservasPorDiaHora />} />
          <Route
            path="canceladas-ultimos-3-meses"
            element={<ReservasCanceladasUltimoTrimestre />}
          />
          <Route path="clientes-frecuentes" element={<ClientesFrecuentes />} />
        </Route>
      </Routes>
    </div>
  );
}

export default App;
