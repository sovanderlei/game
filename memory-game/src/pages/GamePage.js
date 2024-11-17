import React, { useState, useEffect } from "react";
import "./GamePage.css";
import { useNavigate } from "react-router-dom";

const initialCards = [
    { id: 1, image: "../images/cards/KP.png", flipped: false, matched: false },
    { id: 2, image: "../images/cards/DV.png", flipped: false, matched: false },
    { id: 3, image: "../images/cards/KV.png", flipped: false, matched: false },
    { id: 4, image: "../images/cards/DG.png", flipped: false, matched: false },
    { id: 5, image: "../images/cards/BG.png", flipped: false, matched: false },
    { id: 6, image: "../images/cards/KVD.png", flipped: false, matched: false },
    { id: 7, image: "../images/cards/KP.png", flipped: false, matched: false },
    { id: 8, image: "../images/cards/DV.png", flipped: false, matched: false },
    { id: 9, image: "../images/cards/KV.png", flipped: false, matched: false },
    { id: 10, image: "../images/cards/DG.png", flipped: false, matched: false },
    { id: 11, image: "../images/cards/BG.png", flipped: false, matched: false },
    { id: 12, image: "../images/cards/KVD.png", flipped: false, matched: false },
];

const shuffleCards = (cards) => {
    return cards
        .slice()
        .sort(() => Math.random() - 0.5)
        .map((card) => ({ ...card }));
};

function GamePage() {
    const [cards, setCards] = useState(shuffleCards(initialCards));
    const [flippedCards, setFlippedCards] = useState([]);
    const [disabled, setDisabled] = useState(false);
    const [counter, setCounter] = useState(0);
    const [isRunning, setIsRunning] = useState(false);
    const [points, setPoints] = useState(0);
    const navigate = useNavigate();

    const handleCardClick = (index) => {
        if (disabled || cards[index].flipped || flippedCards.length === 2) return;

        const updatedCards = cards.map((card, i) =>
            i === index ? { ...card, flipped: true } : card
        );
        setCards(updatedCards);
        setFlippedCards([...flippedCards, index]);
    };


    useEffect(() => {
        if (flippedCards.length === 2) {
            const [firstIndex, secondIndex] = flippedCards;
            const firstCard = cards[firstIndex];
            const secondCard = cards[secondIndex];

            if (firstCard.image === secondCard.image) {
                setIsRunning(false);

                setPoints((prevPoints) => prevPoints + 10);

                const updatedCards = cards.map((card, index) =>
                    index === firstIndex || index === secondIndex
                        ? { ...card, matched: true }
                        : card
                );
                setCards(updatedCards);
                setFlippedCards([]);

                setTimeout(() => {
                    setIsRunning(true);
                }, 500);
            } else {

                setDisabled(true);
                setTimeout(() => {
                    const updatedCards = cards.map((card) =>
                        card.flipped && !card.matched ? { ...card, flipped: false } : card
                    );
                    setCards(updatedCards);
                    setFlippedCards([]);
                    setDisabled(false);
                }, 1000);
            }
        }
    }, [flippedCards, cards]);


    useEffect(() => {
        let interval;
        if (isRunning) {
            interval = setInterval(() => setCounter((prev) => prev + 1), 1000);
        } else {
            clearInterval(interval);
        }
        return () => clearInterval(interval);
    }, [isRunning]);


    const handleStart = () => setIsRunning(true);
    const handlePause = () => setIsRunning(false);
    const handleReset = () => {
        setIsRunning(false);
        setCounter(0);
        setPoints(0);
        setCards(shuffleCards(initialCards));
    };

    const handleBack = () => {
        navigate("/homePage");
    };

    return (
        <div className="GamePage">
            <h1>Memory Matching Game</h1>
            <div className="board">
                {cards.map((card, index) => (
                    <div
                        key={index}
                        className={`card ${card.flipped ? "flipped" : ""}`}
                        onClick={() => handleCardClick(index)}
                    >
                        {card.flipped || card.matched ? (
                            <img src={card.image} alt="Card front" className="card-image" />
                        ) : (
                            <img src="/images/cards/card-back.png" alt="Card back" className="card-image" />
                        )}
                    </div>
                ))}
            </div>

            <div className="footCard">
                <div className="column">
                    <h3>Timer: {counter}s</h3>
                    <button onClick={handleStart}>Start</button>
                    <button onClick={handlePause}>Pause</button>
                    <button onClick={handleReset}>Restart</button>
                </div>

                <div className="column">
                    <img
                        src="../images/cards/userAvatar.jpg"
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

        </div>
    );
}

export default GamePage;