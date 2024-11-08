// src/components/Register.js
import React, { useState } from 'react';
import {
    Container,
    TextField,
    Button,
    Typography,
    Box,
    Alert,
} from '@mui/material';
import authService from '../services/authService';
import { useNavigate } from "react-router-dom";

/**
 * Componente de registro que permite a los usuarios crear una nueva cuenta.
 */
const Register = () => {
    const navigate = useNavigate();
    // Estados para los datos del formulario
    const [nombre, setNombre] = useState('');
    const [apellidos, setApellidos] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');      // Estado para mensajes de error
    const [success, setSuccess] = useState('');  // Estado para mensajes de éxito

    /**
     * Maneja el envío del formulario de registro, validando los campos y enviando los datos a la API.
     *
     * @param {Event} e - Evento de envío del formulario.
     */
    const handleSubmit = async (e) => {
        e.preventDefault(); // Previene el comportamiento por defecto del formulario
        setError(''); // Limpia cualquier mensaje de error anterior
        setSuccess(''); // Limpia cualquier mensaje de éxito anterior

        // Validación básica: todos los campos deben estar completos
        if (!nombre || !apellidos || !email || !password) {
            setError('Por favor, completa todos los campos.');
            return;
        }

        try {
            // Llamada al servicio de registro de usuario
            const response = await authService.register(nombre, apellidos, email, password);
            setSuccess(response.data); // Guarda el mensaje de éxito en el estado
            // Limpia los campos del formulario
            setNombre('');
            setApellidos('');
            setEmail('');
            setPassword('');
            navigate('/login');
        } catch (err) {
            // Muestra un mensaje de error en caso de fallo
            setError('Ocurrió un error. Por favor, intenta nuevamente.');
        }
    };

    // Renderiza el formulario de registro
    return (
        <Container maxWidth="sm">
            <Box
                sx={{
                    marginTop: 8,
                    display: 'flex',
                    flexDirection: 'column',
                    alignItems: 'center',
                }}
            >
                <Typography component="h1" variant="h5">
                    Registrarse
                </Typography>
                {/* Muestra mensaje de error si existe */}
                {error && (
                    <Alert severity="error" sx={{ width: '100%', mt: 2 }}>
                        {error}
                    </Alert>
                )}
                {/* Muestra mensaje de éxito si existe */}
                {success && (
                    <Alert severity="success" sx={{ width: '100%', mt: 2 }}>
                        {success}
                    </Alert>
                )}
                {/* Formulario de registro */}
                <Box component="form" onSubmit={handleSubmit} sx={{ mt: 1, width: '100%' }}>
                    <TextField
                        margin="normal"
                        required
                        fullWidth
                        id="nombre"
                        label="Nombre"
                        name="nombre"
                        autoComplete="nombre"
                        autoFocus
                        value={nombre}
                        onChange={(e) => setNombre(e.target.value)}
                    />
                    <TextField
                        margin="normal"
                        required
                        fullWidth
                        id="apellidos"
                        label="Apellidos"
                        name="apellidos"
                        autoComplete="apellidos"
                        value={apellidos}
                        onChange={(e) => setApellidos(e.target.value)}
                    />
                    <TextField
                        margin="normal"
                        required
                        fullWidth
                        id="email"
                        label="Correo Electrónico"
                        name="email"
                        autoComplete="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                    <TextField
                        margin="normal"
                        required
                        fullWidth
                        name="password"
                        label="Contraseña"
                        type="password"
                        id="password"
                        autoComplete="current-password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        color="primary"
                        sx={{ mt: 3, mb: 2 }}
                    >
                        Registrarse
                    </Button>
                </Box>
            </Box>
        </Container>
    );
};

export default Register;
