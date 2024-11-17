import React, { useState, useContext } from "react";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "../../../contexts/AuthContext";
import "./LoginContainer.css";

function LoginContainer() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const { login } = useContext(AuthContext);  
    const navigate = useNavigate();

    const handleLogin = async () => {
        const success = await login(email, password);  
        if (success) {
            navigate("/homePage"); 
        } else {
            alert("Invalid credentials");
        }
    };

    return (
        <div className="login-container">
            <h1 className="login-title">Login</h1>
            <div className="form-group">
                <input
                    type="email"
                    placeholder="Enter your email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    className="input"
                />
            </div>
            <div className="form-group">
                <input
                    type="password"
                    placeholder="Enter your password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    className="input"
                />
            </div>
            <button onClick={handleLogin} className="button">
                Login
            </button>
        </div>
    );
}

export default LoginContainer;
