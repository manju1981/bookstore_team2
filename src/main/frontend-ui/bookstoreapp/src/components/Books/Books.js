import React, { useState } from "react";
import Card from "../Cards";
import PaginationNavigation from "../PaginationNavigation/PaginationNavigation";
const Books = ({ books }) => {

    return (
        <div className="body-container">
            <div className="cord-collection-wrapper" data-testid="Books-test">
                {books.map((item, index) => (
                    <Card key={index} data={item} />
                ))}
            </div>
        </div>
    );
};
export default Books;