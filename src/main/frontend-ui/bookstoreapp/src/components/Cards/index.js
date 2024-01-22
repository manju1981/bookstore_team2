import React from "react";

const Card = ({ data }) => {
  return (
    <div className="card-container">
      <div className="card-upper-body">
        <img src={data?.img} alt={data?.img} className="card-img" />
      </div>
      <div className="card-lower-body">
        <label className="card-title">{data?.title}</label>
        <label className="card-author">{data?.author}</label>
      </div>
    </div>
  );
};

export default Card;
