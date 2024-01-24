import React, { useEffect, useState } from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import "./App.css";
import Footer from "./components/Footer/Footer";
import Header from "./components/Header/Header";
import Books from "./components/Books/Books";
import PaginationNavigation from "./components/PaginationNavigation/PaginationNavigation";
import BookDetails from "./components/BookDetails/BookDetails";

function App() {
    const [books, setBooks] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [totalPages, setTotalPages] = useState(1);

    useEffect(() => {
        fetch(`http://localhost:8090/api/v1/books/fetch-all?page=${currentPage}&size=9`)
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
      <Router>
      <div className="App">
        <Header />
        <Routes>
          <Route path="/" element={<Books books={books} />} />
          <Route path="/books" element={<Books books={books} />} />
          <Route path="/book/:id" element={<BookDetails />} />
        </Routes>
            <PaginationNavigation
                currentPage={currentPage}
                totalPages={totalPages}
                onPageChange={handlePageChange}
            />
        <Footer />
      </div>
    </Router>
    );
}
export default App;