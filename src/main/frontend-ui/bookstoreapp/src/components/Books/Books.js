import React, { useState } from "react";
import { Link } from "react-router-dom";
import Card from "../Cards";
import PaginationNavigation from "../PaginationNavigation/PaginationNavigation";
const Books = ({ books }) => {

    return (
        <div className="body-container">
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