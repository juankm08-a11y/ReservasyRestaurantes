import axios from "axios";

const API_URL = import.meta.env.VITE_API_URL;

export const getClientes = () => {
  return axios.get(`${API_URL}/lista-clientes`);
};

export const crearCliente = (datos) => {
  return axios.post(`${API_URL}/crear-clientes`, datos);
};

export const actualizarCliente = (datos) => {
  return axios.put(`${API_URL}/actualizar-clientes`, datos);
};

export const eliminarCliente = () => {
  return axios.delete(`${API_URL}/eliminar-clientes`);
};
