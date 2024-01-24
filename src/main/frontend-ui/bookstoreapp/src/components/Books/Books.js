import React, { useState } from "react";
import { Link } from "react-router-dom";
import Card from "../Cards";
import PaginationNavigation from "../PaginationNavigation/PaginationNavigation";

const Books = ({ books,setSortCriterion,handleSortAsc,handleSortDesc}) => {
    return (
        <div className="body-container">
            <div className="button-container">
                <button onClick={handleSortAsc}>↑</button>
                <button onClick={handleSortDesc}>↓</button>
                <button onClick={() => setSortCriterion("title")}>Sort by Title</button>
                <button onClick={() => setSortCriterion("price")}>Sort by Price</button>
                <button onClick={() => setSortCriterion("rating")}>Sort by Rating</button>
            </div>
            <div className="cord-collection-wrapper" data-testid="Books-test">
                {books.map((item, index) => (
                  <Link to={`/book/${item.id}`} key={index}>
                    <Card key={index} data={item} />
                  </Link>
                ))}
            </div>
        </div>
    );
};
export default Books;