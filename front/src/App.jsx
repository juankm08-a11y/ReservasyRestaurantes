import { useState } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import "./App.css";
import { Route, Routes } from "react-router-dom";
import Inicio from "./pages/Inicio";
import RegistroClientes from "./pages/RegistroClientes";
import RegistroMesas from "./pages/RegistroMesas";
import RegistroReservas from "./pages/RegistroReservas";

function App() {
  const [count, setCount] = useState(0);

  return (
    <div className="bg-[#f38b26] ">
      <h1 className="flex justify-center items-center">
        Reservas y Restaurantes
      </h1>
      <Routes>
        <Route path="/" element={<Inicio />} />
        <Route path="/registro-clientes" element={<RegistroClientes />} />
        <Route path="/registro-mesas" element={<RegistroMesas />} />
        <Route path="/registro-reservas" element={<RegistroReservas />} />
      </Routes>
      <p>Bienvenido a nuestra plataforma software de reservas y Restaurantes</p>
    </div>
  );
}

export default App;
