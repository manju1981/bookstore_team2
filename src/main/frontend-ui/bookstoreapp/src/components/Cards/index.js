import React from "react";
import StarRating from "../StarRating/StarRating";


const Card = ({ data }) => {
  console.log("data",data)
  return (
    <div className="card-container">
     <div className="card-upper-body">
        <img src={data?.img} alt={data?.img} className="card-img" />
      </div>
      <div className="card-lower-body">
        <div style={{ display: 'flex', flexDirection: 'row', justifyContent: 'space-between' }}>
          <label className="card-title">{data?.title}</label>
          <StarRating rating={data?.rating || 0} />
        </div>
        <label className="card-author">{data?.author}</label>
        <label className="card-price">Rs. {data?.price}</label>
      </div>
    </div>
  );
};

export default Card;
