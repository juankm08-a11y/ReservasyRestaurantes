import axios from "axios";

const API_URL = import.meta.env.VITE_API_URL;
export const verReservas = () => {
  return axios.get(`${API_URL}/lista-reservas`);
};

export const crearReserva = (datos) => {
  return axios.post(`${API_URL}/crear-reservas`, datos);
};

export const actualizarReserva = (datos) => {
  return axios.put(`${API_URL}/actualizar-mesas`, datos);
};

export const eliminarReserva = () => {
  return axios.delete(`${API_URL}/eliminar-reservas`);
};
