import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api";
export const getMesas = () => {
  return axios.get(`${API_BASE_URL}/lista-mesas`);
};

export const escogerMesa = (datos) => {
  return axios.post(`${API_BASE_URL}/escoger-mesas`, datos);
};

export const actualizarMesa = (datos) => {
  return axios.put(`${API_BASE_URL}/actualizar-mesas`, datos);
};

export const eliminarMesa = () => {
  return axios.delete(`${API_BASE_URL}/eliminar-mesas`);
};
