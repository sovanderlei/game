// src/routes/AppRoutes.js
import React from "react";
import { Routes, Route } from "react-router-dom";
import LoginPage from "../pages/LoginPage";
import HomePage from "../pages/HomePage";
import GamePage from "../pages/GamePage";
import PrivateRoute from "./PrivateRoute";

const AppRoutes = () => {
    return (
        <Routes>
            <Route path="/" element={<LoginPage />} />
            <Route path="/login" element={<LoginPage />} />
            <Route
                path="/homePage"
                element={
                    <PrivateRoute>
                        <HomePage />
                    </PrivateRoute>
                }
            />
            <Route
                path="/gamepage"
                element={
                    <PrivateRoute>
                        <GamePage />
                    </PrivateRoute>
                }
            />
            
        </Routes>
    );
};

export default AppRoutes;
