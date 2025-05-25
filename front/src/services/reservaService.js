import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api";
export const verReservas = () => {
  return axios.get(`${API_BASE_URL}/lista-reservas`);
};

export const crearReserva = (datos) => {
  return axios.post(`${API_BASE_URL}/crear-reservas`, datos);
};

export const actualizarReserva = (datos) => {
  return axios.put(`${API_BASE_URL}/actualizar-mesas`, datos);
};

export const eliminarReserva = () => {
  return axios.delete(`${API_BASE_URL}/eliminar-reservas`);
};
