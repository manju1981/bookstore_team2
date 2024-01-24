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
//    const handleSortChange = (event) => {
//        setSortCriterion(event.target.value);
//     };
     const handleSortAsc = () => {
      setSortDirection('false');
     };
     const handleSortDesc = () => {
      setSortDirection('true');
     };
    return (
      <Router>
      <div className="App">
        <Header />
        <Routes>
          <Route path="/" element={<Books books={books}
          setSortCriterion={setSortCriterion}
          handleSortAsc={handleSortAsc}
          handleSortDesc={handleSortDesc}
          />}/>
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