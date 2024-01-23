import React, { useState } from "react";
import Card from "../Cards";
import PaginationNavigation from "../PaginationNavigation/PaginationNavigation";
const Books = ({ books }) => {
    // Move the state declaration inside the functional component
    const [booksState, setBooksState] = useState([]);

    return (
        <div className="body-container">
            <div className="cord-collection-wrapper" data-testid="Books-test">
                {books.map((item, index) => (
                    <Card key={index} data={item} />
                ))}
            </div>
            <div className="pagination-wrapper">
                <PaginationNavigation />
            </div>
        </div>
    );
};
export default Books;