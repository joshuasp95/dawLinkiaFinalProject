// src/components/PrivateRoute.js
import React, { useContext } from 'react';
import { Navigate } from 'react-router-dom';
import { AuthContext } from '../context/AuthContext';

/**
 * Componente de ruta protegida que redirige a la página de inicio de sesión si el usuario no está autenticado.
 *
 * @param {ReactNode} children - Componentes hijos que se mostrarán si el usuario está autenticado.
 */
const PrivateRoute = ({ children }) => {
    // Extrae el token de autenticación del contexto
    const { authToken } = useContext(AuthContext);

    // Si el usuario está autenticado, renderiza los hijos; de lo contrario, redirige a /login
    return authToken ? children : <Navigate to="/login" />;
};

export default PrivateRoute;
