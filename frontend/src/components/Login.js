// src/components/Login.js
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

const Login = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const [success, setSuccess] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        setSuccess('');

        // Validaciones básicas
        if (!email || !password) {
            setError('Por favor, completa todos los campos.');
            return;
        }

        try {
            const response = await authService.login(email, password);
            const { token } = response.data;

            // Guardar el token en localStorage
            localStorage.setItem('token', token);
            console.log("Token guardado en localStorage:", localStorage.getItem('token')); // Verifica que el token se guarda
            setSuccess('Inicio de sesión exitoso.');

            // Redirigir o realizar otras acciones después del login
            // Por ejemplo, redirigir a una página protegida
            // window.location.href = '/dashboard';
        } catch (err) {
            if (err.response && err.response.status === 401) {
                setError('Credenciales no válidas.');
            } else {
                setError('Ocurrió un error. Por favor, intenta nuevamente.');
            }
        }
    };

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
                    Iniciar Sesión
                </Typography>
                {error && (
                    <Alert severity="error" sx={{ width: '100%', mt: 2 }}>
                        {error}
                    </Alert>
                )}
                {success && (
                    <Alert severity="success" sx={{ width: '100%', mt: 2 }}>
                        {success}
                    </Alert>
                )}
                <Box component="form" onSubmit={handleSubmit} sx={{ mt: 1, width: '100%' }}>
                    <TextField
                        margin="normal"
                        required
                        fullWidth
                        id="email"
                        label="Correo Electrónico"
                        name="email"
                        autoComplete="email"
                        autoFocus
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
                        Iniciar Sesión
                    </Button>
                </Box>
            </Box>
        </Container>
    );
};

export default Login;
