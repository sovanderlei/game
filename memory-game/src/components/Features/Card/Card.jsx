import React from "react";
import "./Card.css"; // Arquivo CSS para estilizar as cartas

const Card = ({ card, index, handleCardClick }) => {
    return (
        <div
            className={`card ${card.flipped ? "flipped" : ""}`}
            onClick={() => handleCardClick(index)}
        >
            {card.flipped || card.matched ? (
                <img src={card.image} alt="Card front" className="card-image" />
            ) : (
                <img
                    src="../../../images/cards/card-back.png"
                    alt="Card back"
                    className="card-image"
                />
            )}
        </div>
    );
};

export default Card;
