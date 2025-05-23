import { useState } from "react";
import React from "react";
import "./App.css";
import { Route, Routes } from "react-router-dom";
import Inicio from "./pages/Inicio";
import RegistroClientes from "./pages/RegistroClientes";
import RegistroMesas from "./pages/RegistroMesas";
import RegistroReservas from "./pages/RegistroReservas";
import ConsultasAvanzadas from "./pages/ConsultasAvanzadas";
import CompletadasPorDiaHora from "./components/CompletadasPorDiaHora";
import ReservasPorMesaHora from "./components/ReservasPorMesaHora";
import ReservasCanceladasUltimoTrimestre from "./components/ReservasCanceladasUltimoTrimestre";
import ClientesFrecuentes from "./components/ClientesFrecuentes";
import PorcentajesCanceladas from "./components/PorcentajesCanceladas";

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
        <Route path="/consultas-avanzadas" element={<ConsultasAvanzadas />} />
        <Route
          path="/consultas/reservas-dia-hora"
          element={<CompletadasPorDiaHora />}
        />
        <Route
          path="/consultas/reservas-mesa-hora"
          element={<ReservasPorMesaHora />}
        />
        <Route
          path="/consultas/canceladas-ultimos-3-meses"
          element={<ReservasCanceladasUltimoTrimestre />}
        />
        <Route
          path="/consultas/clientes-frecuentes"
          element={<ClientesFrecuentes />}
        />
        <Route
          path="/consultas/porcentaje-canceladas"
          element={<PorcentajesCanceladas />}
        />
      </Routes>
    </div>
  );
}

export default App;
