import React from 'react';

const StarRating = ({ rating }) => {
 const stars = [...Array(5)].map((_, index) => {
    return <span key={index}>&#9733;</span>; // Unicode character for star
 });

 return <div>{stars.slice(0, Math.round(rating))}</div>;
};

export default StarRating;