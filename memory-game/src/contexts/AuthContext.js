import React, { createContext, useState } from "react";
export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);
 
    const login = async (email, password) => { 
        if (email === "test@test.com" && password === "123456") {
            setUser({ email });
            return true;  
        }
        return false;  
    };
 
    const logout = () => {
        setUser(null);
    };
 
    const value = {
        user,
        login,
        logout,
    };

    return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};



/*
import React, { createContext, useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);
    const navigate = useNavigate();

    const login = async (email, password) => {
        try {
            const response = await fetch('http://localhost:8000/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ email, password }),
            });

            const data = await response.json();

            if (response.ok) {
                setUser(data.user);
                localStorage.setItem('user', JSON.stringify(data.user));
                navigate('/homePage');
            } else {
                alert(data.message || 'Invalid credentials');
            }
        } catch (error) {
            console.error('Error during login:', error);
            alert('An error occurred. Please try again.');
        }
    };

    const logout = () => {
        setUser(null);
        localStorage.removeItem('user');
        navigate('/');
    };

    useEffect(() => {
        const storedUser = localStorage.getItem('user');
        if (storedUser) {
            setUser(JSON.parse(storedUser));
        }
    }, []);

    return (
        <AuthContext.Provider value={{ user, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};
*/