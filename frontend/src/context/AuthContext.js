// src/context/AuthContext.js
import React, { createContext, useState, useEffect } from 'react';
import * as jwt_decode from 'jwt-decode';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [authToken, setAuthToken] = useState(() => localStorage.getItem('token'));
    const [user, setUser] = useState(() => (authToken ? jwt_decode(authToken) : null));

    useEffect(() => {
        if (authToken) {
            setUser(jwt_decode(authToken));
        } else {
            setUser(null);
        }
    }, [authToken]);

    const login = (token) => {
        localStorage.setItem('token', token);
        setAuthToken(token);
    };

    const logout = () => {
        localStorage.removeItem('token');
        setAuthToken(null);
    };

    return (
        <AuthContext.Provider value={{ authToken, user, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};
