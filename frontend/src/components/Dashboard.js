// src/components/Dashboard.js
import React, { useEffect, useState } from 'react';
import authService from '../services/authService';
import { Typography, Container } from '@mui/material';

/**
 * Componente que muestra el Dashboard, una pantalla protegida que solo es accesible para usuarios autenticados.
 */
const Dashboard = () => {
    // Estado para almacenar el mensaje de la API
    const [message, setMessage] = useState('');

    /**
     * useEffect que se ejecuta una vez al montar el componente.
     * Llama a la función fetchProtectedData para obtener el contenido restringido de la API.
     */
    useEffect(() => {
        const fetchProtectedData = async () => {
            try {
                // Llamada a la API protegida para obtener un mensaje
                const response = await authService.axiosInstance.get('auth/protected');
                setMessage(response.data.message); // Guarda el mensaje recibido en el estado
            } catch (err) {
                console.error(err); // Muestra cualquier error en la consola
            }
        };

        fetchProtectedData(); // Ejecuta la función de obtención de datos
    }, []);

    // Renderiza el contenido del Dashboard, mostrando el mensaje protegido recibido de la API
    return (
        <Container>
            <Typography variant="h4" sx={{ mt: 4 }}>
                Dashboard
            </Typography>
            <Typography variant="body1" sx={{ mt: 2 }}>
                {message}
            </Typography>
        </Container>
    );
};

export default Dashboard;
