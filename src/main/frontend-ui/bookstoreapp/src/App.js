import React, { useEffect, useState } from "react";
import "./App.css";
import Footer from "./components/Footer/Footer";
import Header from "./components/Header/Header";
import Books from "./components/Books/Books";
import PaginationNavigation from "./components/PaginationNavigation/PaginationNavigation";

function App() {
    const [books, setBooks] = useState([]);
    const [currentPage, setCurrentPage] = useState();
    const [totalPages, setTotalPages] = useState();

    useEffect(() => {
        fetch("http://localhost:8090/api/v1/book/fetch-all")
            .then((response) => response.json())
            .then((data) => setBooks(data?.content))
            .catch((error) => console.error(error));
    }, []);

    return (
        <div className="App">
            <Header />
            <Books books={books} />
            <Footer />
        </div>
    );
}

export default App;
