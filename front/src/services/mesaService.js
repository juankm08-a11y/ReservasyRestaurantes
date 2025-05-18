import axios from "axios";

const API_BASE_URL = "http://localhost:8080";
export const getMesas = () => {
  return axios.get(`${API_BASE_URL}/lista-mesas`);
};

export const escogerMesa = (datos) => {
  return axios.post(`${API_BASE_URL}/crear-mesas`, datos);
};

export const actualizarMesa = (datos) => {
  return axios.put(`${API_BASE_URL}/actualizar-mesas`, datos);
};

export const eliminarMesa = () => {
  return axios.delete(`${API_BASE_URL}/eliminar-mesas`);
};
