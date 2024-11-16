// src/context/AuthContext.js
import React, { createContext, useState, useEffect } from "react";
// @ts-ignore
import { jwtDecode as jwt_decode } from "jwt-decode";

// Crea el contexto de autenticación
export const AuthContext = createContext();

// Componente proveedor de autenticación
export const AuthProvider = ({ children }) => {
  // Estado para almacenar el token JWT. Se inicializa con el valor en localStorage (si existe)
  const [authToken, setAuthToken] = useState(() =>
    localStorage.getItem("token")
  );

  // Estado para almacenar los datos del usuario decodificados del token JWT
  const [user, setUser] = useState(() => {
    try {
      return authToken ? jwt_decode(authToken) : null; // Decodifica el token solo si existe
    } catch (error) {
      console.error("Token inválido al inicializar:", error);
      return null; // Si el token es inválido, retorna null
    }
  });

  console.log("AuthProvider - authToken:", authToken);
  console.log("AuthProvider - user:", user);

  // useEffect para actualizar el estado del usuario cada vez que el authToken cambie
  useEffect(() => {
    if (authToken) {
      try {
        // Si hay un token, decodifica y establece los datos del usuario
        const decodedUser = jwt_decode(authToken);
        setUser(decodedUser);
        console.log("AuthProvider - Actualizado user:", decodedUser);
      } catch (error) {
        // Si el token es inválido, establece el usuario como null y elimina el token
        console.error("Error al decodificar el token:", error);
        setUser(null);
        setAuthToken(null);
        localStorage.removeItem("token"); // Limpia el token inválido
      }
    } else {
      // Si no hay token, establece el usuario como null
      setUser(null);
      console.log("AuthProvider - User establecido a null");
    }
  }, [authToken]); // Dependencia en authToken: ejecuta el efecto cuando authToken cambia

  // Función para manejar el inicio de sesión
  const login = (token) => {
    // Guarda el token en localStorage
    localStorage.setItem("token", token);
    // Actualiza el estado con el nuevo token, lo que también activará el useEffect anterior
    setAuthToken(token);
  };

  // Función para manejar el cierre de sesión
  const logout = () => {
    // Elimina el token del localStorage
    localStorage.removeItem("token");
    // Establece authToken como null, lo que también activará el useEffect y limpiará el usuario
    setAuthToken(null);
  };

  // Retorna el proveedor del contexto con los valores y funciones necesarias
  return (
    <AuthContext.Provider value={{ authToken, user, login, logout }}>
      {children}{" "}
      {/* Renderiza los componentes hijos que consuman este contexto */}
    </AuthContext.Provider>
  );
};
