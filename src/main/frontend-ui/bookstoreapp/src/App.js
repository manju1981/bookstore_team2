import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import "./App.css";
import Footer from "./components/Footer/Footer";
import Header from "./components/Header/Header";
import Books from "./components/Books/Books";
import BookDetails from "./components/BookDetails/BookDetails";

function App() {
  return (
    <Router>
      <div className="App">
        <Header />
        <Routes>
          <Route path="/" element={<Books />} />
          <Route path="/book/:id" element={<BookDetails />} />
        </Routes>
        <Footer />
      </div>
    </Router>
  );
}
export default App;
