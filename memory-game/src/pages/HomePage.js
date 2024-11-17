import React from "react";
import { useNavigate } from "react-router-dom";
import "./HomePage.css";

function HomePage() {
    const navigate = useNavigate();

    const goToLogin = () => {
        navigate("/login");
    };

    const goToGamePage = () => {
        navigate("/gamepage");
    };

    return (
        <div className="HomePage">
            <div style={{ textAlign: "center", marginTop: "50px" }}>
                <h1>Welcome to the Game JDK</h1>
                <button onClick={goToLogin} style={styles.button}>
                    Go to Login
                </button>
                <button onClick={goToGamePage} style={styles.button}>
                    Open GamePage
                </button>
            </div>
        </div>
    );
}

const styles = {
    button: {
        margin: "10px",
        padding: "10px 20px",
        fontSize: "16px",
        cursor: "pointer",
    },
};

export default HomePage;
