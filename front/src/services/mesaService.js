import axios from "axios";

const API_URL = import.meta.env.VITE_API_URL;
export const getMesas = () => {
  return axios.get(`${API_URL}/lista-mesas`);
};

export const escogerMesa = (datos) => {
  return axios.post(`${API_URL}/escoger-mesas`, datos);
};

export const actualizarMesa = (datos) => {
  return axios.put(`${API_URL}/actualizar-mesas`, datos);
};

export const eliminarMesa = () => {
  return axios.delete(`${API_URL}/eliminar-mesas`);
};
