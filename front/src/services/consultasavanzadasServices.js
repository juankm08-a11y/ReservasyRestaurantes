import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api";

export const getReservasCompletadasPorDiaHora = () => {
  return axios.get(`${API_BASE_URL}/reservas/completadas-por-dia-hora`);
};

export const getCanceladasUltimos3Meses = () => {
  return axios.get(`${API_BASE_URL}/reservas/canceladas-ultimos-3-meses`);
};

export const getReservasPorMesaHora = () => {
  return axios.get(`${API_BASE_URL}/reservas/completadas-por-mesa-hora`);
};

export const getClientesFrecuentes = () => {
  return axios.get(`${API_BASE_URL}/reservas/clientes-frecuentes`);
};

export const getTotalPorEstado = () => {
  return axios.get(`${API_BASE_URL}/reservas/total-por-estado`);
};

export const getPorcentajeCanceladas = () => {
  return axios.get(`${API_BASE_URL}/reservas/porcentaje-canceladas`);
};
