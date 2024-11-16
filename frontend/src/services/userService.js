// src/services/userService.js
import authService from "./authService"; // Importa authService para reutilizar axiosInstance

// Función para obtener todos los usuarios
export const getUsers = () => {
  return authService.axiosInstance.get("users"); // Ajusta 'users' al endpoint real de tu backend
};

// Función para crear un nuevo usuario
export const createUser = (userData) => {
  return authService.axiosInstance.post("users", userData);
};

// Función para actualizar un usuario existente
export const updateUser = (userId, userData) => {
  return authService.axiosInstance.put(`users/${userId}`, userData);
};

// Función para eliminar un usuario
export const deleteUser = (userId) => {
  return authService.axiosInstance.delete(`users/${userId}`);
};
