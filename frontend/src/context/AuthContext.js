// src/context/AuthContext.js
import React, { createContext, useState, useEffect } from 'react';
// @ts-ignore  
import jwt_decode from "jwt-decode";

// Crea el contexto de autenticación
export const AuthContext = createContext();

// Componente proveedor de autenticación
export const AuthProvider = ({ children }) => {
    // Estado para almacenar el token JWT. Se inicializa con el valor en localStorage (si existe)
    const [authToken, setAuthToken] = useState(() => localStorage.getItem('token'));

    // Estado para almacenar los datos del usuario decodificados del token JWT
    const [user, setUser] = useState(() => (
        authToken ? jwt_decode(authToken) : null // Decodifica el token solo si existe
    ));

    // useEffect para actualizar el estado del usuario cada vez que el authToken cambie
    useEffect(() => {
        if (authToken) {
            // Si hay un token, decodifica y establece los datos del usuario
            setUser(jwt_decode(authToken));
        } else {
            // Si no hay token, establece el usuario como null
            setUser(null);
        }
    }, [authToken]); // Dependencia en authToken: ejecuta el efecto cuando authToken cambia

    // Función para manejar el inicio de sesión
    const login = (token) => {
        // Guarda el token en localStorage
        localStorage.setItem('token', token);
        // Actualiza el estado con el nuevo token, lo que también activará el useEffect anterior
        setAuthToken(token);
    };

    // Función para manejar el cierre de sesión
    const logout = () => {
        // Elimina el token del localStorage
        localStorage.removeItem('token');
        // Establece authToken como null, lo que también activará el useEffect y limpiará el usuario
        setAuthToken(null);
    };

    // Retorna el proveedor del contexto con los valores y funciones necesarias
    return (
        <AuthContext.Provider value={{ authToken, user, login, logout }}>
            {children} {/* Renderiza los componentes hijos que consuman este contexto */}
        </AuthContext.Provider>
    );
};
