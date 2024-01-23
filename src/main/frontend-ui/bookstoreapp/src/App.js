import React, { useEffect, useState } from "react";
import "./App.css";
import Footer from "./components/Footer/Footer";
import Header from "./components/Header/Header";
import Books from "./components/Books/Books";
import PaginationNavigation from "./components/PaginationNavigation/PaginationNavigation";

function App() {
    const [books, setBooks] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [totalPages, setTotalPages] = useState(1);

    useEffect(() => {
        fetch(`http://localhost:8090/api/v1/book/fetch-all?page=${currentPage}&size=9`)
            .then((response) => response.json())
            .then((data) => {
                setBooks(data?.content);
                setCurrentPage(data?.currentPage);
                setTotalPages(data?.totalPages);
            })
            .catch((error) => console.error(error));
    }, [currentPage]);

    const handlePageChange = (e, { activePage }) => {
        setCurrentPage(activePage);
    };

    return (
        <div className="App">
            <Header />
            <Books books={books} />
            <PaginationNavigation
                currentPage={currentPage}
                totalPages={totalPages}
                onPageChange={handlePageChange}
            />
            <Footer />
        </div>
    );
}
export default App;