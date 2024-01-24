import React from "react";
import StarRating from "../StarRating/StarRating";

const Card = ({ data }) => {
  return (
    <div className="card-container">
     <div className="card-upper-body">
        <img src={data?.img} alt={data?.img} className="card-img" />
     </div>
     <div className="card-lower-body">
        <label className="card-title">{data?.title}</label>
        <label className="card-author">{data?.author}</label>
        <label className="card-price">Rs. {data?.price}</label>
        <StarRating rating={data?.rating || 0} />
     </div>
    </div>
  );
};

export default Card;
