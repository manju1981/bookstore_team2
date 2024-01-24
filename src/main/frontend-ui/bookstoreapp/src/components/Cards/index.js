import React from "react";
import { Rating } from 'semantic-ui-react'


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
          <Rating icon='star' defaultRating={data?.rating} maxRating={5} />

        </div>
        <label className="card-author">{data?.author}</label>
        <label className="card-price">Rs. {data?.price}</label>
      </div>
    </div>
  );
};

export default Card;
