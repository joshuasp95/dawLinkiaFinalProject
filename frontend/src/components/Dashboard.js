// src/components/Dashboard.js
import React, { useEffect, useState } from "react";
import authService from "../services/authService";
import {
  Typography,
  Container,
  Button,
  CircularProgress,
  Alert,
} from "@mui/material";
import { useNavigate } from "react-router-dom";

/**
 * Componente que muestra el Dashboard, una pantalla protegida que solo es accesible para usuarios autenticados.
 */
const Dashboard = () => {
  // Estado para almacenar el mensaje de la API
  const [message, setMessage] = useState("");
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  /**
   * useEffect que se ejecuta una vez al montar el componente.
   * Llama a la función fetchProtectedData para obtener el contenido restringido de la API.
   */
  useEffect(() => {
    const fetchProtectedData = async () => {
      try {
        // Llamada a la API protegida para obtener un mensaje
        const response = await authService.axiosInstance.get("auth/protected");
        setMessage(response.data.message); // Guarda el mensaje recibido en el estado
        setLoading(false); // Actualiza el estado de carga a false
      } catch (err) {
        console.error(err); // Muestra cualquier error en la consola
        setLoading(false); // Actualiza el estado de carga a false
      }
    };

    fetchProtectedData(); // Ejecuta la función de obtención de datos
  }, []);

  const handleManageUsers = () => {
    console.log("Dashboard - Redirigiendo a /users");
    navigate("/users"); // Redirige a la ruta de gestión de usuarios
  };

  // Renderiza el contenido del Dashboard
  return (
    <Container>
      <Typography variant="h4" sx={{ mt: 4 }}>
        Dashboard
      </Typography>
      {loading ? (
        <CircularProgress sx={{ mt: 2 }} />
      ) : error ? (
        <Alert severity="error" sx={{ mt: 2 }}>
          {error}
        </Alert>
      ) : (
        <>
          <Typography variant="body1" sx={{ mt: 2 }}>
            {message}
          </Typography>
          <Button
            variant="contained"
            color="secondary"
            onClick={handleManageUsers}
            sx={{ mt: 4 }}
          >
            Gestionar Usuarios
          </Button>
        </>
      )}
    </Container>
  );
};

export default Dashboard;
