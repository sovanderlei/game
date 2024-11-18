import React from "react";
import GameBoard from "../components/Features/GameBoard/GameBoard.jsx"; // Importando o GameBoard

const GamePage = () => {
    return (
        <div className="GamePage">
            <h1>Memory Matching Game</h1>
            <GameBoard />
        </div>
    );
};

export default GamePage;
