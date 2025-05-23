import axios from "axios";

const API_URL = import.meta.env.VITE_API_URL;

export const getReservasCompletadasPorDiaHora = () => {
  return axios.get(`${API_URL}/reservas/completadas-por-dia-hora`);
};

export const getCanceladasUltimos3Meses = () => {
  return axios.get(`${API_URL}/reservas/canceladas-ultimos-3-meses`);
};

export const getReservasPorMesaHora = () => {
  return axios.get(`${API_URL}/reservas/completadas-por-mesa-hora`);
};

export const getClientesFrecuentes = () => {
  return axios.get(`${API_URL}/reservas/clientes-frecuentes`);
};

export const getPorcentajeCanceladas = () => {
  axios.get(`${API_URL}/reservas/porcentaje-canceladas`);
};
