// src/services/authService.js
import axios from 'axios';

// URL base de la API
const API_URL = 'http://localhost:8081/api/auth/';

/**
 * Función para iniciar sesión.
 * Realiza una solicitud POST al endpoint de login.
 *
 * @param {string} email - Correo electrónico del usuario.
 * @param {string} password - Contraseña del usuario.
 * @returns {Promise} - Promesa con la respuesta de la API.
 */
const login = async (email, password) => {
    console.log("URL de la solicitud de login:", `${API_URL}login`);
    console.log("Datos enviados:", { email, password });
    try {
        const response = await axios.post(`${API_URL}login`, { email, password });
        console.log("Respuesta recibida:", response);

        return response;
    } catch (error) {
        console.error("Error en login:", error);
        throw error;
    }
};



/**
 * Función para registrar un nuevo usuario.
 * Realiza una solicitud POST al endpoint de registro.
 *
 * @param {string} nombre - Nombre del usuario.
 * @param {string} apellidos - Apellidos del usuario.
 * @param {string} email - Correo electrónico del usuario.
 * @param {string} password - Contraseña del usuario.
 * @returns {Promise} - Promesa con la respuesta de la API.
 */
const register = (nombre, apellidos, email, password) => {
    return axios.post(`${API_URL}register`, { nombre, apellidos, email, password });
};

/**
 * Función para cerrar sesión.
 * Elimina el token del localStorage.
 */
const logout = () => {
    localStorage.removeItem('token');
};

// Crear una instancia de axios para las solicitudes a la API protegida
const axiosInstance = axios.create({
    baseURL: API_URL,
    headers: {
        'Content-Type': 'application/json',
    },
});


// Añadir un interceptor para incluir el token en las solicitudes
axiosInstance.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem('token');
        if (token && !config.url.includes('/auth/login')) { // Evita el token en /auth/login
            config.headers['Authorization'] = `Bearer ${token}`;
        }
        return config;
    },
    (error) => Promise.reject(error)
);




// Exportar los servicios de autenticación
const authService = {
    login,
    register,
    logout,
    axiosInstance,
};

export default authService;
