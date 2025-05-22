import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api";

export const getReservasCompletadasPorDiaHora = () => {
  return axios.get(`${API_BASE_URL}`);
};

export const getCanceladasUltimos3Meses = () => {
  return axios.get(`${API_BASE_URL}/reservas/canceladas-ultimos-3-meses`);
};

export const getReservasPorMesaHora = () => {
  return axios.get(`${API_URL}/completadas-por-mesa-hora`);
};

export const getClientesFrecuentes = () => {
  return axios.get(`${API_URL}/clientes-frecuentes`);
};

export const getPorcentajeCanceladas = () => {
  axios.get(`${API_URL}/porcentaje-canceladas`);
};
