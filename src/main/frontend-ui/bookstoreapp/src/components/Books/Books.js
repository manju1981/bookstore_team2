import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import Card from "../Cards";
import PaginationNavigation from "../PaginationNavigation/PaginationNavigation";

const Books = () => {

    const [books, setBooks] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [totalPages, setTotalPages] = useState(1);
    const [sortCriterion, setSortCriterion] = useState('title');
    const [sortDirection,setSortDirection] =useState('false');

    useEffect(() => {
        fetch(`http://localhost:8090/api/v1/books/fetch-all?page=${currentPage}&size=9&sort=${sortCriterion}&descending=${sortDirection}`)
            .then((response) => response.json())
            .then((data) => {
                setBooks(data?.content);
                setCurrentPage(data?.currentPage);
                setTotalPages(data?.totalPages);
            })
            .catch((error) => console.error(error));
    }, [currentPage,sortCriterion,sortDirection]);

    const handlePageChange = (e, { activePage }) => {
        setCurrentPage(activePage);
    };
     const handleSortAsc = () => {
      setSortDirection('false');
     };
     const handleSortDesc = () => {
      setSortDirection('true');
     };

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
            <PaginationNavigation
                currentPage={currentPage}
                totalPages={totalPages}
                onPageChange={handlePageChange}
            />
  
        </div>
    );
};
export default Books;