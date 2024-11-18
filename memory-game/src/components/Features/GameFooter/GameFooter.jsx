import React from "react";
import "./GameFooter.css";

const GameFooter = ({ counter, points, handleStart, handlePause, handleReset, handleBack }) => {
    return (
        <div className="footCard">
            <div className="column">
                <h3>Timer: {counter}s</h3>
                <button onClick={handleStart}>Start</button>
                <button onClick={handlePause}>Pause</button>
                <button onClick={handleReset}>Restart</button>
            </div>

            <div className="column">
                <img
                    src="../../../images/cards/userAvatar.jpg"
                    alt="User Avatar"
                    className="user-avatar"
                />
                <h3>User: John Doe</h3>
                <p>Points: {points}</p>
            </div>

            <div className="column">
                <button onClick={handleBack}>Back</button>
            </div>
        </div>
    );
};

export default GameFooter;
