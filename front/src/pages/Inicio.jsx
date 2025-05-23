import React from "react";
import { Link } from "react-router-dom";

export default function Inicio() {
  return (
    <div className="flex flex-col items-center min-h-screen p-2 sm:p-4 md:p-10 bg-[#f38b26]">
      <nav className="relative border-2 bg-[#eeccac] p-2 rounded-md">
        <ul className="flex flex-wrap justify-center gap-4 py-2">
          <Link to="/registro-clientes">Clientes</Link>
          <Link to="/registro-mesas">Mesas</Link>
          <Link to="/registro-reservas">Reservas</Link>
          <Link to="/consultas/*">Consultas Avanzadas</Link>
        </ul>
      </nav>
    </div>
  );
}
