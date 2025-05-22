import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api";

export const getClientes = () => {
  return axios.get(`${API_BASE_URL}/lista-clientes`);
};

export const crearCliente = (datos) => {
  return axios.post(`${API_BASE_URL}/crear-clientes`, datos);
};

export const actualizarCliente = (datos) => {
  return axios.put(`${API_BASE_URL}/actualizar-clientes`, datos);
};

export const eliminarCliente = () => {
  return axios.delete(`${API_BASE_URL}/eliminar-clientes`);
};
