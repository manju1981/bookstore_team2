import React from 'react';

const StarRating = ({ rating }) => {
 const stars = [...Array(5)].map((_, index) => {
    return <span key={index} >&#9733;</span>; // Unicode character for star<p>☆</p>
 });
 const empty = [...Array(5)].map((_, index) => {
  return <span class="star-rating" key={index} >☆</span>;
  });

 return <div>{stars.slice(0, Math.round(rating))}{empty.slice(Math.round(rating),5)}</div>;
};

export default StarRating;